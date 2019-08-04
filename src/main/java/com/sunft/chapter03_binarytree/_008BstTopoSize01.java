package com.sunft.chapter03_binarytree;

/**
 * 找到二叉树中符合搜索二叉树条件的最大拓扑结构
 *
 * 【题目】
 *  给定一棵二叉树的头结点head，已知所有结点的值都不一样，返回其中最大的且符合
 *  搜索二叉树条件的最大拓扑结构的大小。
 */
public class _008BstTopoSize01 {

    public int bstTopoSize1(Node head) {
        if (head == null) {
            return 0;
        }
        
        int max = maxTopo(head, head);
        max = Math.max(bstTopoSize1(head.left), max);
        max = Math.max(bstTopoSize1(head.right), max);
        return max;
    }

    private int maxTopo(Node h, Node n) {
        if (h != null && n != null && isBSTNode(h, n, n.value)) {
            return maxTopo(h, n.left) + maxTopo(h, h.right) + 1;
        }
        return 0;
    }

    private boolean isBSTNode(Node h, Node n, int value) {
        if (h == null) {
            return false;
        }

        if (h == n) {
            return true;
        }

        return isBSTNode(h.value > value ? h.left:h.right, n, value);
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
