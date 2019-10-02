package com.sunft.chapter03_binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * 统计和生成所有不同的二叉树
 * 【题目】
 *  给定一个整数N，如果N<1，代表空树结构，否则代表中序遍历的结果为
 *  {1,2,3,...,N}。请计算返回可能的二叉树结构有多少。
 *
 *  进阶：N的含义不变，假设可能的二叉树结构有M种，请返回M个二叉树的头结点，
 *  每一颗二叉树代表一种可能的结构。
 */
public class _23NumTrees {

    public int numTree(int n) {
        if (n < 2) {
            return 1;
        }

        int[] num = new int[n + 1];
        num[0] = 1;
        for (int i = 1; i < n + 1; i ++) {
            for (int j = 1; j < i + 1; j ++) {
                num[i] += num[j - 1] * num[i - j];
            }
        }
        return num[n];
    }

    public List<Node> generate(int n) {
        return generate(1, n);
    }

    public List<Node> generate(int start, int end) {
        List<Node> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
        }

        Node head = null;
        for (int i = start; i < end + 1; i ++) {
            head = new Node(i);
            List<Node> lSubs = generate(start, i - 1);
            List<Node> rSubs = generate(i + 1, end);
            for (Node l : lSubs) {
                for (Node r : rSubs) {
                    head.left = l;
                    head.right = r;
                    res.add(cloneTree(head));
                }
            }
        }
        return res;
    }

    public Node cloneTree(Node head) {
        if (head == null) {
            return null;
        }

        Node res = new Node(head.value);
        res.left = cloneTree(head.left);
        res.right = cloneTree(head.right);
        return res;
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
