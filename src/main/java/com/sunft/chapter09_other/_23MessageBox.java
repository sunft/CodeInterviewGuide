package com.sunft.chapter09_other;

import java.util.HashMap;
import java.util.Map;

/**
 * 一种消息接收并打印的结构设计
 * <p>
 * 【题目】
 * 消息流吐出2， 一种结构接收而不打印2， 因为1还没出现。
 * 消息流吐出1， 一种结构接收1， 并且打印： 1， 2。
 * 消息流吐出4， 一种结构接收而不打印4， 因为3还没出现。
 * 消息流吐出5， 一种结构接收而不打印5， 因为3还没出现。
 * 消息流吐出7， 一种结构接收而不打印7， 因为3还没出现。
 * 消息流吐出3， 一种结构接收3， 并且打印： 3， 4， 5。
 * 消息流吐出9， 一种结构接收而不打印9， 因为6还没出现。
 * 消息流吐出8， 一种结构接收而不打印8， 因为6还没出现。
 * 消息流吐出6， 一种结构接收6， 并且打印： 6， 7， 8， 9。
 * <p>
 * 已知一个消息流会不断地吐出整数1～N， 但不一定按照顺序吐
 * 出。 如果上次打印的数为i， 那么当i+1出现时， 请打印i+1及其之后接
 * 收过的并且连续的所有数， 直到1～N全部接收并打印完， 请设计这
 * 种接收并打印的结构。
 * <p>
 * 【要求】
 * 消息流最终会吐出全部的1～N， 当然最终也会打印完所有的1
 * ～N， 要求接收和打印1～N的整个过程， 时间复杂度为O(N)。
 */
public class _23MessageBox {

    private Map<Integer, Node> headMap;
    private Map<Integer, Node> tailMap;
    private int lastPrint;

    public _23MessageBox() {
        headMap = new HashMap<>();
        tailMap = new HashMap<>();
        lastPrint = 0;
    }

    public void receive(int num) {
        if (num < 1) {
            return;
        }
        Node cur = new Node(num);
        headMap.put(num, cur);
        tailMap.put(num, cur);
        if (tailMap.containsKey(num - 1)) {
            tailMap.get(num - 1).next = cur;
            tailMap.remove(num - 1);
            headMap.remove(num);
        }
        if (headMap.containsKey(num + 1)) {
            cur.next = headMap.get(num + 1);
            tailMap.remove(num);
            headMap.remove(num + 1);
        }
        if (headMap.containsKey(lastPrint + 1)) {
            print();
        }
    }

    private void print() {
        Node node = headMap.get(++lastPrint);
        headMap.remove(lastPrint);
        while (node != null) {
            System.out.println(node.num + " ");
            node = node.next;
            lastPrint++;
        }
        tailMap.remove(--lastPrint);
        System.out.println();
    }

    private static class Node {
        public int num;
        public Node next;

        public Node(int num) {
            this.num = num;
        }
    }

}
