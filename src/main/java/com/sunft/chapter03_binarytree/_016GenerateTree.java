package com.sunft.chapter03_binarytree;

/**
 * 通过有序数组生成平衡搜索二叉树
 *
 * 【题目】
 *  给定一个有序数组sortArr，已知其中没有重复值，用这个有序数组生成一棵平衡搜索
 *  二叉树，并且该搜索二叉树中序遍历的结果与sortArr一致。
 *
 * @param <E>
 */
public class _016GenerateTree<E extends Comparable<? super E>> {

    public Node<E> genetateTree(E[] sortArr) {
        if (sortArr == null) {
            return null;
        }

        return generate(sortArr, 0, sortArr.length - 1);
    }

    private Node<E> generate(E[] sortArr, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node<E> head = new Node<>(sortArr[mid]);
        head.left = generate(sortArr, start, mid - 1);
        head.right = generate(sortArr, mid + 1, end);
        return head;
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
