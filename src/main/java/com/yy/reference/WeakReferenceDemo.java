package com.yy.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        m.get();
    }
}
