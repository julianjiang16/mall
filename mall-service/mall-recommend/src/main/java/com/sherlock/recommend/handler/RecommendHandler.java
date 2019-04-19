package com.sherlock.recommend.handler;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Copyright (C), 2015-2019
 * FileName: RecommendHandler
 * Author:   jcj
 * Date:     2019/4/19 16:30
 * Description:
 */
public class RecommendHandler {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 实例化配置
        Configuration conf = new Configuration();
        // 获取任务实例
        Job job = Job.getInstance(conf);
        job.setJarByClass(RecommendStepOne.class);
        // 设置输出key value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        // 设置启动map reduce类
        job.setMapperClass(RecommendStepOne.UserGoodsStepMapper.class);
        job.setReducerClass(RecommendStepOne.UserGoodsStepReduce.class);

        FileInputFormat.setInputPaths(job,new Path("D:/maven/in"));
        FileOutputFormat.setOutputPath(job,new Path("D:/maven/out"));

        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
