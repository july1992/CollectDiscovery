package com.vily.collect.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 主键工具类
 */
public class KeyUtil {


    /**
     * 生成32位不重复主键
     */
    public static synchronized String getKeyNo() {

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 生成22位编号内码
     */
    public static synchronized String getNoCode(){
        Random random = new Random();

        String randomInt = String.valueOf(random.nextInt(90000) + 10000);

        String format = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        return format+randomInt;

    }


    /**
     * 生成6位随机数字
     */
    public static String getSixRandomCode() {

        return (int)((Math.random()*9+1)*100000)+"";
    }

    /**
     * 生成通讯10位编号内码
     */
    public static synchronized String getTenRandomCode(){

        String randomStr = RandomStringUtils.random(7, "abcdefghijklmnopqrstuvwxyz1234567890");

        String format = new SimpleDateFormat("SSS").format(new Date());

        return format+randomStr;

    }

}
