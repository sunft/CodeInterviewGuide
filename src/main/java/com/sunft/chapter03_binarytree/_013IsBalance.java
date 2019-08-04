package com.sunft.chapter03_binarytree;

/**
 * 判断二叉树是否为平衡二叉树
 * <p>
 * 【题目】
 * 平衡二叉树的性质为：要么是一棵空树，要么任何一个节点的左右子树高度差
 * 的绝对值不超过1。给定一棵二叉树的头结点head，判断这棵二叉树是否为平衡
 * 二叉树。
 * <p>
 * 【要求】
 * 如果二叉树的结点数为N，要求时间复杂度为O(N)。
 */
public class _013IsBalance<E extends Comparable<? super E>> {

    public boolean isBalance(Node<E> head) {
        boolean[] res = new boolean[11];
        res[0] = true;
        getHeight(head, 1, res);
        return res[0];
    }

    private int getHeight(Node<E> head, int level, boolean[] res) {
        if (head == null) {
            return level;
        }

        int lH = getHeight(head.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rH = getHeight(head.right, level + 1, res);
        if (!res[0]) {
            return level;
        }

        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }

        return Math.max(lH, rH);
    }

    /**
     * 结点
     *
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//结点数据域
        Node<E> left; //左孩子
        Node<E> right; //右孩子

        public Node(E e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

}
