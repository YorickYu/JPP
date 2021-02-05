package com.yy.guava;

import cn.hutool.json.JSONUtil;

public class TestReturnPrototype {
    String ref;

    public TestReturnPrototype(String ref) {
        this.ref = ref;
    }

    public TestReturnPrototype(TestReturnPrototype prototype) {
        this.ref = prototype.ref;
    }

    public TestReturnPrototype test() {
        return new TestReturnPrototype(this) {
            public String print(String str) {
                System.out.println("print" + str);
                return str;
            }
        };
    }

    public static void main(String[] args) {
        TestReturnPrototype prototype = new TestReturnPrototype("1") {
            public String print(String str) {
                System.out.println("print" + str);
                return str;
            }
        };

        TestReturnPrototype test = prototype.test();
    }
}
