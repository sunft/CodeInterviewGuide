package com.sunft.chapter02_list;

/**
 * 单链表的选择排序
 * 【题目】
 *  给定一个无序单链表的头结点head，实现单链表的选择排序。
 *  要求：额外空间复杂度为O(1)
 */
public class _016SelectSortOfSingleList<E extends Comparable<? super E>> {

    /**
     * 单链表的选择排序
     * @param head
     * @return
     */
    public Node<E> selectionSort(Node<E> head) {
        Node<E> tail = null;//排序部分尾部
        Node<E> cur = head;//未排序部分的头部
        Node<E> smallPre = null;//最小结点的前一个结点
        Node<E> small = null; //最小的结点

        while (cur != null) {
            small = cur;
            smallPre = getSmallestPreNode(cur);
            if (smallPre != null) {
                small = smallPre.next;
                smallPre.next = small.next;
            }
            cur = cur == small ? cur.next : cur;
            if (tail == null) {
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;
        }

        return head;
    }

    /**
     * 获取最小结点的前一个结点
     * @param head
     * @return
     */
    private Node<E> getSmallestPreNode(Node<E> head) {
        Node<E> smallPre = null;
        Node<E> small = head;
        Node<E> pre = head;
        Node<E> cur = head.next;
        while (cur != null) {
            if (cur.e.compareTo(small.e) < 0) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }

    /**
     * 结点
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
