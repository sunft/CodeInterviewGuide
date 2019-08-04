package com.sunft.chapter02_list;

/**
 * 【题目】
 * 分别实现反转单向链表和反转双向链表的函数，该部分反转双向链表
 * 【要求】
 * 如果链表长度为N，时间复杂度要求为O(N)，额外空间复杂度为O(1)
 */
public class _004ReverseDoubleList<E extends Comparable<? super E>> {

    /**
     * 反转双向链表
     * @param head
     * @return
     */
    public DoubleNode<E> reverseDoubleList(DoubleNode<E> head) {
        DoubleNode<E> pre = null;
        DoubleNode<E> next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }

        return pre;
    }

    /**
     * 链表的节点
     * @param <E>
     */
    private static class DoubleNode<E extends Comparable<? super E>> {
        E e;//元素的值
        DoubleNode<E> pre; //当前结点的前一个结点
        DoubleNode<E> next;//当前结点的后一个结点

        public DoubleNode(E e) {
            this.e = e;
        }
    }


}
