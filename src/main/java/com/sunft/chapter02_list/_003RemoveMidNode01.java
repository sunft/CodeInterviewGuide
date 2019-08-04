package com.sunft.chapter02_list;

/**
 * 删除链表的中间节点和a/b处的节点
 * 【题目】
 *  给定链表的头结点head，实现删除链表的中间节点的函数
 */
public class _003RemoveMidNode01<E extends Comparable<? super E>> {

    public Node<E> removeMidNode(Node<E> head) {
        //链表长度为null或者链表长度为1的情况
        if (head == null || head.next == null) {
            return head;
        }

        //链表长度为2的情况，删除头节点
        if (head.next.next == null) {
            head = head.next;
            return head;
        }

        //链表长度大于2的情况
        Node<E> pre = head;
        Node<E> cur = head.next.next;

        //链表长度每增加2，被删除的结点需要往后挪动1
        if (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }
        //指向下一个节点，中间的节点被删除
        pre = pre.next.next;

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
