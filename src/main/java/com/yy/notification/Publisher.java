package com.yy.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @className: Publisher
 * @description:
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public class Publisher<T> {
    private final List<Consumer<T>> observers = new ArrayList<>();
    public void record(Consumer<T> consumer) {
        observers.add(consumer);
    }
    public void publish(T msg) {
        observers.forEach(c -> {
            c.accept(msg);
        });
    }
}
