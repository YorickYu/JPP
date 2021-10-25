package com.yy.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestLocalCache extends LocalCacheTemplate<String, String> {


    @Override
    protected String CacheBuilder(String key/*, BinaryOperator<String> mergeFunction*/) {
        StringBuilder builder = new StringBuilder(key);
        return builder.toString();
    }
}
