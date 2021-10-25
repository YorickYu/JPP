package com.yy.cache;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;

public abstract class LocalCacheTemplate<K, V> {

    LoadingCache<K, V> loadingCache;

    BinaryOperator bo;

    public LocalCacheTemplate() {
        loadingCache = CacheBuilder.newBuilder()
                .maximumSize(16)
                .expireAfterAccess(60, TimeUnit.SECONDS)
                .build(new CacheLoader<K, V>() {

            @Override
            public V load(K key) throws Exception {
                return (V) CacheBuilder(key/*, bo*/);
            }
        });
    }

    protected abstract V CacheBuilder(K key/*, BinaryOperator<String> mergeFunction*/);
}
