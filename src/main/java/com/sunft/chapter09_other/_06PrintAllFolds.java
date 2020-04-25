package com.sunft.chapter09_other;

/**
 * 折纸问题
 * <p>
 * 【题目】
 * 请把一段纸条竖着放在桌子上， 然后从纸条的下边向上方对折1次， 压出折痕后展开。 此时折痕是凹下去的， 即折痕突起的方向指向
 * 纸条的背面。 如果从纸条的下边向上方连续对折2次， 压出折痕后展开， 此时有三条折痕， 从上到下依次是下折痕、 下折痕和上折痕。 给
 * 定一个输入参数N， 代表纸条都从下边向上方连续对折N次， 请从上到下打印所有折痕的方向。
 * <p>
 * 例如： N=1时， 打印：
 * down
 * <p>
 * N=2时， 打印：
 * down
 * down
 * up
 */
public class _06PrintAllFolds {

    public void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    private void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "down " : "up ");
        printProcess(i + 1, N, false);
    }

}
