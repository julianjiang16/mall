package com.sherlock.recommend.handler;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Copyright (C), 2015-2019
 * FileName: RecommendStepOne
 * Author:   jcj
 * Date:     2019/4/19 16:36
 * Description:
 *  map -> 数据切片
 *  reduce -> 合成结果
 *  <h2> 数据源：  userId1:goodsId1,goodsId2.....
 *                 userId2:goodsId1,goodsId3.....
 *  </h2>
 */
public class RecommendStepOne {

    static class UserGoodsStepMapper extends Mapper<LongWritable, Text,Text,Text> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] user_goods = line.split(":");
            String user = user_goods[0];
            String goods = user_goods[1];
            for (String good: goods.split(",")){
                context.write(new Text(user),new Text(good));
            }
        }
    }

    static class UserGoodsStepReduce extends Reducer<Text, Text,Text,Text>  {
        @Override
        protected void reduce(Text goods, Iterable<Text> user, Context context) throws IOException, InterruptedException {

            StringBuffer sb = new StringBuffer();

            for (Text u : user){
                sb.append(u).append(",");
            }
            context.write(goods,new Text(sb.toString()));
        }
    }
}
