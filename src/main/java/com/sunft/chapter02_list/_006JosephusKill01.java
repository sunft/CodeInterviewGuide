package com.sunft.chapter02_list;

/**
 * 环形单链表的约瑟夫问题
 * 【题目】
 * 输入：一个环形单向链表的头结点head和报数的值n
 * 返回：最后生存下来的结点，且这个结点自己组成环形单向链表，其他结点都删掉
 */
public class _006JosephusKill01<E extends Comparable<? super E>> {

    public Node<E> josephusKill01(Node<E> head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }

        //找到头结点的上一个结点
        Node<E> pre = head;
        while (pre.next != head) {
            pre = pre.next;
        }

        int count = 0;
        while (head != pre) {
            if (++count == m) {
                pre.next = head.next;
                count = 0;
            } else {
                pre = pre.next;
            }
            head = pre.next;
        }

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
