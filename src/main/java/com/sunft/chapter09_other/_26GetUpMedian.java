package com.sunft.chapter09_other;

/**
 * 在两个长度相等的排序数组中找到上中位数
 * <p>
 * 【题目】
 * 给定两个有序数组arr1和arr2， 已知两个数组的长度都为N， 求两个数组中所有数的上中位数。
 * <p>
 * 【举例】
 * arr1=[1， 2， 3， 4]， arr2=[3， 4， 5， 6]
 * 总共有8个数， 那么上中位数是第4小的数， 所以返回3。
 * <p>
 * arr1=[0， 1， 2]， arr2 =[3， 4， 5]
 * 总共有6个数， 那么上中位数是第3小的数， 所以返回2。
 * <p>
 * 【要求】
 * 时间复杂度为O(logN)， 额外空间复杂度为O(1)。
 */
public class _26GetUpMedian {

    public int getUpMedian(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            throw new RuntimeException("Your arr is invalid!");
        }
        int start1 = 0;
        int end1 = arr1.length - 1;
        int start2 = 0;
        int end2 = arr2.length - 1;
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (start1 < end1) {
            mid1 = (start1 + end1) / 2;
            mid2 = (start2 + end2) / 2;
            //元素个数为奇数，则offset为0，元素个数为偶数，则offset为1。
            offset = ((end1 - start1 + 1) & 1) ^ 1;
            if (arr1[mid1] > arr2[mid2]) {
                end1 = mid1;
                start2 = mid2 + offset;
            } else if (arr1[mid1] < arr2[mid2]) {
                start1 = mid1 + offset;
                end2 = mid2;
            } else {
                return arr1[mid1];
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }

}
