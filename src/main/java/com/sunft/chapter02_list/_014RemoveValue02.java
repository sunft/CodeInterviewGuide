package com.sunft.chapter02_list;

import java.util.Stack;

/**
 * 在单链表中删除指定值的结点
 * 【题目】
 *  给定一个链表的头结点head和一个整数num，请实现函数将值为num的值全部删除。
 *  例如，链表为1->2->3->4->null，num=3，链表调整后为：1->2->4->null。
 * @param <E>
 */
public class _014RemoveValue02<E extends Comparable<? super E>> {

    /**
     * 使用栈的方法实现
     * @param head 头结点
     * @param e 被比较元素
     * @return
     */
    public Node<E> removeValue01(Node<E> head, E e) {
        Stack<Node<E>> stack = new Stack<>();
        while (head != null) {
            if (head.e.compareTo(e) != 0) {
                stack.push(head);
            }
            head = head.next;
        }

        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head =stack.pop();
        }
        return head;
    }

    /**
     * 思路：首先从链表头开始，找到第一个值不等于num的结点，作为新的头结点，这个结点是肯定不用删除的，计为newHead。
     * 继续往后遍历，假设当前结点为cur，如果cur结点等于e，就将cur结点删除，删除的方式是将之前最近一个不等于num的结点pre
     * 连接到cur的下一个结点，即pre.next = cur.next；如果cur结点值不等于num，就令pre=cur，即更新最近一个值不等于num的结点。
     * @param head 头结点
     * @param e 被比较元素
     * @return
     */
    public Node<E> removeValue02(Node<E> head, E e) {
        /**
         * 找到第一个不等于e的结点，相当于删除链表头部值为e的结点
         */
        while (head != null) {
            if (head.e.compareTo(e) != 0) {
                break;
            }
            head = head.next;
        }

        Node<E> pre = head;
        Node<E> cur = head;

        while (cur != null) {
            if (cur.e.compareTo(e) == 0) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 结点
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//结点数据域
        Node<E> next; //指向下一个结点

        public Node(E e) {
            this.e = e;
        }
    }

}
