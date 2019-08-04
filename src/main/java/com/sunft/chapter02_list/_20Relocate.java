package com.sunft.chapter02_list;

/**
 * 按照左右半区的方式重新组合单链表
 * 【题目】
 * 给定一个单链表的头部结点head，链表长度为N，如果N为偶数，那么前N/2个结点
 * 算作左半区，后N/2个结点算作右半区：如果N为奇数，那么前N/2个结点算作左半区，
 * 后N/2+1个结点算作右半区。左半区从左到右依次记为L1->L2->...，右半区从左到右依次
 * 记为R1->R2...，请将单链表调整成L1->R1->L1->R2->...的形式。
 * 例如：
 * 1->null，调整为1->null。
 * 1->2->null，调整为1->2->null。
 * 1->2->3->null，调整为1->2->3->null。
 * 1->2->3->4->null，调整为1->3->2->4->null。
 * 1->2->3->4->5->null，调整为1->3->2->4->5->null。
 * 1->2->3->4->5->6->null，调整为1->4->2->2->3->6->null。
 * <p>
 * 【解答】
 * 假设链表的长度为N，直接给出时间复杂度为O(N)、额外空间复杂度为O(1)的方法。
 *
 * @param <E>
 */
public class _20Relocate<E extends Comparable<? super E>> {

    /**
     * 重新组合链表
     * @param head
     */
    public void relocate(Node<E> head) {
        if (head == null || head.next == null) {
            return;
        }

        Node<E> mid = head;
        Node<E> right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);
    }

    /**
     * 合并左边的链表和右边的链表
     *
     * @param left
     * @param right
     */
    private void mergeLR(Node<E> left, Node<E> right) {
        Node<E> next = null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
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
