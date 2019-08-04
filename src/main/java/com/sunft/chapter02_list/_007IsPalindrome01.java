package com.sunft.chapter02_list;

import java.util.Stack;

/**
 * 判断一个链表是否为回文结构
 * 【题目】
 * 给定一个链表的头结点head，请判断该链表是否为回文结构。
 * 例如：
 * 1->2->1，返回true
 * 1->2->2->1，返回true
 * 1->2->3，返回false
 * 实现思路：
 *  (1)将全部元素入栈
 *  (2)只入栈一半的元素
 *
 * @param <E>
 */
public class _007IsPalindrome01<E extends Comparable<? super E>> {

    /**
     * 将全部元素入栈
     * @param head
     * @return
     */
    public boolean isPalindrome(Node<E> head) {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.e.compareTo(stack.pop().e) != 0) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    /**
     * 把整个链表的右半部分压入栈中
     * @param head
     * @return
     */
    public boolean isPalindrome2(Node<E> head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node<E> right = head.next;
        Node<E> cur = head;

        //没增加两个元素，右半部分的起点向后移一位
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }

        Stack<Node<E>> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        //栈中的元素只有链表的一般，比较的时候以栈为基准
        while (!stack.isEmpty()) {
            if (head.e.compareTo(stack.pop().e) != 0) {
                return false;
            }
            head = head.next;
        }
        return true;
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
