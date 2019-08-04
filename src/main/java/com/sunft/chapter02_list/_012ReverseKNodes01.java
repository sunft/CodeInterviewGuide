package com.sunft.chapter02_list;

import java.util.Stack;

/**
 * 将单链表的没K个结点之间逆序
 * 【题目】
 * 给定一个单链表的头结点head，实现一个调整单链表的函数，使得每K个结点之间逆序，
 * 如果最后不够K个结点一组，则不调整最后几个结点。
 * 例如：
 * 链表：1->2->3->4->5->6->7->8->null, K=3
 * 调整后为：3->2->1->6->5->4->7->8->null。其中7、8不调整，因为不够一组。
 * 方式一：使用栈去实现
 *
 * @param <E>
 */
public class _012ReverseKNodes01<E extends Comparable<? super E>> {

    public Node<E> reverseKNodes1(Node<E> head, int k) {
        //如果k为1，则不用调整
        if (k < 2) {
            return head;
        }
        Stack<Node<E>> stack = new Stack<>();
        Node<E> newHead = head;
        Node<E> cur = head;
        Node<E> pre = null;
        Node<E> next = null;
        while (cur != null) {
            next = cur.next;
            stack.push(cur);//入栈
            //判断栈是否满
            if (stack.size() == k) {
                pre = resign1(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    /**
     * 反转栈中链表的内容
     * @param stack 栈
     * @param left 前一个结点
     * @param right 后一个节点
     * @return
     */
    private Node<E> resign1(Stack<Node<E>> stack, Node<E> left, Node<E> right) {
        Node<E> cur = stack.pop();
        if (left != null) {
            left.next = cur;
        }
        Node<E> next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
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
