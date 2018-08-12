package com.gaoyg.monkeyzicloud.util.pubutils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 高yg
 * @date: 2018/7/31 21:26
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:ThreadLocalMap
 */
public class ThreadLocalMap {
    private static final ThreadLocal<Map<String,Object>> THREAD_CONTEXT=new MapThreadLocal();

    /**
     * put key value to ThreadLocal
     * @param key
     * @param value
     */
    public static void put(String key,Object value){
        getThreadContext().put(key,value);
    }

    /**
     * get by key from  ThreadLocal
     * @param key
     * @return
     */
    public static Object get(String key){
       return getThreadContext().get(key);
    }

    /**
     * remove key from ThreadLocal
     * @param key
     * @return
     */
    public static Object  remove(String key){
      return getThreadContext().remove(key);
    }
    private static class MapThreadLocal extends ThreadLocal<Map<String,Object>>{
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String,Object>(8){
                private static final long serialVersionUID = 3637958959138295593L;

                @Override
                public Object put(String key, Object value) {
                    return super.put(key, value);
                }
            };
        }
    }

    private static Map<String,Object> getThreadContext(){
        return THREAD_CONTEXT.get();
    }
}
