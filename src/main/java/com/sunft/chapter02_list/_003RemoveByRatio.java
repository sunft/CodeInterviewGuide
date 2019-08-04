package com.sunft.chapter02_list;

/**
 * 删除链表的a/b处的节点
 * @param <E>
 */
public class _003RemoveByRatio<E extends Comparable<? super E>> {

    public Node<E> removeByRatio(Node<E> head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }

        //计算链表的长度
        int n = 0;
        Node<E> cur = head;
        while (cur != null) {
            n ++;
            cur = cur.next;
        }
        //计算待删除节点在链表中的位置
        n = (int)Math.ceil((double) (a*n)/(double)b);
        //为1直接删除头节点
        if (n == 1) {
            head = head.next;
        }

        //找到要删除的结点
        if (n > 1) {
            cur = head;
            while (--n != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
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
