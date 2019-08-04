package com.sunft.chapter03_binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的按层打印与ZigZag打印
 *
 * 【题目】
 *  给定一棵二叉树的头结点head，分别实现按层打印和ZigZag打印二叉树的函数
 *
 *  例如，二叉树如图3-29所示。
 *
 *  按层打印时，输出格式必须如下：
 *  Level 1:1
 *  Level 2:2 3
 *  Level 3:4 5 6
 *  Level 4:7 8
 *
 *  ZigZag打印时，输出格式必须如下：
 *  Level 1 from left to right: 1
 *  Level 2 from right to left: 3 2
 *  Level 3 from left ro right: 4 5 6
 *  Level 4 from right to left: 8 7
 *
 */
public class _009PrintBinaryTree {

    /**
     * 按层打印
     * @param head
     */
    public void printByLevel(Node head) {
        if (head == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        int level = 1;
        Node last = head;
        Node nLast = null;
        queue.offer(head);
        System.out.print("Level " + (level ++) + " : ");
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if (head.left != null) {
                queue.offer(head.left);
                nLast = head.left;
            }

            if (head.right != null) {
                queue.offer(head.right);
                nLast = head.right;
            }

            if (head == last && !queue.isEmpty()) {
                System.out.print("\nLevel " + (level ++) + ":" );
                last = nLast;
            }
        }
        System.out.println();
    }

    /**
     * 按ZigZag打印
     * @param head
     */
    public void printByZigZag(Node head) {
        if (head == null) {
            return;
        }

        Deque<Node> dq = new LinkedList<>();
        int level = 1;
        boolean lr = true;
        Node last = head;
        Node nLast = null;
        dq.offerFirst(head);
        printLevelAndOrientation(level ++, lr);
        while (!dq.isEmpty()) {
            if (lr) {
                head = dq.pollFirst();
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerFirst(head.left);
                }
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerLast(head.right);
                }
            } else {
                head = dq.pollLast();
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerFirst(head.right);
                }

                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerFirst(head.left);
                }
            }
            System.out.print(head.value + " ");
            if (head == last && !dq.isEmpty()) {
                lr = !lr;
                last = nLast;
                nLast = null;
                System.out.println();
                printLevelAndOrientation(level ++, lr);
            }
        }
        System.out.println();
    }

    private void printLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right: " : "right to left: ");
    }

    /**
     * 结点
     */
    private static class Node {
        int value;//结点数据域
        Node left; //左孩子
        Node right; //右孩子

        public Node(int value) {
            this.value = value;
        }

    }

}
