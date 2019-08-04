package com.sunft.chapter02_list;

/**
 * 【题目】
 * 分别实现反转单向链表和反转双向链表的函数，该部分反转单向链表
 * 【要求】
 * 如果链表长度为N，时间复杂度要求为O(N)，额外空间复杂度为O(1)
 */
public class _004ReverseSingleList<E extends Comparable<? super E>> {

    /**
     * 反转单向链表
     * @param head
     * @return
     */
    public Node<E> reverseSingleList(Node<E> head) {

        //链表为空，或者链表只有一个结点直接返回即可
        if (head == null || head.next == null) {
            return head;
        }

        Node<E> pre = null;
        Node<E> next = null;

        while(head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 链表的节点
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//元素的值
        Node<E> next;//当前元素的下一个值

        public Node(E e) {
            this.e = e;
        }
    }

}
