package com.sunft.chapter02_list;

/**
 * 删除无序单链表中值重复出现的结点
 * 【题目】
 * 给定一个无序单链表的头结点head，删除其中值重复出现的结点。
 * 例如：1->2->3->3->4->4->2->1->1->null，删除值重复的结点之后为1->2->3->4->null。
 * 方法二：第一次删除与第一个结点相等的数据，第二次删除与第二个结点相等的数据，以此类推。
 */
public class _13RemoveDuplicateNode02<E extends Comparable<? super E>> {

    /**
     * 类似于选择排序法的思路实现，自己写的代码
     * @param head
     */
    public void removeRep01(Node<E> head) {
        if (head == null) {
            return;
        }

        Node<E> cur = head;
        while (cur != null) {
            Node<E> pre = cur;
            Node<E> inner = cur.next;
            while (inner != null) {
                if (inner.e.compareTo(cur.e) == 0) {
                    pre.next = inner.next;
                    inner = pre.next;
                    pre = inner;
                } else {
                    inner = inner.next;
                    pre = pre.next;
                }
            }
            cur = cur.next;
        }

    }

    /**
     * 书上的代码
     * @param head 头结点
     */
    public void removeRep02(Node<E> head) {
        Node<E> cur = head;
        Node<E> pre = null;
        Node<E> next = null;

        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
                if (cur.e == next.e) {
                    pre.next = next.next;
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }
    }

    /**
     * 结点
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//结点数据域
        Node<E> next; //指向下一个结点

        public Node(E e) {
            this.e = e;
        }
    }

}
