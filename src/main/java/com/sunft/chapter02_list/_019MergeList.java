package com.sunft.chapter02_list;

/**
 * 合并两个有序的单链表
 * 【题目】
 * 给定两个有序单链表的头结点head1和head2，请合并两个有序链表，合并后的单链表依然有序，
 * 并返回合并后链表的头结点。
 * 例如：
 * 0-> 2-> 3-> 7-> null
 * 1-> 3-> 5-> 7-> 9 -> null
 * <p>
 * 合并后的链表为：0->1->2->3->3->5->7->7->9->null
 *
 * @param <E>
 */
public class _019MergeList<E extends Comparable<? super E>> {

    /**
     * 合并的方法
     * @param head1
     * @param head2
     * @return
     */
    public Node<E> merge(Node<E> head1, Node<E> head2) {
        //判断两个链表是否空
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }

        //头结点指向head1和head2较小的元素
        Node<E> head = (head1.e.compareTo(head2.e) < 0) ? head1 : head2;

        //cur1指向较小的头结点
        Node<E> cur1 = head == head1 ? head1 : head2;
        //cur2指向较大的头结点
        Node<E> cur2 = head == head1 ? head2 : head1;

        //辅助结点
        Node<E> pre = null;
        Node<E> next = null;

        //遍历链表进行连接
        while (cur1 != null && cur2 != null) {
            //第一步肯定走这一步
            if (cur1.e.compareTo(cur2.e) <= 0) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;

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
