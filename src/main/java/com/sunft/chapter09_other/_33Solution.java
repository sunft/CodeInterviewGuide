package com.sunft.chapter09_other;

/**
 * 画匠问题
 * <p>
 * 【题目】
 * 给定一个整型数组arr， 数组中的每个值都为正数， 表示完成一
 * 幅画作需要的时间， 再给定一个整数num表示画匠的数量， 每个画匠
 * 只能画连在一起的画作。 所有的画家并行工作， 请返回完成所有的画
 * 作需要的最少时间。
 * <p>
 * 【举例】
 * arr=[3， 1， 4]， num=2。
 * 最好的分配方式为第一个画匠画3和1， 所需时间为4。 第二个画
 * 匠画4， 所需时间为4。 因为并行工作， 所以最少时间为4。 如果分配
 * 方式为第一个画匠画3， 所需时间为3。 第二个画匠画1和4， 所需的时
 * 间为5。 那么最少时间为5， 显然没有第一种分配方式好。 所以返回
 * 4。
 * <p>
 * arr=[1， 1， 1， 4， 3]， num=3。
 * 最好的分配方式为第一个画匠画前三个1， 所需时间为3。 第二个
 * 画匠画4， 所需时间为4。 第三个画匠画3， 所需时间为3。 返回4。
 */
public class _33Solution {

    public int solution1(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            throw new RuntimeException("err");
        }
        int[] sumArr = new int[arr.length];
        int[] map = new int[arr.length];

        sumArr[0] = arr[0];
        map[0] = arr[0];
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
            map[i] = sumArr[i];
        }
        for (int i = 1; i < num; i++) {
            for (int j = map.length - 1; j > i - 1; j--) {
                int min = Integer.MAX_VALUE;
                for (int k = i - 1; k < j; k++) {
                    int cur = Math.max(map[k], sumArr[j] - sumArr[k]);
                    min = Math.min(min, cur);
                }
                map[j] = min;
            }
        }
        return map[arr.length - 1];
    }

    public int solution2(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            throw new RuntimeException("err");
        }
        int[] sumArr = new int[arr.length];
        int[] map = new int[arr.length];
        sumArr[0] = arr[0];
        map[0] = arr[0];
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
            map[i] = sumArr[i];
        }
        int[] cands = new int[arr.length];
        for (int i = 1; i < num; i++) {
            for (int j = map.length - 1; j > i - 1; j--) {
                int minPar = cands[j];
                int maxPar = j == map.length - 1 ? j : cands[j + 1];
                int min = Integer.MAX_VALUE;
                for (int k = minPar; k < maxPar + 1; k++) {
                    int cur = Math.max(map[k], sumArr[j] - sumArr[k]);
                    if (cur <= min) {
                        min = cur;
                        cands[j] = k;
                    }
                }
                map[j] = min;
            }
        }
        return map[arr.length - 1];
    }

    public int solution3(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            throw new RuntimeException("err");
        }
        if (arr.length < num) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i != arr.length; i++) {
                max = Math.max(max, arr[i]);
            }
            return max;
        } else {
            int minSum = 0;
            int maxSum = 0;
            for (int i = 0; i < arr.length; i++) {
                maxSum += arr[i];
            }
            while (minSum != maxSum - 1) {
                int mid = (minSum + maxSum) / 2;
                if (getNeedNum(arr, mid) > num) {
                    minSum = mid;
                } else {
                    maxSum = mid;
                }
            }
            return maxSum;
        }
    }

    private int getNeedNum(int[] arr, int lim) {
        int res = 1;
        int stepSum = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] > lim) {
                return Integer.MAX_VALUE;
            }
            stepSum += arr[i];
            if (stepSum > lim) {
                res++;
                stepSum = arr[i];
            }
        }
        return res;
    }

}
