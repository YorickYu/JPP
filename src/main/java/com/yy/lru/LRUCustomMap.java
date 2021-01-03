package com.yy.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCustomMap<K, V> {

    private int capacity;
    public Map<K, Node<K, V>> lruMap;
    private DoubleLinkedList<K, V> lruDoubleLinkedList;

    public LRUCustomMap(int capacity) {
        this.capacity = capacity;
        lruMap = new HashMap<>();
        lruDoubleLinkedList = new DoubleLinkedList<>();
    }

    public V get(K key) {
        if (!lruMap.containsKey(key))
            return null;

        Node<K, V> node = lruMap.get(key);
        lruDoubleLinkedList.popNode(node);
        lruDoubleLinkedList.pushNode(node);

        return node.v;
    }

    public void put(K key, V value) {
        if (lruMap.containsKey(key)) {
            Node<K, V> node = lruMap.get(key);
            node.v = value;
            lruMap.put(key, node);
            lruDoubleLinkedList.popNode(node);
            lruDoubleLinkedList.pushNode(node);
        }
        else {
            if (lruMap.size() == capacity) {
                Node<K, V> lastNode = lruDoubleLinkedList.lastNode();
                lruMap.remove(lastNode.k);
                lruDoubleLinkedList.popNode(lastNode);
            }

            // new
            Node<K, V> newNode = new Node<>(key, value);
            lruMap.put(key, newNode);
            lruDoubleLinkedList.pushNode(newNode);
        }

    }

    /**
     * Node 节点类
     * @param <K>
     * @param <V>
     */
    class Node<K, V> {
        K k;
        V v;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.prev = this.next = null;
        }

        public Node(Node<K, V> prev, Node<K, V> next) {
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 双向链表类
     * @param <K>
     * @param <V>
     */
    class DoubleLinkedList<K, V> {
        Node<K, V> HEAD;
        Node<K, V> TAIL;

        public DoubleLinkedList() {
            HEAD = new Node<>();
            TAIL = new Node<>();
            HEAD.next = TAIL;
            TAIL.prev = HEAD;
        }

        // head  <=>   tail
        // head  <=>   node   <=>   tail
        public void pushNode(Node<K, V> node) {
            node.prev = HEAD;
            node.next = HEAD.next;
            HEAD.next.prev = node;
            HEAD.next = node;
        }

        public Node<K, V> lastNode() {
            return TAIL.prev;
        }

        public void popNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }
    }
}
