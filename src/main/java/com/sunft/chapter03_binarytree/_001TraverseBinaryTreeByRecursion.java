package com.sunft.chapter03_binarytree;

/**
 * 分别用递归和非递归方式实现二叉树先序、中序和后序遍历
 * 【题目】
 *  用递归和非递归方式，分别按照二叉树先序、中序和后序打印所以的结点。
 *  我们约定先序遍历顺序为根、左、右；中序遍历顺序为左、根、右；后序遍历顺序为左右根
 *
 *  该类使用递归遍历二叉树：先序、中序、后序三种方式
 *
 * @param <E>
 */
public class _001TraverseBinaryTreeByRecursion<E extends Comparable<? super E>> {

    /**
     * 先序遍历
     * @param head
     */
    public void preOrderRecur(Node<E> head) {
        if (head == null) {
            return;
        }
        System.out.print(head.e + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 中序遍历二叉树
     * @param head
     */
    public void inOrderRecur(Node<E> head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.e + " ");
        inOrderRecur(head.right);
    }

    /**
     * 后序遍历二叉树
     * @param head
     */
    public void posOrderRecur(Node<E> head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.e + " ");
    }

    /**
     * 结点
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
