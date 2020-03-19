package com.sunft.chapter08_array_matrix;

import java.util.HashMap;

/**
 * 未排序数组中累加和为给定值的最长子数组系列问题
 * <p>
 * 【题目】
 * 给定一个无序数组arr， 其中元素可正、 可负、 可0， 给定一个整
 * 数k。 求arr所有的子数组中累加和为k的最长子数组长度。
 * <p>
 * 【补充题目】
 * 给定一个无序数组arr， 其中元素可正、 可负、 可0。 求arr所有的
 * 子数组中正数与负数个数相等的最长子数组长度。
 * <p>
 * 【补充题目】
 * 给定一个无序数组arr， 其中元素只是1或0。 求arr所有的子数组
 * 中0和1个数相等的最长子数组长度。
 */
public class _11MaxLength {

    public int maxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //重要
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - K)) {
                len = Math.max(i - map.get(sum - K), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

}
