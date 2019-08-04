package com.sunft.chapter02_list;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制含有随机指针结点的链表
 *
 * 使用HashMap实现
 */
public class _009CopyListWithRand01<E extends Comparable<? super E>> {

    /**
     * 复制含有随机指针结点的链表
     * @param head
     * @return
     */
    public Node<E> copyListWithRand01(Node<E> head) {
        Map<Node<E>, Node<E>> map = new HashMap<>();
        Node<E> cur = head;
        //复制结点中的数据域
        while (cur != null) {
            map.put(cur, new Node<>(cur.e));
            cur = cur.next;
        }
        //复制next和rand域
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        return map.get(head);
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
