package com.sunft.chapter09_other;

/**
 * 分糖果问题
 * <p>
 * 【题目】
 * 一群孩子做游戏， 现在请你根据游戏得分来发糖果， 要求如下：
 * 1．每个孩子不管得分多少， 起码分到1个糖果。
 * 2．任意两个相邻的孩子之间， 得分较多的孩子必须拿多一些的糖果。
 * 给定一个数组arr代表得分数组， 请返回最少需要多少糖果。
 * 例如： arr=[1， 2， 2]， 糖果分配为[1， 2， 1]， 即可满足要求且
 * 数量最少， 所以返回4。
 * <p>
 * 【进阶题目】
 * 原题目中的两个规则不变， 再加一条规则：
 * 3．任意两个相邻的孩子之间如果得分一样， 糖果数必须相同。
 * 给定一个数组arr代表得分数组， 返回最少需要多少糖果。
 * 例如： arr=[1， 2， 2]， 糖果分配为[1， 2， 2]， 即可满足要求且
 * 数量最少， 所以返回5。
 * <p>
 * 【要求】
 * arr长度为N， 原题与进阶题都要求时间复杂度为O(N)， 额外空间
 * 复杂度为O(1)。
 */
public class _22Candy {

    public int candy1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex1(arr, 0);
        int res = rightCands(0, index++);
        int lbase = 1;
        int next = 0;
        int rcands = 0;
        int rbase = 0;
        while (index != arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                index++;
            } else if (arr[index] < arr[index - 1]) {
                next = nextMinIndex1(arr, index - 1);
                rcands = rightCands(index - 1, next++);
                rbase = next - index + 1;
                res += rcands + (rbase > lbase ? -lbase : -rbase);
                lbase = 1;
                index = next;
            } else {
                res += 1;
                lbase = 1;
                index ++;
            }
        }
        return res;
    }

    private int nextMinIndex1(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public int rightCands(int left, int right) {
        int n = right - left + 1;
        return n + n * (n - 1) / 2;
    }

    public int candy2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex2(arr, 0);
        int[] data = rightCandsAndBase(arr, 0, index++);
        int res = data[0];
        int lbase = 1;
        int same = 1;
        int next = 0;
        while (index != arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                same = 1;
                index ++;
            } else if (arr[index] < arr[index - 1]) {
                next = nextMinIndex2(arr, index - 1);
                data = rightCandsAndBase(arr, index - 1, next++);
                if (data[1] <= lbase) {
                    res += data[0] - data[1];
                } else {
                    res += -lbase * same + data[0] - data[1] + data[1] * same;
                }
                index = next;
                lbase = 1;
                same = 1;
            } else {
                res += lbase;
                same ++;
                index ++;
            }
        }
        return res;
    }

    private int nextMinIndex2(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public int[] rightCandsAndBase(int[] arr, int left, int right) {
        int base = 1;
        int cands = 1;
        for (int i = right - 1; i >= left; i--) {
            if (arr[i] == arr[i + 1]) {
                cands += base;
            } else {
                cands += ++base;
            }
        }
        return new int[]{cands, base};
    }


}
