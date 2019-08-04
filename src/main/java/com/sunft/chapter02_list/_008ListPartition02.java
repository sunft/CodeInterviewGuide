package com.sunft.chapter02_list;

/**
 * 对每部分都增加了结点顺序要求，同时时间复杂度仍然为O(N)，额外空间复杂度为O(1)。
 * 既然额外空间复杂度为O(1)，说明实现时只能使用有限的几个变量来完成所有的调整。
 *
 * 进阶解法的具体过程如下：
 * 1、将原链表中的所有结点依次划分进三个链表，三个链表分别为small代表左部分，
 *      equal代表中间部分，big代表右部分。
 *  例如，链表7->9->1->8->5->2->5，pivot = 5。在划分之后，small、equal、big分别为：
 *  small：1->2->null
 *  equal：5->5->null
 *  big：7->9->8->null
 * 2、将small、equal和big三个链表重新串起来即可。
 * 3、整个过程需要特别注意对null结点的判断和处理。
 *
 * @param <E>
 */
public class _008ListPartition02<E extends Comparable<? super E>> {

    /**
     * 使用几个有限的变量实现
     * @param head
     * @param pivot
     * @return
     */
    public Node<E> listPartition01(Node<E> head, E pivot) {

        if (pivot == null) {
            return null;
        }

        Node<E> sH = null;//小的头
        Node<E> sT = null;//小的尾
        Node<E> eH = null;//相等的头
        Node<E> eT = null;//相等的尾
        Node<E> bH = null;//大的头
        Node<E> bT = null;//大的尾
        Node<E> next = null;//保存下一个结点

        //所有结点分进三个链表中
        while (head != null) {
            next = head.next;//保存头结点的下一个结点
            head.next = null;//断开头结点
            if (head.e.compareTo(pivot) < 0) {
                //设置头和尾结点
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.e.compareTo(pivot) == 0) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        //小的和相等的重新连接
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }

        //所有的重新连接
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
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
