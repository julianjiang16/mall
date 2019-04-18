package com.sherlock.mallcommon.utils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

/**
 * Copyright (C), 2015-2018,
 * FileName: QiniuOssUtils
 * Author:   jcj
 * Date:     2018/11/8 10:41
 * Description: 七牛云对象存储工具类
 */
public class QiniuOssUtils
{
    
    /** slf4j log */
    private static final Logger log = LoggerFactory.getLogger(QiniuOssUtils.class);
    
    /** accessKey */
    private static String accessKey = "";
    
    /** secretKey */
    private static String secretKey = "";
    
    /** 存储空间 */
    private static String bucketName = "";
    
    /** 存储空间域名 */
    private static String domainOfBucket = "";
    

    /**
     * 功能描述: <br>
     * 初始化oss客户端
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/25 10:47
     */
    static
    {
        InputStream inputStream = QiniuOssUtils.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try
        {
            properties.load(inputStream);
            
            accessKey = properties.getProperty("qiniu.access.key");
            secretKey = properties.getProperty("qiniu.secret.key");
            bucketName = properties.getProperty("qiniu.bucket.name");
            domainOfBucket = properties.getProperty("qiniu.domain.bucket");
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
            log.error("QiniuOssUtils初始化，系统异常退出，异常信息：" + e.getMessage());
            System.exit(1);
        }
    }

    public static String getToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketName);
        return upToken;
    }

    public static String getQiniuUrlByKey(String key)
        throws UnsupportedEncodingException
    {
        String result = "";
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600 * 24 * 365 * 30;
        String encodedFileName = URLEncoder.encode(key, "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        result = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        return result;
    }
    
    /**
     * 功能描述: <br>
     * 数据流上传
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/8 10:56
     */
    public static String upload(InputStream stream, String fileName)
    {
        String result = "";
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketName);
        try
        {
            Response response = uploadManager.put(stream, key, upToken, null, null);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            
            String encodedFileName = URLEncoder.encode(fileName, "utf-8");
            String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
            // 100年失效
            long expireInSeconds = 3600 * 24 * 365 * 30;
            result = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        }
        catch (QiniuException ex)
        {
            Response r = ex.response;
            try {
                log.info("qiniu upload exception response:"+r.bodyString());
            } catch (QiniuException e) {
                log.info(e.getMessage());
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    private static String[] getSizeLessThanZero()
    {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        // 文件名前缀
        String prefix = "";
        // 每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        // 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        List<String> target = Lists.newArrayList();
        // 列举空间文件列表
        BucketManager.FileListIterator fileListIterator =
            bucketManager.createFileListIterator(bucketName, prefix, limit, delimiter);
        while (fileListIterator.hasNext())
        {
            // 处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items)
            {
                if (item.fsize == 0)
                {
                    target.add(item.key);
                }
            }
        }
        String[] result = new String[target.size()];
        
        return target.toArray(result);
    }
    
    private static void delFileByKey(String[] keys)
    {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try
        {
            // 单次批量请求的文件数量不得超过1000
            
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucketName, keys);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keys.length; i++)
            {
                BatchStatus status = batchStatusList[i];
                String key = keys[i];
                System.out.print(key + "\t");
                if (status.code == 200)
                {
                    System.out.println("delete success");
                }
                else
                {
                    System.out.println(status.data.error);
                }
            }
        }
        catch (QiniuException ex)
        {
            log.info("operation delete qiniu file exception:"+ex.response.toString());
        }
    }

    private static String getUrl(String key) throws UnsupportedEncodingException {
        Auth auth = Auth.create(accessKey, secretKey);
        String encodedFileName = URLEncoder.encode(key, "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        // 30年失效
        long expireInSeconds = 3600 * 24 * 365 * 30;
        String result = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        return result;
    }
    
    public static void main(String[] args)
        throws UnsupportedEncodingException
    {
        Auth auth = Auth.create(accessKey, secretKey);
        String encodedFileName = URLEncoder.encode("mv4.MP4", "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        // 100年失效
        long expireInSeconds = 3600 * 24 * 365 * 30;
        String result = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        // System.err.println(JSONObject.toJSONString(getSizeLessThanZero()));
    }
}
