package com.sunft.chapter02_list;

/**
 * 在单链表中删除指定值的结点
 * 【题目】
 *  给定一个链表的头结点head和一个整数num，请实现函数将值为num的值全部删除。
 *  例如，链表为1->2->3->4->null，num=3，链表调整后为：1->2->4->null。
 * @param <E>
 */
public class _014RemoveValue01<E extends Comparable<? super E>> {

    /**
     * 删除链表中指定的结点，自己实现的代码
     * @param head 头结点
     * @param e 待删除结点的元素
     */
    public void removeValue01(Node<E> head, E e) {
        Node<E> cur = head;
        Node<E> pre = cur;
        while (cur != null) {
            if (cur.e.compareTo(e) == 0) {
              if (cur == head) {
                  head = head.next;
                  cur = head;
                  pre = cur;
              }  else {
                pre.next = cur.next;
              }
            } else {
                if (cur == head) {
                    cur = cur.next;
                } else {
                    cur = cur.next;
                    pre = pre.next;
                }
            }
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
