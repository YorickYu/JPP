package com.yy.hashmap;

import java.util.HashMap;

public class TailInsert {
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        boolean hashNext() {
            return next != null;
        }

    }

    public static void main(String[] args) {

        Node tab = new Node(0,0,null); // node链表
        Node p = tab;
        for (int i = 1; i <= 10; i++) {
            Node tail = new Node(i, i, null); // 最后一个节点

            if (p.next == null) {
                p.next = tail;
            }else { // 尾插
                Node e;
                while ((e = p.next) != null) {
                    if (e.next == null) {
                        e.next = tail;
                        break;
                    }
                    p = e;
                };
            }
        }
        System.out.println(tab);


        ////// 仿resize
        Node loHead = null, loTail = null;
        Node hiHead = null, hiTail = null;
        Node next;
        Node e = tab;
        do {
            next = e.next;
            if ((int)e.value % 2 == 0) {
                if (loTail == null)
                    loHead = e;
                else
                    loTail.next = e;
                loTail = e;
            }
            if ((int)e.value % 2 == 1) {
                if (hiTail == null)
                    hiHead = e;
                else
                    hiTail.next = e;
                hiTail = e;
            }

        } while ((e = next) != null);
        System.out.println(loHead);
        System.out.println(hiHead);

    }
}
