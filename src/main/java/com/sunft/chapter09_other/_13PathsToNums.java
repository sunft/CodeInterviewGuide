package com.sunft.chapter09_other;

/**
 * 路径数组变为统计数组
 * 【题目】
 * 给定一个路径数组paths， 表示一张图。 paths[i]==j代表城市i连向
 * 城市j， 如果paths[i]==i， 则表示i城市是首都， 一张图里只会有一个
 * 首都且图中除首都指向自己之外不会有环。 例如， paths=[9， 1， 4，
 * 9， 0， 4， 8， 9， 0， 1]， 代表的图如图9-6所示。
 * <p>
 * 1
 * ^
 * |
 * 9
 * ^
 * /|\
 * / | \
 * 0  3  7
 * ^
 * / \
 * /   \
 * 4     8
 * ^     ^
 * / \     \
 * 2   5    6
 * 由数组表示的图可以知道， 城市1是首都， 所以距离为0， 离首都
 * 距离为1的城市只有城市9， 离首都距离为2的城市有城市0、 3和7， 离
 * 首都距离为3的城市有城市4和8， 离首都距离为4的城市有城市2、 5和
 * 6。 所以距离为0的城市有1座， 距离为1的城市有1座， 距离为2的城市
 * 有3座， 距离为3的城市有2座， 距离为4的城市有3座。 那么统计数组
 * 为nums=[1， 1， 3， 2， 3， 0， 0， 0， 0， 0]， nums[i]==j代表距离为i的城市有j座。 要求实现一个void类型的函数， 输入一个路径数组
 * paths， 直接在原数组上调整， 使之变为nums数组， 即paths=[9， 1，
 * 4， 9， 0， 4， 8， 9， 0， 1]经过这个函数处理后变成[1， 1， 3， 2，
 * 3， 0， 0， 0， 0， 0]。
 * 【要求】
 * 如果paths长度为N， 请达到时间复杂度为O(N)， 额外空间复杂度
 * 为O(1)。
 */
public class _13PathsToNums {

    public void pathsToNums(int[] paths) {
        if (paths == null || paths.length == 0) {
            return;
        }

        //citiesPath -> distancesArray
        pathsToDistances(paths);

        // distancesArray -> numArray
        distancesToNums(paths);
    }

    private void pathsToDistances(int[] paths) {
        int cap = 0;
        for (int i = 0; i != paths.length; i++) {
            if (paths[i] == i) {
                cap = i;
            } else if (paths[i] > -1) {
                int curI = paths[i];
                paths[i] = -1;
                int preI = i;
                while (paths[curI] != curI) {
                    if (paths[curI] > -1) {
                        int nextI = paths[curI];
                        paths[curI] = preI;
                        preI = curI;
                        curI = nextI;
                    } else {
                        break;
                    }
                }
                int value = paths[curI] == curI ? 0 : paths[curI];
                while (paths[preI] != -1) {
                    int lastPreI = paths[preI];
                    paths[preI] = --value;
                    curI = preI;
                    preI = lastPreI;
                }
                paths[preI] = --value;
            }
        }
        paths[cap] = 0;
    }

    private void distancesToNums(int[] disArr) {
        for (int i = 0; i != disArr.length; i++) {
            int index = disArr[i];
            if (index < 0) {
                disArr[i] = 0; //重要
                while (true) {
                    index = -index;
                    if (disArr[index] > -1) {
                        disArr[index] ++;
                        break;
                    } else {
                        int nextIndex = disArr[index];
                        disArr[index] = 1;
                        index = nextIndex;
                    }
                }
            }
        }
        disArr[0] = 1;
    }

}
