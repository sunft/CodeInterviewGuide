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
 * 自己写的代码
 */
public class _005ReversePartialList01<E extends Comparable<? super E>> {

    public Node<E> reversePart(Node<E> head, Integer from, Integer to) {
        //以下情况：直接返回即可
        if (head == null || (from == null) || (to == null) || (from >= to)) {
            return head;
        }

        int len = 0;//记录链表长度
        Node<E> cur = head;
        Node<E> fPre = null;
        Node<E> tPos = null;
        //遍历链表，获取长度，并找到需要转换区间的前一个和后一个结点
        while (cur != null) {
            len ++;
            if (len + 1 == from) {
                fPre = cur;
            }
            if (len - 1 == to) {
                tPos = cur;
            }
            cur = cur.next;
        }

        if ((1 <= from && from <= len) && (to <= len) && (fPre != null)) {
            Node<E> pre = fPre.next;
            Node<E> target = pre.next;
            Node<E> next = target.next;
            Node<E> first = fPre.next;
            while (target != tPos) {
                target.next = pre;
                pre = target;
                target = next;
                next = next.next;
            }
            fPre = target;
            first.next = tPos;
        }

        return head;

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
