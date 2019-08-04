package com.sunft.chapter03_binarytree;

/**
 * 遍历二叉树的神级方法
 *
 * 【题目】
 * 给定一棵二叉树的头结点head，完成二叉树的先序、中序和后序遍历。如果二叉树的节点数为N，
 * 要求时间复杂度为O(N)，额外空间复杂度为O(1)。
 *
 * Morris遍历
 *
 * @param <E>
 */
public class _005MorrisBinaryTree<E extends Comparable<? super E>> {

    /**
     * morrisIn的中序遍历
     * @param head
     */
    public void morrisIn(Node<E> head) {
        if (head == null) {
            return;
        }

        Node<E> cur1 = head;
        Node<E> cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                //找到左子树最右边的结点
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                } else {
                    cur2.right = null;
                }
            }
            System.out.print(cur1.e + " ");
            cur1 = cur1.right;
        }
        System.out.println();

    }

    /**
     * MorrisPre遍历
     * @param head
     */
    public void morrisPre(Node<E> head) {
        if (head == null) {
            return;
        }

        Node<E> cur1 = head;
        Node<E> cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                //找到左子树最右边的结点
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    System.out.print(cur1.e + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                System.out.print(cur1.e + " ");
            }
            cur1 = cur1.right;
        }
        System.out.println();
    }

    public void morrisPos(Node<E> head) {
        if (head == null) {
            return;
        }

        Node<E> cur1 = head;
        Node<E> cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }

                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
        System.out.println();
    }

    private void printEdge(Node<E> head) {
        Node<E> tail = reverseEdge(head);
        Node<E> cur = tail;
        while (cur != null) {
            System.out.print(cur.e + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private Node<E> reverseEdge(Node<E> from) {
        Node<E> pre = null;
        Node<E> next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
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
