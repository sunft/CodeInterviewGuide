package com.sunft.chapter02_list;

/**
 * 将单链表的没K个结点之间逆序
 * 【题目】
 * 给定一个单链表的头结点head，实现一个调整单链表的函数，使得每K个结点之间逆序，
 * 如果最后不够K个结点一组，则不调整最后几个结点。
 * 例如：
 * 链表：1->2->3->4->5->6->7->8->null, K=3
 * 调整后为：3->2->1->6->5->4->7->8->null。其中7、8不调整，因为不够一组。
 * 方式二：直接在链表中调整
 *
 * @param <E>
 */
public class _012ReverseKNodes02<E extends Comparable<? super E>> {

    /**
     * 直接在链表中调整
     * @param head 头结点
     * @param k 调整的组的大小
     * @return
     */
    public Node<E> reverseKNodes2(Node<E> head, int k) {
        if (k < 2) {
            return head;
        }
        Node<E> cur = head;
        Node<E> start = null;
        Node<E> pre = null;
        Node<E> next = null;
        int count = 1;//计数器
        while (cur != null) {
            next = cur.next;
            if (count == k) {
                start = (pre == null) ? head : pre.next;
                head = (pre == null) ? cur : head;//第一组时头部需要调整，后面不用调整
                resign2(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count ++;
            cur = next;
        }
        return head;
    }

    /**
     * 对链表进行反转调整
     * @param left
     * @param start
     * @param end
     * @param right
     */
    private void resign2(Node<E> left, Node<E> start, Node<E> end, Node<E> right) {
        Node<E> pre = start;
        Node<E> cur = start.next;
        Node<E> next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;
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
