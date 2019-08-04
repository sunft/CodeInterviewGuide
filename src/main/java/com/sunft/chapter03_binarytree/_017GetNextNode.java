package com.sunft.chapter03_binarytree;

/**
 * 在二叉树中找到一个结点的后继结点
 *
 * 【题目】
 *  现在有一种新的二叉树结点类型如下：
 *  private static class Node<E extends Comparable<? super E>> {
 *         E e;//结点数据域
 *         Node<E> left; //左孩子
 *         Node<E> right; //右孩子
 *         Node<E> parent; //指向父亲
 *
 *         public Node(E e) {
 *             this.e = e;
 *         }
 *
 *         @Override
 *         public String toString() {
 *             return e.toString();
 *         }
 *     }
 *
 *     该结构比普通二叉树结点结构锁了一个指向父结点的parent指针。假设有一棵Node
 *     类型的结点组成的二叉树，树中每个结点的parent指针都正确地指向自己的父结点，头结点
 *     的parent指向null。只给一个在二叉树中的某个结点node，请实现返回node的后继结点
 *     的函数。在二叉树的中序遍历的序列中，node的下一个结点叫做node的后继结点。
 */
public class _017GetNextNode<E extends Comparable<? super E>> {

    public Node<E> getNextNode(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            Node<E> parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private Node<E> getLeftMost(Node<E> node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
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
        Node<E> parent; //指向父亲

        public Node(E e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

}
