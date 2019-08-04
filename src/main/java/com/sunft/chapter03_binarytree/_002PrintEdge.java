package com.sunft.chapter03_binarytree;

/**
 * 打印二叉树的边界结点
 * 【题目】
 *  给定一棵二叉树的头结点head，按照如下两种标准分别实现二叉树边界结点的逆时针打印
 *
 *  标准一：
 *  1.头结点为边界结点；
 *  2.叶结点为边界结点；
 *  3.如果结点在其所在的层中是最左或最右的，那么也是边界结点。
 *
 *  标准二：
 *  1.头结点为边界结点；
 *  2.叶结点为边界结点；
 *  3.树左边界延伸下去的路径为边界结点；
 *  4.树右边界延伸下去的路径为边界结点。
 *
 *  【要求】
 *  1.如果结点数为N，两种标准实现的时间复杂度要求都为O(N)，额外空间复杂度要求都为O(h)，h为二叉树的高度.
 *  2.两种标准都要求逆时针顺序且不重复打印所有的边界结点。
 *  
 * @param <E>
 */
public class _002PrintEdge<E extends Comparable<? super E>> {

    /**
     * 按照标准一打印
     * @param head
     */
    public void printEdge1(Node<E> head) {
        if (head == null) {
            return;
        }
        
        int height = getHeight(head, 0);
        Node<E>[][] edgeMap = (Node<E>[][]) new Object[height][2];
        setEdgeMap(head, 0, edgeMap);
        //打印左边界
        for (int i = 0; i != edgeMap.length; i ++) {
            System.out.print(edgeMap[i][0].e + " ");
        }
        
        //打印既不是左边界，也不是右边界的叶子结点
        printLeafNotInMap(head, 0, edgeMap);
        //打印左边界，但不是左边界的结点
        for (int i = edgeMap.length - 1; i != -1; i --) {
            if (edgeMap[i][0] != edgeMap[i][1]) {
                System.out.print(edgeMap[i][1].e + " ");
            }
        }
        System.out.println();
    }

    //打印既不是左边界，也不是右边界的叶子结点
    private void printLeafNotInMap(Node<E> h, int l, Node<E>[][] m) {
        if (h == null) {
            return;
        }

        if (h.left == null && h.right == null && h != m[l][1]) {
            System.out.print(h.e + " ");
        }
        printLeafNotInMap(h.left, l + 1, m);
        printLeafNotInMap(h.right, l + 1, m);
    }

    private void setEdgeMap(Node<E> h, int l, Node<E>[][] edgeMap) {
        if (h == null) {
            return;
        }
        edgeMap[1][0] = edgeMap[1][0] == null ? h : edgeMap[1][0];
        edgeMap[1][1] = h;
        setEdgeMap(h.left, l + 1, edgeMap);
        setEdgeMap(h.right, l + 1, edgeMap);
    }

    /**
     * 计算树的高度
     * @param h
     * @param l
     * @return
     */
    private int getHeight(Node<E> h, int l) {
        if (h == null) {
            return 1;
        }
        return Math.max(getHeight(h.left, l + 1), getHeight(h.right, l + 1));
    }

    /**
     * 按照标准二打印二叉树
     * @param head
     */
    public void printEdge2(Node<E> head) {
        if (head == null) {
            return;
        }
        System.out.print(head.e + " ");
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
        System.out.println();
    }

    /**
     * 打印左边界
     * @param h
     * @param print
     */
    private void printLeftEdge(Node<E> h, boolean print) {
        if (h == null) {
            return;
        }
        if (print || (h.left == null && h.right == null)) {
            System.out.print(h.e + " ");
        }
        printLeftEdge(h.left, print);
        printLeftEdge(h.right, print && h.left == null);
    }

    /**
     * 打印右边界
     * @param h
     * @param print
     */
    private void printRightEdge(Node<E> h, boolean print) {
        if (h == null) {
            return;
        }

        printRightEdge(h.left, print && h.right == null);
        printRightEdge(h.right, print);
        if (print || (h.left == null && h.right == null)) {
            System.out.print(h.e + " ");
        }
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
