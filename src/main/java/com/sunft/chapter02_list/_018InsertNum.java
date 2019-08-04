package com.sunft.chapter02_list;

/**
 * 向有序的环形单链表中插入新结点
 * 【题目】
 *  一个环形单链表从头结点head开始不降序，同时有最后的结点指回头结点。
 *  给定这样一个环形单链表的头结点head和一个整数num，请生成结点值为num的
 *  新结点，并插入到这个环形链表中，保证调整后的链表仍然有序。
 */
public class _018InsertNum<E extends Comparable<? super E>> {

    public Node<E> insertNum(Node<E> head, E e) {
        Node<E> node = new Node<>(e);
        //头结点为空，结点自己成环
        if (head == null) {
            node.next = node;
            return node;
        }

        Node<E> pre = head;
        Node<E> cur = head.next;

        while (cur != head) {
            if ((pre.e.compareTo(e) <= 0) && (cur.e.compareTo(e) >= 0)) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }

        pre.next = node;
        node.next = cur;
        return (head.e.compareTo(e) < 0) ? head : node;
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
