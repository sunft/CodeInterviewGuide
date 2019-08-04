package com.sunft.chapter02_list;

/**
 * 一种怪异的结点删除方式
 * 【题目】
 * 链表结点的类型为int型，给定一个链表的结点node，但不给定整个链表的头结点。
 * 如何在链表中删除node？请实现这个函数，并分析这么会出现哪些问题。
 * 要求：时间复杂度为O(1)。
 *
 * @param <E>
 */
public class _017RemoveNodeWired<E extends Comparable<? super E>> {

    public void removeNodeWired(Node<E> node) {
        if (node == null) {
            return;
        }

        Node<E> next = node.next;
        if (next == null) {
            throw new RuntimeException("can not remove last node.");
        }

        node.e = next.e;
        node.next = next.next;
    }

    /**
     * 结点
     *
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//结点数据域
        Node<E> next; //下一个结点

        public Node(E e) {
            this.e = e;
        }
    }

}
