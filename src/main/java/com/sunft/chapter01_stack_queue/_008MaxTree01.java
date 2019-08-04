package com.sunft.chapter01_stack_queue;

import java.util.HashMap;
import java.util.Stack;

/**
 * 构造数组的MaxTree
 * 【题目】
 * 定义二叉树结点
 * 一个数组的MaxTree定义如下。
 * 1)、数组必须没有重复元素;
 * 2)、MaxTree是一棵二叉树，数组的每一个值对应一个二叉树结点;
 * 3)、包括MaxTree树在内且在其中的每一棵子树上，值最大的结点都是树的头。
 * 【要求】
 * 给定一个没有重复元素的数组arr，写出生成这个数组的MaxTree的函数，
 * 要求如果数组长度为N，则时间复杂度为O(N)、额外空间复杂度为O(N)。
 */
public class _008MaxTree01<E extends Comparable<? super E>> {

    /**
     * 表示树的结点
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node getMaxTree(int[] arr) {
        //声明树的结点数组并执行初始化
        Node[] nArr = new Node[arr.length];
        for (int i = 0; i != arr.length; i ++) {
            nArr[i] = new Node(arr[i]);
        }

        Stack<Node> stack = new Stack<>();
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();
        for (int i = 0; i != nArr.length; i ++) {
            Node curNode = nArr[i];
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
                popStackSetMap(stack, lBigMap);
            }
            stack.push(curNode);
        }

        while (!stack.isEmpty()) {
            popStackSetMap(stack, rBigMap);
        }

        Node head = null;
        for (int i = 0; i != nArr.length; i ++) {
            Node curNode = nArr[i];
            Node left = lBigMap.get(curNode);
            Node right = rBigMap.get(curNode);
            if (left == null && right == null) {
                head = curNode;
            } else if (left == null) {
                if (right.left == null) {
                    right.left = curNode;
                } else {
                    right.right = curNode;
                }
            } else if (right == null) {
                if (left.left == null) {
                    left.left = curNode;
                } else {
                    left.right = curNode;
                }
            } else {
                Node parent = left.value < right.value ? left : right;
                if (parent.left == null) {
                    parent.left = curNode;
                } else {
                    parent.right = curNode;
                }
            }
        }
        return head;
    }

    private void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map) {
        Node popNode = stack.pop();
        if (stack.isEmpty()) {
            map.put(popNode, null);
        } else {
            map.put(popNode, stack.peek());
        }
    }

}
