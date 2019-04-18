package com.sherlock.mallcommon.utils;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (C), 2015-2018,
 * FileName: FFMpegUtil
 * Author:   jcj
 * Date:     2018/11/16 9:51
 * Description: ffempeg工具类
 */
public class FFMpegUtil
{
    
    private static final Logger logger = LoggerFactory.getLogger(FFMpegUtil.class);
    
    // ffmpeg命令所在路径
    private static final String FFMPEG_PATH = "/home/chaoren/ffmpeg/ffmpeg/ffmpeg";
    
    // ffmpeg处理后的临时文件
    private static final String TMP_PATH = "/tmp";
    
    // home路径
    private static final String HOME_PATH;
    
    static
    {
        HOME_PATH = System.getProperty("user.home");
        logger.info("static home path : " + HOME_PATH);
    }
    

    /**
     * 功能描述: <br> 网络视频提取音频
     *             <br> 返回 key:音频url value:音频时长
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/19 17:12
     */
    public static Pair<String, Integer> videoToAudio(String videoUrl)
    {
        String aacFile = "";
        int duration = 0;
        String resultUrl = "";
        try
        {
            // 处理linux系统shell脚本下&转义
            videoUrl = videoUrl.replace("&", "\\&");
            // 指定音频文件路径 保存在tmp下
            aacFile = TMP_PATH + "/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + UUID.randomUUID().toString().replaceAll("-", "") + ".aac";
            // 生成提取音频command命令
            String command = FFMPEG_PATH + " -i " + videoUrl + " -vn -acodec copy " + aacFile;
            logger.info("video to audio command --------------: " + command);
            // 添加bin/sh ，防止command被阻挡
            String[] commands = new String[] {"/bin/sh", "-c", command};
            // 执行command命令
            Process process = Runtime.getRuntime().exec(commands);
            process.waitFor();
            
            // 获取提取的音频时长
            duration = getVideoTime(aacFile);
            
            // 将音频流上传到七牛云
            InputStream is = new FileInputStream(new File(aacFile));
            resultUrl = QiniuOssUtils.upload(is, getFileName());
            is.close();
            
            // 删除本地tmp音频文件
            File file = new File(aacFile);
            if (file.exists())
            {
                file.delete();
            }
            
        }
        catch (Exception e)
        {
            logger.error("video change fail，video-url--------" + videoUrl, e);
        }
        return new Pair<String, Integer>(resultUrl, duration);
    }
    
    private static String getFileName()
    {
        Random random = new Random();
        String file = random.nextInt(10000) + System.currentTimeMillis() + ".mp3";
        return file;
    }

    /**
     * 功能描述: <br>
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/19 17:05
     */
    public static int getVideoTime(String video_path) {
        List<String> commands = new java.util.ArrayList<String>();
//        commands.add("/bin/sh");
//        commands.add("-c");
        commands.add(FFMPEG_PATH);
        commands.add("-i");
        commands.add(video_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            final Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            //从视频信息中解析时长
            String regexDuration = "Duration: ([\\d:]*)";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                int time = getTimelen(m.group(1));
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 功能描述: <br> 根据时长字符串获取秒数 例如 00:02:01 -- 121s
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/19 17:05
     */
    private static int getTimelen(String timelen){
        if (StringUtils.isEmpty(timelen)){
            return 0;
        }
        int min=0;
        String strs[] = timelen.split(":");
        if (strs.length!=3){
            return 0;
        }
        if (strs[0].compareTo("0") > 0) {
            min+=Integer.valueOf(strs[0])*60*60;//秒
        }
        if(strs[1].compareTo("0")>0){
            min+=Integer.valueOf(strs[1])*60;
        }
        if(strs[2].compareTo("0")>0){
            min+=Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }
}