package com.yy.outoforder;

public class OutOfOrder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0; // 记录次数
        for (;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();two.start();
            one.join();two.join();
            if (x == 0 && y ==0) {
                System.out.println("第"+i+"次，出现("+x+","+y+")");
                break;
            }
        }
    }
}
