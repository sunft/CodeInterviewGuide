package com.sunft.chapter08_array_matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 在数组中找到出现次数大于N/K的数
 * 【题目】
 * 给定一个整型数组arr， 打印其中出现次数大于一半的数， 如果没有这样的数， 打印提示信息。
 * <p>
 * 【进阶】
 * 给定一个整型数组arr， 再给定一个整数K， 打印所有出现次数大于N/K的数， 如果没有这样的数， 打印提示信息。
 * <p>
 * 【要求】
 * 原问题要求时间复杂度为O(N)， 额外空间复杂度为O(1)。 进阶问题要求时间复杂度为O(N×K)， 额外空间复杂度为O(K)
 */
public class _06PrintHalfMajor {

    public void printHalfMajor(int[] arr) {
        int cand = 0;
        int times = 0;
        for (int i = 0; i != arr.length; i++) {
            if (times == 0) {
                cand = arr[i];
                times = 1;
            } else if (arr[i] == cand) {
                times++;
            } else {
                times--;
            }
        }
        times = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] == cand) {
                times++;
            }
        }
        if (times > arr.length / 2) {
            System.out.println(cand);
        } else {
            System.out.println("no such number.");
        }

    }

    public void printKMajor(int[] arr, int K) {
        if (K < 2) {
            System.out.println("the value of K is invalid.");
            return;
        }
        HashMap<Integer, Integer> cands = new HashMap<>();
        for (int i = 0; i != arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i] + 1));
            } else {
                if (cands.size() == K - 1) {
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        HashMap<Integer, Integer> reals = getReals(arr, cands);
        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> set : cands.entrySet()) {
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / K) {
                hasPrint = true;
                System.out.print(key + " ");
            }
        }
        System.out.println(hasPrint ? "" : "no such number.");

    }

    private void allCandsMinusOne(HashMap<Integer, Integer> map) {
        List<Integer> removeList = new LinkedList<>();
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            Integer key = set.getKey();
            Integer value = set.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            map.put(key, value - 1);
        }
        for (Integer removeKey : removeList) {
            map.remove(removeKey);
        }
    }

    private HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
        HashMap<Integer, Integer> reals = new HashMap<>();
        for (int i = 0; i != arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                reals.put(curNum, reals.getOrDefault(curNum, 1));
            }
        }
        return reals;
    }


}
