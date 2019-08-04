package com.sunft.chapter03_binarytree;

/**
 * 在二叉树中找到两个结点的最近公共祖先
 *
 * 【题目】
 *  给定一棵二叉树的头结点head，以及这棵树中的两个结点o1和o2，
 *  请返回o1和o2的最近公共祖先结点。
 *
 *  进阶：如果查询两个结点的最近公共祖先的操作十分频繁，想法让单条
 *  查询的查询时间减少。
 *
 *  再进阶：给定二叉树的头结点head，同时给定所有想要进行的查询。二叉树的节点数
 *  量为N，查询条数为M，请在时间复杂度为O(N+M)内返回所有查询的结果。
 */
public class _018LowestAncestor<E extends Comparable<? super E>> {

    public Node<E> lowestAncestor(Node<E> head, Node<E> o1, Node<E> o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }

        Node<E> left = lowestAncestor(head.left, o1, o2);
        Node<E> right = lowestAncestor(head.right, o1, o2);

        if (left != null && right != null) {
            return head;
        }

        return left != null ? left : right;
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
