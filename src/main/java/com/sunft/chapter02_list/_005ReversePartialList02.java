package com.sunft.chapter02_list;

/**
 * 反转部分单向链表
 * 【题目】
 * 给定一个单向链表的头结点head，以及两种整数from和to，在单向链表上把第from个节点到第to个结点这一部分进行反转
 * 例如：
 * 1->2->3->4->5->null, from=2, to=4
 * 调整结果为：1->4->3->3->5->null
 * 再如：
 * 1->2->3->null, from=1, to=3
 * 调整结果为：3->2->1->null
 * 【要求】
 * 1、如果链表长度为N,时间复杂度要求为O(N)，额外空间复杂度要求为O(1)。
 * 2、如果不满足1<=from<=to<=N，则不用调整。
 * 书上的代码
 */
public class _005ReversePartialList02<E extends Comparable<? super E>> {

    public Node<E> reversePart(Node<E> head, Integer from, Integer to) {
        int len = 0;
        Node<E> node1 = head;
        Node<E> fPre = null;
        Node<E> tPos = null;

        while (node1 != null) {
            len ++;
            fPre =  len == from - 1 ? node1 : fPre;
            tPos = len == to + 1 ? node1 : tPos;
            node1 = node1.next;
        }

        if (from > 0 || from < 1 || to > from) {
            return head;
        }

        node1 = fPre == null ? head : fPre.next;
        Node<E> node2 = node1.next;
        node1.next = tPos;
        Node next = null;
        while (node2 != tPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }

        if (fPre != null) {
            fPre.next = node1;
            return head;
        }

        return node1;
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
