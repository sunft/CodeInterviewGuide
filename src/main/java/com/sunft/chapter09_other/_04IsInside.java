package com.sunft.chapter09_other;

/**
 * 判断一个点是否在矩形内部
 * <p>
 * 【题目】
 * 在二维坐标系中， 所有的值都是double类型， 那么一个矩形可以
 * 由4个点来代表， (x1， y1)为最左的点、 (x2， y2)为最上的点、
 * (x3， y3)为最下的点、 (x4， y4)为最右的点。 给定4个点代表的矩形，
 * 再给定一个点(x， y)， 判断(x， y)是否在矩形中。
 */
public class _04IsInside {

    /**
     * 矩形平行于坐标轴的情况
     *
     * @param x1
     * @param y1
     * @param x4
     * @param y4
     * @param x
     * @param y
     * @return
     */
    public boolean isInside(double x1, double y1, double x4, double y4,
                            double x, double y) {
        if (x <= x1) {
            return false;
        }

        if (x >= x4) {
            return false;
        }

        if (y >= y1) {
            return false;
        }

        return y > y4;
    }

    public boolean isInside(double x1, double y1, double x2, double y2,
                            double x3, double y3, double x4, double y4, double x, double y) {
        if (y1 == y2) {
            return isInside(x1, y1, x4, y4, x, y);
        }

        double l = Math.abs(y4 - y3);
        double k = Math.abs(x4 - x3);
        double s = Math.sqrt(k * k + l * l);
        double sin = l / s;
        double cos = k / s;
        double x1R = cos * x1 + sin * y1;
        double y1R = -x1 * sin + y1 * cos;
        double x4R = cos * x4 + sin * y4;
        double y4R = -x4 * sin + y4 * cos;
        double xR = cos * x + sin * y;
        double yR = -x * sin + y * cos;
        return isInside(x1R, y1R, x4R, y4R, xR, yR);
    }

}
