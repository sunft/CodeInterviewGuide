package com.sunft.chapter02_list;

/**
 * 判断一个链表是否为回文结构
 * 【题目】
 * 给定一个链表的头结点head，请判断该链表是否为回文结构。
 * 例如：
 * 1->2->1，返回true
 * 1->2->2->1，返回true
 * 1->2->3，返回false
 * 实现思路：
 *  使用反转链表的方式
 * @param <E>
 */
public class _007IsPalindrome02<E extends Comparable<? super E>> {

    public boolean isPalindrome(Node<E> head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node<E> n1 = head;
        Node<E> n2 = head;

        //查找链表中部
        if (n2.next != null && n2.next.next != null) {
            n1 = n1.next;//最后会指向链表中间的元素
            n2 = n2.next.next;//最后会指向链表末尾或者倒数第二个元素
        }

        n2 = n1.next;//n2 -> 有部分第一个结点
        n1.next = null;//mid.next -> null
        Node<E> n3 = null;
        //反转链表右半部分
        while (n2 != null) {
            n3 = n2.next;//n3 -> 保存下一个结点
            n2.next = n1;//下一个反转结点
            n1 = n2;//n1移动
            n2 = n3;//n2移动
        }

        n3 = n1;//n3 -> 保存最后一个结点
        n2 = head;//n2 -> 左边第一个结点
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.e.compareTo(n2.e) != 0) {//检查回文
                res = false;
                break;
            }
            n1 = n1.next;//从左到中部
            n2 = n2.next;//从右到中部
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
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
