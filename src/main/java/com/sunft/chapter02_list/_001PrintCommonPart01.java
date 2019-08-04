package com.sunft.chapter02_list;

/**
 * 打印两个有序链表的公共部分
 * 【题目】
 * 给定两个有序链表的头指针head1和head2，打印两个链表的公共部分。
 * 【解答】
 * 本题的难度很低，因为是有序链表，所以从两个链表的头开始进行如下判断
 * 如果head1的值小于head2，则head1往下移动。
 * 如果head2的值小于head1，则head2往下移动。
 * 如果head1的值与head2的值相等，则打印这个值，然后head1与head2都往下移动。
 * head1或head2有任何一个移动到null，整个过程停止。
 */
public class _001PrintCommonPart01<E extends Comparable<? super E>> {


    public void printCommonPart(Node<E> head1, Node<E> head2) {

        while(head1 != null && head2 != null) {
            if (head1.e.compareTo(head2.e) < 0) {
                head1 = head1.next;
            } else if (head1.e.compareTo(head2.e) > 0) {
                head2 = head2.next;
            } else {
                System.out.print(head1.e + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();

    }

    /**
     * 链表的节点类
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
