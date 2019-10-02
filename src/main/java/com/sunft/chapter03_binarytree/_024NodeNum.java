package com.sunft.chapter03_binarytree;

/**
 * 统计完全二叉树的节点数
 * 【题目】
 *  给定一棵完全二叉树的头结点head，返回这棵树的节点个数。
 *
 *  【要求】
 *   如果完全二叉树的节点数为N，请实现时间复杂度低于O(N)的解法。
 */
public class _024NodeNum {

    public int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }

        return bs(head, 1, mostLeftLevel1(head, 1));
    }

    private int bs(Node node, int l, int h) {
        if (l == h) {
            return 1;
        }

        if (mostLeftLevel1(node.right, l + 1) == h) {
            return (1 << (h - 1)) + bs(node.right, l + 1, h);
        } else {
            return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
        }
    }

    private int mostLeftLevel1(Node node, int level) {
        while (node != null) {
            level ++;
            node = node.left;
        }
        return level - 1;
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
