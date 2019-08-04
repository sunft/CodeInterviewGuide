package com.sunft.chapter03_binarytree;

/**
 * 找到二叉树中的最大搜索二叉子树
 *
 * 【题目】
 *  给定一棵二叉树的头结点head，已知其中所有结点的值都不一样，找到含有结点最多
 *  的搜索二叉子树，并返回这棵子树的头结点。
 *
 *  【要求】
 *   如果结点数为N，要求时间复杂度为O(N)，额外空间复杂度为O(h)，h为二叉树的高度。
 */
public class _007BiggestSubBST {

    public Node biggestSubBST(Node head) {
        int[] record = new int[3];
        return posOrder(head, record);
    }

    private Node posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }

        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        Node lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        Node rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        record[1] = Math.min(lMin, value);
        record[2] = Math.max(rMax, value);
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
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
