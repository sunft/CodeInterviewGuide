package com.sunft.chapter02_list;

/**
 * 环形单链表的约瑟夫问题
 * 【题目】
 * 输入：一个环形单向链表的头结点head和报数的值n
 * 返回：最后生存下来的结点，且这个结点自己组成环形单向链表，其他结点都删掉
 * 进阶：如果链表结点数为N，想在时间复杂度为O(N)时完成原问题的要求，该怎么实现？
 * 思路：假设链表长度为n，输入的数为m，直接算出要最后存活的结点即可。
 */
public class _006JosephusKill02<E extends Comparable<? super E>>{

    public Node<E> josephusKill02(Node<E> head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }

        Node<E> cur = head.next;
        int size = 1;//链表结点的个数
        while (cur != head) {
            size ++;
            cur = cur.next;
        }
        size = getLive(size, m);//求出被删除结点的位置
        while (--size != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }


    /**
     * 求解链表中最终存活的是哪个结点
     * @param size
     * @param m
     * @return
     */
    private int getLive(int size, int m) {
        if (size == 1) {
            return 1;
        }

        return (getLive(size - 1, m) + m -1) % size + 1;
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
