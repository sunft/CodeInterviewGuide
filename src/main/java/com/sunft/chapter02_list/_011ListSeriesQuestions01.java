package com.sunft.chapter02_list;

/**
 * 两个单链表相交的一系列问题
 *
 * 【题目】
 *  在本题中，单链表可能有环，也可能无环。给定两个单链表的头结点head1和head2，
 *  这两个链表可能相交，也可能不相交。请实现一个函数，如果两个链表相交，请返回
 *  相交的第一个结点；如果不相交，返回null即可。
 *
 *  要求：
 *  如果链表1的长度为N，链表2的长度为M，时间复杂度请达到O(N+M)，额外空间复杂度请达到O(1)。
 *
 *  【解答】
 *  本题可以拆分成三个子问题，每个问题都可以作为一道独立的算法题，具体如下：
 *  问题一：如何判断一个链表是否有环，如果有，则返回第一个进入环的结点，没有则返回null。
 *          返回进入环的结点证明：https://blog.csdn.net/sinat_35261315/article/details/79205157
 *  问题二：如何判断两个无环链表是否相交，相交则返回第一个相交结点，不想交则返回null。
 *  问题三：如何判断两个有环链表是否相交，相交则返回第一个相交结点，不想交则返回null。
 *  注意：如何一个链表有环，另外一个链表无环，它们是不可能相交的，直接返回null。
 *
 */
public class _011ListSeriesQuestions01<E extends Comparable<? super E>> {

    /**
     * 判断链表是否有环，采用快慢指针的方法
     * @param head 头结点
     * @return
     */
    public Node<E> getLoopNode(Node<E> head) {

        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node<E> slow = head.next;//慢指针
        Node<E> fast = head.next.next;//快指针

        //判断链表是否有环
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        //找出链表入口结点
        Node<E> step = head;
        while (step != slow) {
            step = step.next;
            slow = slow.next;
        }

        return step;
    }

    /**
     * 判断无环链表是否相交，相交则返回第一个相交的结点
     *
     * @param head1 第一个链表
     * @param head2 第二个链表
     * @return
     */
    public Node<E> noLoop(Node<E> head1, Node<E> head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node<E> cur1 = head1;
        Node<E> cur2 = head2;
        int n = 0;

        //计算第一个链表的长度
        while (cur1.next != null) {
            n ++;
            cur1 = cur1.next;
        }

        while (cur2.next != null) {
            n --;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) {
            return null;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n --;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    /**
     * 判断两个有环链表的相交情况
     * @param head1 第一个链表的头结点
     * @param loop1 入环结点
     * @param head2 第二个链表的头结点
     * @param loop2 入环结点
     * @return
     */
    public Node<E> bothLoop(Node<E> head1, Node<E> loop1, Node<E> head2, Node<E> loop2) {
        Node<E> cur1 = null;
        Node<E> cur2 = null;

        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n ++;
                cur1 = cur1.next;
            }

            while (cur2 != loop2) {
                n --;
                cur2 = cur2.next;
            }

            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n --;
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop1) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    /**
     *
     * @param head1
     * @param head2
     * @return
     */
    public Node<E> getIntersectNode(Node<E> head1, Node<E> head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        //判断链表是否有环
        Node<E> loop1 = getLoopNode(head1);
        Node<E> loop2 = getLoopNode(head2);

        //两个链表都没有环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        //两个链表都有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }

        return null;
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
