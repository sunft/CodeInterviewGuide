package com.sunft.chapter02_list;

import java.util.Stack;

/**
 * 两个单链表生成相加链表
 * 【题目】
 *  假设链表中每一个结点的值都在0~9之间，那么链表整体就可以代表一个整数。
 *  例如：9->3->7，可以代表整数937。
 *
 *  给定两个这种链表的头结点head1和head2，请生成代表两个整数值相加值的结果链表。
 *  例如：链表1为9->3->7，链表2为6->3，最后生成新的结果链表为1->0->0->0。
 *
 *  方法一：利用栈结构求解。
 *
 */
public class _010AddList01 {

    public Node<Integer> addList(Node<Integer> head1, Node<Integer> head2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (head1 != null) {
            s1.push(head1.e);
            head1 = head1.next;
        }

        while (head2 != null) {
            s2.push(head2.e);
            head2 = head2.next;
        }

        int ca = 0;//链表中的进位
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node<Integer> node = null;
        Node<Integer> pre = null;

        while (!s1.isEmpty() || !s2.isEmpty()) {
            n1 = s1.isEmpty() ? 0 : s1.pop();//获取第一个栈中的数字
            n2 = s2.isEmpty() ? 0 : s2.pop();//获取第二个栈中的数字
            n = n1 + n2 + ca;//两个数相加并且加上进位
            pre = node;
            node = new Node<>(n % 10);
            node.next = pre;
            ca = n / 10;
        }

        if (ca == 1) {
            pre = node;
            node = new Node<>(1);
            node.next = pre;
        }
        return node;
    }

    /**
     * 结点
     * @param <Integer>
     */
    private static class Node<Integer> {
        Integer e;//结点数据域
        Node<Integer> next; //指向下一个结点

        public Node(Integer e) {
            this.e = e;
        }
    }

}
