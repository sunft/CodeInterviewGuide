package com.sunft.chapter03_binarytree;

/**
 * 根据后续数组重建搜索二叉树
 *
 * 【题目】
 *  给定一个整型数组arr，已知其中没有重复值，判断arr是否可能是结点值类型为整型
 *  的二叉搜索树后续遍历。
 *
 *  进阶：如果整型数组arr中没有重复值，且已知是一棵搜索二叉树的后续遍历结果，
 *  通过数组arr重构二叉树
 *
 */
public class _014IsPostArray<E extends Comparable<? super E>> {
    
    public boolean isPostArray(E[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        
        return isPost(arr, 0, arr.length - 1);
    }

    private boolean isPost(E[] arr, int start, int end) {
        if (start == end) {
            return true;
        }

        int less = -1;
        int more = end;

        for (int i = start; i < end; i ++) {
            if (arr[end].compareTo(arr[i]) > 0) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }

        if (less == -1 || more == end) {
            return isPost(arr, start, end - 1);
        }

        if (less != more - 1) {
            return false;
        }

        return isPost(arr, start, less) && isPost(arr, more, end - 1);
    }
    
    public Node<E> posArrayToBST(E[] posArr) {
        if (posArr == null) {
            return null;
        }
        return postToBST(posArr, 0, posArr.length - 1);
    }

    private Node<E> postToBST(E[] posArr, int start, int end) {
        if (start > end) {
            return null;
        }

        Node<E> head = new Node<>(posArr[end]);
        int less = -1;
        int more = end;
        for (int i = start; i < end; i ++) {
            if (posArr[end].compareTo(posArr[i]) > 0) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }
        head.left = postToBST(posArr, start, less);
        head.right = postToBST(posArr, more, end - 1);
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
