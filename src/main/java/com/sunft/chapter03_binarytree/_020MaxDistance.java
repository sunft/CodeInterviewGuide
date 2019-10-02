package com.sunft.chapter03_binarytree;

/**
 * 二叉树结点间的最大距离问题
 * 【题目】
 *  从二叉树的结点A出发，可以向上或者向下走，但沿途的结点只能经过一次，当到达结点B时，路径上
 *  的结点数叫做A到B的距离。
 *
 *  【要求】
 *   如果二叉树的结点数为N，时间复杂度要求为O(N)。
 */
public class _020MaxDistance {

    public int maxDistance(Node head) {
        int[] record = new int[1];
        return posOrder(head, record);
    }

    private int posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            return 0;
        }

        int lMax = posOrder(head.left, record);
        int maxFromLeft = record[0];
        int rMax = posOrder(head.right, record);
        int maxFromRight = record[0];
        int curNodeMax = maxFromLeft + maxFromRight + 1;
        record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
        return Math.max(Math.max(lMax, rMax), curNodeMax);
    }

    public class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }

    }

}
