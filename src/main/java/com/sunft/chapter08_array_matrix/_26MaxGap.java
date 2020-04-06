package com.sunft.chapter08_array_matrix;

/**
 * 数组排序之后相邻数的最大差值
 * <p>
 * 【题目】
 * 给定一个整型数组arr， 返回排序后的相邻两数的最大差值。
 * <p>
 * 【举例】
 * arr=[9， 3， 1， 10]。 如果排序， 结果为[1， 3， 9， 10]， 9和3的差为最大差值， 故返回6。
 * <p>
 * arr=[5， 5， 5， 5]。 返回0。
 * <p>
 * 【要求】
 * 如果arr的长度为N， 请做到时间复杂度为O(N)。
 */
public class _26MaxGap {

    public int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }

        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max); //算出桶号
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = 0;
        int i = 0;
        while (i <= len) {
            if (hasNum[i++]) { //找到第一个不为空的桶
                lastMax = maxs[i - 1];
                break;
            }
        }
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    /**
     * 使用long类型是为了防止相乘时溢出
     *
     * @param num
     * @param len
     * @param min
     * @param max
     * @return
     */
    private int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

}
