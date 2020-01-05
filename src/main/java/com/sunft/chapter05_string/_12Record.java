
package com.sunft.chapter05_string;

import java.util.HashMap;
import java.util.Map;

/**
 * 进阶题目
 */
public class _12Record {

    private Map<String, HashMap<String, Integer>> record;

    public _12Record(String[] strArr) {
        record = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i != strArr.length; i ++) {
            String curStr = strArr[i];
            update(indexMap, curStr, i);
            indexMap.put(curStr, i);
        }
    }

    private void update(Map<String, Integer> indexMap, String str, int i) {
        if (!record.containsKey(str)) {
            record.put(str, new HashMap<>());
        }
        Map<String, Integer> strMap = record.get(str);
        for (Map.Entry<String ,Integer> lastEntry : indexMap.entrySet()) {
            String key = lastEntry.getKey();
            int index = lastEntry.getValue();
            if (!key.equals(str)) {
                Map<String, Integer> lastMap = record.get(key);
                int curMin = i - index;
                if (strMap.containsKey(key)) {
                    int preMin = strMap.get(key);
                    if (curMin < preMin) {
                        strMap.put(key, curMin);
                        lastMap.put(str, curMin);
                    }
                } else {
                    strMap.put(key, curMin);
                    lastMap.put(str, curMin);
                }
            }
        }
    }

    public int minDistance(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }

        if (str1.equals(str2)) {
            return 0;
        }

        if (record.containsKey(str1) && record.get(str1).containsKey(str2)) {
            return record.get(str1).get(str2);
        }
        return -1;
    }

}
