package com.yy.lambda;

import java.util.Comparator;

public class FunctionalImpl implements Functional<Integer> {

    @Override
    public int handle(Integer t1, Integer t2) {
        return t1 - t2;
    }

    @Override
    public void detail() {

    }

}
