package com.yy.concurrentHashMap;

public class logn {
    public static void main(String[] args) {
        int up = up(13);
            System.out.println("up = " + up);
        int down = down(17);
            System.out.println("down = " + down);
    }


    public static int up(int m) {
        m |= (m >>> 1);
        m |= (m >>> 2);
        m |= (m >>> 4);
        m |= (m >>> 8);
        m |= (m >>> 16);
        return  m + 1;
    }

    public static int down(int m) {
        m |= (m >>> 1);
        m |= (m >>> 2);
        m |= (m >>> 4);
        m |= (m >>> 8);
        m |= (m >>> 16);
        return  m - (m >>> 1);
    }

}
