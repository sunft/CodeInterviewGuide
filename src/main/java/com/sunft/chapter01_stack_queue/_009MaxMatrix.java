package com.sunft.chapter01_stack_queue;

import java.util.Stack;

/**
 * 求最大子矩阵的大小
 * 【题目】
 * 给定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的所有矩形区域中，
 * 最大的矩形区域为1的数量。
 * 例如：
 * 1    1   1   0
 * 其中，最大的矩形区域有3个1，所以返回3。
 * 再如：
 * 1    0   1   1
 * 1    1   1   1
 * 1    1   1   0
 * 其中，最大的矩形区域有6个1，所以返回6.
 */
public class _009MaxMatrix {

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }

        int maxArea = 0;//假设最大面积为0
        int[] height = new int[map[0].length];
        //遍历二维数组，每一行都做对应的统计处理
        for (int i = 0; i < map.length; i ++) {
            for (int j = 0; j < map[0].length; j ++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }

        return maxArea;
    }

    private static int maxRecFromBottom(int[] height) {
        //数组校验
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i ++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();//指向栈顶的位置
                int k = stack.isEmpty() ? -1 : stack.peek();//指向栈顶的下一个位置
                int curArea = (i - k - 1) * height[j];//计算面积
                maxArea = Math.max(maxArea, curArea);
            }
            //数据入栈
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty()? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {{1, 1, 1, 0}};
        System.out.println(maxRecSize(map));

        int[][] map1 = {{1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 1, 0}};
        System.out.println(maxRecSize(map1));
    }

}
