package com.sunft.chapter02_list;

public class _009CopyListWithRand02<E extends Comparable<? super E>> {

    public Node<E> copyListWithRand02(Node<E> head) {

        if (head == null) {
            return null;
        }

        Node<E> cur = head;
        Node<E> next = null;

        //复制并链接每一个结点
        while (cur != null) {
            next = cur.next;
            cur.next = new Node<>(cur.e);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        Node<E> curCopy = null;
        //设置复制结点的rand指针
        while (cur != null) {
            next = cur.next.next;//获取下一个非复制结点
            curCopy = cur.next;//获取复制的结点
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node<E> res = head.next;//获取第一个复制的结点
        cur = head;
        //拆分
        while (cur != null) {
            next = cur.next.next;//获取当前结点的下一个非复制结点
            curCopy = cur.next;//当前结点的下一个结点
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    /**
     * 结点
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//结点数据域
        Node<E> next; //指向下一个结点
        Node<E> rand; //指向链表中的任意一个结点或者指向null

        public Node(E e) {
            this.e = e;
        }
    }

}
