package com.sunft.chapter02_list;

/**
 * 在单链表和双链表中删除倒数第K个节点
 *
 * 【题目】
 *  分别实现两个函数，一个可以删除单链表中倒数第K个节点的，另一个可以删除双向链表中倒数第K个节点
 * 【要求】
 *  如果链表长度为N，时间复杂度达到O(N)，额外空间复杂度达到O(1)
 */
public class _002RemoveLastKthNode_DoubleList<E extends Comparable<? super E>> {


    public Node<E> removeLastKthNode(Node<E> head, int lastKth) {
        //链表为空，则不用删除
        if (head == null || lastKth < 1) {
            return head;
        }

        Node<E> cur = head;

        //lastKth减一，当前指针指向下一个结点
        while (head != null) {
            lastKth --;
            cur = cur.next;
        }

        //说明需要删除的恰好是第一个结点，头指针指向下一个结点即可
        if (lastKth == 0) {
            head = head.next;
        }

        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            if (cur.next != null) {
                cur.next.prev = cur.next.prev.prev;
            }
        }

        //K大于0的情况说明没有需要删除的结点，直接返回链表即可
        return head;
    }

    /**
     * 链表的节点
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//元素的值
        Node<E> prev;//当前结点的前一个结点
        Node<E> next;//当前结点的后一个节点

        public Node(E e) {
            this.e = e;
        }
    }

}
