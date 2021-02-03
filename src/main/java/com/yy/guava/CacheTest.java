package com.yy.guava;



import com.google.common.cache.*;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;



public class CacheTest {

    private CacheLoader cacheLoader = new CacheLoader<String, String>() {
        // 如果找不到元素，会调用这里
        @Override
        public String load(String key) {
            System.out.println("没有找到 = " + key);
            /**
             * 模拟：去数据库查询/远程调用
             */
            String value = getValue();
            loadingCache.put(key, value);
            return value;
        }
    };

    private LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(1000) // 容量
            .expireAfterWrite(3, TimeUnit.SECONDS) // 过期时间
            .removalListener(new MyRemovalListener()) // 失效监听器
            .build(cacheLoader); //

    private String getValue() {
        int i = new Random().nextInt(500);
        return String.valueOf(i);
    }

    public static void main(String[] args) throws ExecutionException {
        CacheTest test = new CacheTest();
        // 初次获取
        String id1 = test.loadingCache.get("id1");
        System.out.println("id1 = " + id1);

        String id2 = test.loadingCache.get("id2");
        System.out.println("id2 = " + id2);

        String id3 = test.loadingCache.get("id3");
        System.out.println("id3 = " + id3);

        // 从缓存中获取
        String id11 = test.loadingCache.get("id1");
        System.out.println("id11 = " + id11);

        String id22 = test.loadingCache.get("id2");
        System.out.println("id22 = " + id22);

        // 手动过期
        test.loadingCache.invalidate("id3");

        String id33 = test.loadingCache.get("id3");
        System.out.println("id33 = " + id33);
    }

}

class TestRemovalListener implements RemovalListener<String, String> {

    @Override
    public void onRemoval(RemovalNotification<String, String> notification) {
        String reason = String.format("key=%s,value=%s,reason=%s", notification.getKey(), notification.getValue(), notification.getCause());
        System.out.println("reason:" + reason);
    }
}