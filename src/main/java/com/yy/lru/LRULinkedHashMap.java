package com.yy.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap extends LinkedHashMap {
    private int capacity;

    /**
     * 默认构造方法
     * @param capacity
     * @param access
     *      - true  按访问顺序优先
     *      - false 按添加顺序优先
     */
    public LRULinkedHashMap(int capacity, boolean access) {
        super(capacity, 0.75f, access);
        this.capacity = capacity;
    }

    public LRULinkedHashMap(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > capacity;
    }
}
