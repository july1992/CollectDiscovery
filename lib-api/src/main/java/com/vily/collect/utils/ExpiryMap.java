package com.vily.collect.utils;

import lombok.Data;

import java.text.ParseException;
import java.util.*;

/**
 * 缓存工具类
 * @param <K>
 * @param <V>
 */
@Data
public class ExpiryMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 1L;

    /**
     * 默认15分钟
     */
    private long EXPIRY = 1000 * 60 * 15;

    private HashMap<K, Long> expiryMap = new HashMap<>();

    private static ExpiryMap<String, String> instance;

    public static synchronized ExpiryMap<String, String> getStringInstance(){
        if(instance == null){
            instance = new ExpiryMap<>();
        }

        return instance;
    }

    public boolean containsKey(Object key) {
        return !checkExpiry(key, true) && super.containsKey(key);
    }

    public V put(K key, V value) {
        expiryMap.put(key, System.currentTimeMillis() + EXPIRY);
        return super.put(key, value);
    }
    /**
     * @param key
     * @param value
     * @param expiryTime 键值对有效期 毫秒
     * @return
     */
    public V put(K key, V value, long expiryTime) {
        expiryMap.put(key, System.currentTimeMillis() + expiryTime);
        return super.put(key, value);
    }
    public int size() {
        return entrySet().size();
    }

    public boolean isEmpty() {
        return entrySet().size() == 0;
    }

    public boolean containsValue(Object value) {
        if (value == null) return Boolean.FALSE;
        Set<Entry<K, V>> set = super.entrySet();
        Iterator<Entry<K, V>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if(value.equals(entry.getValue())){
                if(checkExpiry(entry.getKey(), false)) {
                    iterator.remove();
                    return Boolean.FALSE;
                }else return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    public Collection<V> values() {

        Collection<V> values = super.values();

        if(values == null || values.size() < 1) return values;

        Iterator<V> iterator = values.iterator();

        while (iterator.hasNext()) {
            V next = iterator.next();
            if(!containsValue(next)) iterator.remove();
        }
        return values;
    }
    public V get(Object key) {
        if (key == null)
            return null;
        if(checkExpiry(key, true))
            return null;
        return super.get(key);
    }
    /**
     *
     * @Description: 是否过期
     * @param key
     * @return null:不存在或key为null -1:过期  存在且没过期返回value 因为过期的不是实时删除，所以稍微有点作用
     */
    public Object isInvalid(Object key) {
        if (key == null)
            return null;
        if(!expiryMap.containsKey(key)){
            return null;
        }
        long expiryTime = expiryMap.get(key);

        boolean flag = System.currentTimeMillis() > expiryTime;

        if(flag){
            super.remove(key);
            expiryMap.remove(key);
            return -1;
        }
        return super.get(key);
    }
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> e : m.entrySet())
            expiryMap.put(e.getKey(), System.currentTimeMillis() + EXPIRY);
        super.putAll(m);
    }
    public Set<Entry<K,V>> entrySet() {
        Set<Entry<K, V>> set = super.entrySet();
        Iterator<Entry<K, V>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if(checkExpiry(entry.getKey(), false)) iterator.remove();
        }

        return set;
    }
    /**
     *
     * @Description: 是否过期
     * @author: qd-ankang
     * @param isRemoveSuper true super删除
     * @return
     */
    private boolean checkExpiry(Object key, boolean isRemoveSuper){

        if(!expiryMap.containsKey(key)){
            return Boolean.FALSE;
        }
        long expiryTime = expiryMap.get(key);

        boolean flag = System.currentTimeMillis() > expiryTime;

        if(flag){
            if(isRemoveSuper)
                super.remove(key);
            expiryMap.remove(key);
        }
        return flag;
    }

    public static void main(String[] args) throws InterruptedException, ParseException {

//        ExpiryMap<String, String> map = new ExpiryMap<>();

        ExpiryMap<String, String> map = ExpiryMap.getStringInstance();
        map.put("test", "giousa");
        map.put("test1", "ankang11");
        map.put("test2", "ankang22", 1000*60);
//        System.out.println("test1" + map.get("test"));
//        Thread.sleep(1000);
//        System.out.println("isInvalid:" + map.isInvalid("test"));
        System.out.println("size:" + map.size());
        System.out.println("size:" + ((HashMap<String, String>)map).size());
//        for (Map.Entry<String, String> m : map.entrySet()) {
//            System.out.println("isInvalid:" + map.isInvalid(m.getKey()));
//            map.containsKey(m.getKey());
//            System.out.println("key:" + m.getKey() + "     value:" + m.getValue());
//        }
//        System.out.println("test1" + map.get("test"));
        long startTime = System.currentTimeMillis();
        ExpiryMap<String, String> stringInstance = ExpiryMap.getStringInstance();
        for (int i = 0; i < 1000000000; i++) {
            String test = stringInstance.get("test");
            String test2 = stringInstance.get("test2");

            System.out.println("test = " + test);
            System.out.println("test2 = " + test2);
            System.out.println("间隔时间 = " + ((System.currentTimeMillis() - startTime))/1000);
            System.out.println("time = "+ DateUtil.longToString(System.currentTimeMillis(),null));
            System.out.println("-------------------------");
            Thread.sleep(3000);

        }

    }
}