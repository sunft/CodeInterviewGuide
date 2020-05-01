package com.sunft.chapter09_other;

import java.util.HashMap;

/**
 * 设计有setAll功能的哈希表
 * 【题目】
 * 哈希表常见的三个操作是put、 get和containsKey， 而且这三个操
 * 作的时间复杂度为O(1)。 现在想加一个setAll功能， 就是把所有记录
 * 的value都设成统一的值。 请设计并实现这种有setAll功能的哈希表，
 * 并且put、 get、 containsKey和setAll四个操作的时间复杂度都为O(1)。
 */
public class _08MyHashMap<K, V> {

    private HashMap<K, MyValue<V>> baseMap;
    private long time;
    private MyValue<V> setAll;

    public _08MyHashMap() {
        this.baseMap = new HashMap<>();
        this.time = 0;
        this.setAll = new MyValue<>(null, -1);
    }

    public boolean containsKey(K key) {
        return this.baseMap.containsKey(key);
    }

    public void put(K key, V value) {
        this.baseMap.put(key, new MyValue<>(value, this.time++));
    }

    public void setAll(V value) {
        this.setAll = new MyValue<>(value, this.time++);
    }

    public V get(K key) {
        if (this.containsKey(key)) {
            if (this.baseMap.get(key).getTime() > this.setAll.getTime()) {
                return this.baseMap.get(key).getValue();
            } else {
                return this.setAll.getValue();
            }
        } else {
            return null;
        }
    }

    private static class MyValue<V> {
        private V value;
        private long time;

        public MyValue(V value, long time) {
            this.value = value;
            this.time = time;
        }

        public V getValue() {
            return this.value;
        }

        public long getTime() {
            return this.time;
        }
    }

}
