package com.sunft.chapter02_list;

import java.util.HashSet;
import java.util.Set;

/**
 * 删除无序单链表中值重复出现的结点
 * 【题目】
 * 给定一个无序单链表的头结点head，删除其中值重复出现的结点。
 * 例如：1->2->3->3->4->4->2->1->1->null，删除值重复的结点之后为1->2->3->4->null。
 * 方法一：如果链表长度为N，时间复杂度达到O(N)，采用HashSet实现。
 */
public class _013RemoveDuplicateNode01<E extends Comparable<? super E>> {

    /**
     * 使用HashSet实现，自己的代码
     * @param head
     */
    public void removeDuplicateNode01(Node<E> head){
        if (head == null) {
            return;
        }
        Set<Node<E>> set = new HashSet<>();
        Node<E> pre = head;
        Node<E> cur = head;
        set.add(cur);
        while (cur.next != null) {
            cur = cur.next;
            if (set.contains(cur)) {
                pre.next = cur.next;
            } else {
                set.add(cur);
                pre = cur;
            }
        }
    }

    /**
     * 书上的代码
     * @param head 头结点
     */
    public void removeRep01(Node<E> head) {
        if (head == null) {
            return;
        }
        Set<E> set = new HashSet<>();
        Node<E> pre = head;
        Node<E> cur = head.next;
        set.add(head.e);
        while (cur != null) {
            if (set.contains(cur.e)) {
                pre.next = cur.next;
            } else {
                set.add(cur.e);
                pre = cur;
            }
            cur = cur.next;
        }
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
