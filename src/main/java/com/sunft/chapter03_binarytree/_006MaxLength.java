package com.sunft.chapter03_binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 在二叉树中找到累加和为指定值的最长路径长度
 *
 * 【题目】
 *  给定一棵二叉树的头结点和一个32位整数sum，二叉树结点值类型为整型，求累加和为sum
 *  的最长路径长度。路径是指从某个结点往下，每次最多选择一个孩子结点或者不选所形成的节点链。
 *
 *
 *
 */
public class _006MaxLength {

    public int getMaxLength(Node head, int sum) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0);//重要
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    private int preOrder(Node head, int sum, int preSum, int level, int maxLen, Map<Integer, Integer> sumMap) {
        if (head == null) {
            return maxLen;
        }

        int curSum = preSum + head.value;
        if (!sumMap.containsKey(curSum)) {
            sumMap.put(curSum, level);
        }
        if (sumMap.containsKey(curSum - sum)) {
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }

        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);

        if (level == sumMap.get(curSum)) {
            sumMap.remove(curSum);
        }

        return maxLen;
    }

    /**
     * 结点
     */
    private static class Node {
        int value;//结点数据域
        Node left; //左孩子
        Node right; //右孩子

        public Node(int e) {
            this.value = value;
        }

    }

}
