package com.yy.enums;

import lombok.Getter;

public class CoopEnum {

    public enum SEASON {
        SPRING(1, "春天"),
        SUMMER(2, "夏天"),
        AUTUMN(3, "秋天"),
        WINTER(4, "冬天");

        private Integer key;
        private String value;

        SEASON(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        // 提供getter方法
        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    @Getter
    public enum WEEK {
        MONDAY(1, "周一"),
        TUESDAY(2, "周二");

        private Integer key;
        private String value;

        WEEK(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 单例的一种写法
     * 解决线程同步问题，可以防止反序列化
     */
    public enum SingletonEnum {
        INSTANCE;
        public void run() {
            System.out.println("run");
        }
    }
}
