package com.vily.collect.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

/**
 * 缓存工具类
 */
public class CacheUtils {

    private static CacheManager cacheManager;

    private static final String SYS_CACHE = "sysCache";
    
	private static final String PATH = "/shiro-ehcache.xml";
	
	private static URL url;


    private final static Byte[] locks = new Byte[0];

    /**
     * 	获取SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * 写入SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    private static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * @param key
     * @return
     */
    private static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    private static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * 写入缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    private static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName
     * @param key
     */
    private static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    private static void removeAll(String cacheName) {
        getCache(cacheName).removeAll();
    }

    /**
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName) {
        if (cacheManager == null) {
            synchronized (locks) {
                if (cacheManager == null) {
                    cacheManager = getCacheManagerInstance();
                }
            }
        }
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }
    
    /**
	 * [获取缓存管理类实例,双重锁确保缓存管理类单例]
	 * 
	 */
	private static CacheManager getCacheManagerInstance() {
		if (cacheManager == null) {
			synchronized (CacheUtils.class) {
				if (cacheManager == null) {
					url = CacheUtils.class.getResource(PATH);
					cacheManager = CacheManager.create(url);
				}
			}
		}
		return cacheManager;
	}

    private static CacheManager getCacheManager() {
        return cacheManager;
    }


    public static synchronized boolean  getSetverifyCode(String key, String value){
        String val = (String) CacheUtils.get(key);
        if (val == null) {
            value=value+"|"+ System.currentTimeMillis();
            put(key ,value);
            String val2 = (String) get(key);
            System.out.println(val2);
            return true;
        }else{
            String[] s=val.split("\\|");
            if(s.length==2){
                if(System.currentTimeMillis()- Long.parseLong(s[1])>60000){//时间间隔>1一分钟，可以重新发送短信
                    value=value+"|"+ System.currentTimeMillis();
                    put(key,value);
                    return true;
                }
            }
        }
        return false;
    }

    public static synchronized boolean  getSetverifyCode2(String key, String value){

        ExpiryMap<String, String> stringInstance = ExpiryMap.getStringInstance();
        String val = stringInstance.get(key);

        if (val == null) {
            value = value+"|"+ System.currentTimeMillis();
            stringInstance.put(key,value);
            return true;
        }else{
            String[] s= val.split("\\|");
            if(s.length==2){
                if(System.currentTimeMillis()- Long.parseLong(s[1])>60000){//时间间隔>1一分钟，可以重新发送短信
                    value=value+"|"+ System.currentTimeMillis();
                    stringInstance.put(key,value);
                    return true;
                }
            }
        }
        return false;
    }

    public static String getKey(String key) {
        ExpiryMap<String, String> stringInstance = ExpiryMap.getStringInstance();
        String val = stringInstance.get(key);
        return val;
    }



}
