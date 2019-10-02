package com.sunft.chapter03_binarytree;

import java.util.HashMap;
import java.util.Map;


/**
 * 先序、中序和后序数组两两结合重构二叉树
 * 【题目】
 *  已知一棵二叉树的所有结点值都不同，给定这棵二叉树正确的先序、中序和后序数组。
 *  请分别用三个函数实现任意两种数组结合重构原来的二叉树，并返回重构二叉树的头结点。
 *
 */
public class _21ToTree {

    /**
     * 先序和中序结合重构二叉树
     * @param pre
     * @param in
     * @return
     */
    public Node preInToTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i ++) {
            map.put(in[i], i);
        }
        return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);

    }

    private Node preIn(int[] p, int pi, int pj, int[] n, int ni, int nj, Map<Integer, Integer> map) {
        if (pi > pj) {
            return null;
        }
        Node head = new Node(p[pi]);
        int index = map.get(p[pi]);
        head.left = preIn(p, pi + 1, pi + index - ni, n, ni, index -1, map);
        head.right = preIn(p, pi + index - ni + 1, pj, n, index + 1, nj, map);
        return head;
    }

    /**
     * 中序和后序重构二叉树
     */
    public Node inPosToTree(int[] in, int[] pos) {
        if (in == null || pos == null) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i ++) {
            map.put(in[i], i);
        }

        return inPos(in, 0, in.length - 1, pos, 0, pos.length - 1, map);
    }

    public Node inPos(int[] n, int ni, int nj, int[] s, int si, int sj, Map<Integer, Integer> map) {
        if (si > sj) {
            return null;
        }
        Node head = new Node(s[sj]);
        int index = map.get(s[sj]);
        head.left = inPos(n, ni, index - 1, s, si, si + index - ni - 1, map);
        head.right = inPos(n, index + 1, nj, s, si + index - ni, sj - 1, map);
        return head;
    }

    /**
     * 先序和后序结合重构二叉树
     * 每个结点的孩子数都为0或2的二叉树才能被先序与后序重构出来
     */
    public Node prePostToTree(int[] pre, int[] pos) {
        if (pre == null || pos == null) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pos.length; i ++) {
            map.put(pos[i], i);
        }
        return prePos(pre, 0, pre.length - 1, pos, 0, pos.length - 1, map);
    }

    public Node prePos(int[] p, int pi, int pj, int[] s, int si, int sj, Map<Integer, Integer> map) {
        Node head = new Node(s[sj--]);
        if (pi == pj) {
            return head;
        }
        int index = map.get(p[++pi]);
        head.left = prePos(p, pi, pi + index - si, s, si, index, map);
        head.right = prePos(p, pi + index - si + 1, pj, s, index + 1, sj, map);
        return head;
    }


    public class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }

    }

}
