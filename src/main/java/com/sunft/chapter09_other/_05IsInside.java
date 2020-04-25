package com.sunft.chapter09_other;

/**
 * 判断一个点是否在三角形内部
 * 【题目】
 * 在二维坐标系中， 所有的值都是double类型， 那么一个三角形可以由3个点来代表， 给定3个点代表的三角形， 再给定一个点(x， y)，
 * 判断(x， y)是否在三角形中。
 */
public class _05IsInside {

    public double getSideLength(double x1, double y1, double x2, double y2) {
        double a = Math.abs(x1 - x2);
        double b = Math.abs(y1 - y2);
        return Math.sqrt(a * a + b * b);
    }

    public double getArea(double x1, double y1,
                          double x2, double y2,
                          double x3, double y3) {
        double side1Length = getSideLength(x1, y1, x2, y2);
        double side2Length = getSideLength(x1, y1, x3, y3);
        double side3Length = getSideLength(x2, y2, x3, y3);
        double p = (side1Length + side2Length + side3Length) / 2;
        return Math.sqrt((p - side1Length) * (p - side2Length) * (p - side3Length) * p);
    }

    public boolean isInside1(double x1, double y1,
                             double x2, double y2,
                             double x3, double y3,
                             double x, double y) {
        double area1 = getArea(x1, y1, x2, y2, x, y);
        double area2 = getArea(x1, y1, x3, y3, x, y);
        double area3 = getArea(x2, y2, x3, y3, x, y);
        double allArea = getArea(x1, y1, x2, y2, x3, y3);
        return area1 + area2 + area3 <= allArea;
    }

    public double crossProduct(double x1, double y1, double x2, double y2) {
        return x1 * y2 - x2 * y1;
    }

    public boolean isInside2(double x1, double y1, double x2, double y2,
                             double x3, double y3, double x, double y) {
        //如果三角形的点不是逆时针输入，改变一下顺序
        if (crossProduct(x3 - x1, y3 - y1, x2 - x1, y2 - y1) >= 0) {
            double tmpx = x2;
            double tmpy = y2;
            x2 = x3;
            y2 = y3;
            x3 = tmpx;
            y3 = tmpy;
        }
        if (crossProduct(x2 - x1, y2 - y1, x - x1, y - y1) < 0) {
            return false;
        }

        if (crossProduct(x3 - x2, y3 - y2, x - x2, y - y2) < 0) {
            return false;
        }

        if (crossProduct(x1 - x3, y1 - y3, x - x3, y - y3) < 0) {
            return false;
        }
        return true;
    }

}
