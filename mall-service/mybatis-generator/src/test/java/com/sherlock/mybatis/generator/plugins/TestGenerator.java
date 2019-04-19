package com.sherlock.mybatis.generator.plugins;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestGenerator {

    private File configFile;

    private static final String GOODS_MODULE_PATH = "mall-goods";

    private static final String PAYMENT_MODULE_PATH = "mall-payment";

    @Before
    public void before() {
        //读取mybatis参数
        String path="D:\\project\\mall\\mall-service\\"+PAYMENT_MODULE_PATH+"\\src\\main\\resources\\generator\\generatorConfig.xml";
        configFile = new File(path);

    }

    @Test
    public void test() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}