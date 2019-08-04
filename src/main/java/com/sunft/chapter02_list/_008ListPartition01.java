package com.sunft.chapter02_list;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 方式一：使用快速排序的变种实现
 *
 * @param <E>
 */
public class _008ListPartition01<E extends Comparable<? super E>> {

    /**
     * 使用快速排序的改进版实现
     * @param head 链表头部
     * @param pivot 比较的基准值
     * @return
     */
    public Node<E> listPartition01(Node<E> head, E pivot) {
        if (head == null || pivot == null) {
            return head;
        }

        Node<E> cur = head;
        int i = 0;
        //计算链表的长度
        while (cur != null) {
            i ++;
            cur = cur.next;
        }
        Node<E>[] nodeAddr = (Node<E>[]) new Object[i];
        i = 0;
        cur = head;
        //初始化数组
        for (i = 0; i != nodeAddr.length; i ++) {
            nodeAddr[i] = cur;
            cur = cur.next;
        }

        //对数组进行排序
        arrPartition(nodeAddr, pivot);
        //将数组重新连接成链表
        for (i = 1; i != nodeAddr.length; i ++) {
                 nodeAddr[i - 1].next = nodeAddr[i];
        }
        nodeAddr[i - 1].next = null;
        return nodeAddr[0];
        
    }

    /**
     *
     * @param nodeAddr
     * @param pivot
     */
    private void arrPartition(Node<E>[] nodeAddr, E pivot) {
        int small = -1;
        int big = nodeAddr.length;
        int index = 0;
        while (index != big) {
            if (nodeAddr[index].e.compareTo(pivot) < 0) {
                swap(nodeAddr, ++ small, index ++);
            } else if (nodeAddr[index].e.compareTo(pivot) == 0) {
                index ++;
            } else {
                swap(nodeAddr, --big, index);
            }
        }
    }

    /**
     * 交换数组元素
     * @param nodeAddr
     * @param a
     * @param b
     */
    private void swap(Node<E>[] nodeAddr, int a, int b) {
        Node<E> tmp = nodeAddr[a];
        nodeAddr[a] = nodeAddr[b];
        nodeAddr[b] = tmp;
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
