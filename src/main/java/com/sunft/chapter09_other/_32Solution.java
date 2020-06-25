package com.sunft.chapter09_other;

/**
 * 丢棋子问题
 * <p>
 * 【题目】
 * 一座大楼有0～N层， 地面算作第0层， 最高的一层为第N层。 已
 * 知棋子从第0层掉落肯定不会摔碎， 从第i层掉落可能会摔碎， 也可能
 * 不会摔碎(1≤i≤N)。 给定整数N作为楼层数， 再给定整数K作为棋子
 * 数， 返回如果想找到棋子不会摔碎的最高层数， 即使在最差的情况下
 * 扔的最少次数。 一次只能扔一个棋子。
 * <p>
 * 【举例】
 * N=10， K=1。
 * 返回10。 因为只有1棵棋子， 所以不得不从第1层开始一直试到第
 * 10层， 在最差的情况下， 即第10层是不会摔坏的最高层， 最少也要扔10次。
 * <p>
 * N=3， K=2。
 * 返回2。 先在2层扔1棵棋子， 如果碎了， 试第1层， 如果没碎， 试第3层。
 * <p>
 * N=105， K=2
 * 返回14。第一个棋子先在14层扔， 碎了则用仅存的一个棋子试1～13。
 * 若没碎， 第一个棋子继续在27层扔， 碎了则用仅存的一个棋子试15～26。
 * 若没碎， 第一个棋子继续在39层扔， 碎了则用仅存的一个棋子试28～38。
 * 若没碎， 第一个棋子继续在50层扔， 碎了则用仅存的一个棋子试40～49。
 * 若没碎， 第一个棋子继续在60层扔， 碎了则用仅存的一个棋子试51～59。
 * 若没碎， 第一个棋子继续在69层扔， 碎了则用仅存的一个棋子试61～68。
 * 若没碎， 第一个棋子继续在77层扔， 碎了则用仅存的一个棋子试70～76。
 * 若没碎， 第一个棋子继续在84层扔， 碎了则用仅存的一个棋子试78～83。
 * 若没碎， 第一个棋子继续在90层扔， 碎了则用仅存的一个棋子试85～89。
 * 若没碎， 第一个棋子继续在95层扔， 碎了则用仅存的一个棋子试91～94。
 * 若没碎， 第一个棋子继续在99层扔， 碎了则用仅存的一个棋子试96～98。
 * 若没碎， 第一个棋子继续在102层扔， 碎了则用仅存的一个棋子试100、 101。
 * 若没碎， 第一个棋子继续在104层扔， 碎了则用仅存的一个棋子试103。
 * 若没碎， 第一个棋子继续在105层扔， 若到这一步还没碎， 那么105便是结果。
 */
public class _32Solution {

    public int solution1(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        return process1(nLevel, kChess);
    }

    private int process1(int nLevel, int kChess) {
        if (nLevel == 0) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i != nLevel + 1; i++) {
            if (i == nLevel) {
                min = Math.min(min, Math.max(process1(i - 1, kChess - 1), process1(nLevel - i, kChess)));
            }
        }
        return min + 1;
    }

    public int solution2(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i != dp.length; i++) {
            for (int j = 2; j != dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k != i + 1; k++) {
                    min = Math.min(min, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

    public int solution3(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[] preArr = new int[nLevel + 1];
        int[] curArr = new int[nLevel + 1];
        for (int i = 1; i != curArr.length; i++) {
            curArr[i] = 1;
        }
        for (int i = 1; i != kChess; i++) {
            int[] tmp = preArr;
            preArr = curArr;
            curArr = tmp;
            for (int j = 1; j != curArr.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k != j + 1; k++) {
                    min = Math.min(min, Math.max(preArr[k - 1], curArr[j - k]));
                }
                curArr[j] = min + 1;
            }
        }
        return curArr[curArr.length - 1];
    }

    public int solution4(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        int[] cands = new int[kChess + 1];
        for (int i = 1; i != dp[0].length; i++) {
            dp[1][i] = 1;
            cands[i] = 1;
        }
        for (int i = 2; i < nLevel + 1; i++) {
            for (int j = kChess; j > 1; j--) {
                int min = Integer.MAX_VALUE;
                int minEnum = cands[j];
                int maxEnum = j == kChess ? i / 2 + 1 : cands[j + 1];
                for (int k = minEnum; k < maxEnum + 1; k++) {
                    int cur = Math.max(dp[k - 1][j - 1], dp[i - k][j]);
                    if (cur <= min) {
                        min = cur;
                        cands[j] = k;
                    }
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

    public int solution5(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        int bsTimes = log2N(nLevel) + 1;
        if (kChess >= bsTimes) {
            return bsTimes;
        }
        int[] dp = new int[kChess];
        int res = 0;
        while (true) {
            res++;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= nLevel) {
                    return res;
                }
            }
        }
    }

    private int log2N(int n) {
        int res = -1;
        while (n != 0) {
            res++;
            n >>>= 1;
        }
        return res;
    }

}
