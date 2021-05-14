package com.vily.collect.utils;

import java.util.regex.Pattern;

/**
 * 验证工具类
 */
public class VerifyUtils {

    /**
     * 验证手机号
     */
    public static boolean CheckMobilePhoneNum(String phoneNum) {
        return phoneNum.length() == 11 ? Pattern.compile("^(1[3-9]\\d{9}$)").matcher(phoneNum).matches() : false;
    }

    /**
     * 验证视频格式
     */
    public static boolean CheckVideoFile(String suffix) {
        String videos = "mp4|flv|avi|rm|rmvb|wmv|mpeg|mov|dat|3gp|dmv";
        return videos.contains(suffix);
    }

    /**
     * 验证图片格式
     */
    public static boolean CheckPicFile(String suffix) {
        String videos = "jpg|png|bmp|jpeg|gif|raw|tiff|webp";
        return videos.contains(suffix);
    }

    /**
     * 验证字符串
     */
    public static boolean CheckStringValue(String value){
        return value.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
    }

}
