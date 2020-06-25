package com.leetcode.domain;

import java.util.HashSet;
import java.util.Stack;

/**
 * 水壶问题
 */
public class WaterKettleProblem365 {

    static class Point {
        int remainX;
        int remainY;

        public Point(int remainX, int remainY) {
            this.remainX = remainX;
            this.remainY = remainY;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(remainX)+Integer.hashCode(remainY);
        }

        @Override
        public boolean equals(Object obj) {
            Point point = (Point) obj;
            return point.remainX == this.remainX && point.remainY == this.remainY;
        }
    }

    // 贝祖定理 ax+by=z 存在z是a,b的最大公约数的倍数
    public boolean canMesureWater1(int x, int y, int z){
        if (x + y < z) return false;
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        int tmpt = -1;
        if (y > x) { // 始终保持x>=y
            tmpt = x;
            x = y;
            y = tmpt;
        }
        while ((tmpt = x % y) != 0) {
            x = y;
            y = tmpt;
        }
        return z % y == 0;
    }

    /**
     * 深度优先遍历
     * @param x
     * @param y
     * @param z
     * @return
     */
    public  boolean canMeasureWater2(int x, int y, int z) {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(0, 0));
        HashSet<Point> allVisitedDatas = new HashSet<>();
        while (!stack.isEmpty()) {
            Point point = stack.pop();
            int remainX = point.remainX;
            int remainY = point.remainY;

            if (remainX + remainY == z) return true;

            if (allVisitedDatas.contains(point)) continue;
            else allVisitedDatas.add(point);

            // X倒满
            stack.push(new Point(x, remainY));
            // X倒空
            stack.push(new Point(0, remainY));
            // Y倒满
            stack.push(new Point(remainX, y));
            // Y倒空
            stack.push(new Point(remainX, 0));
            // X倒到Y
            stack.push(new Point(remainX - Math.min(remainX, y - remainY), remainY + Math.min(remainX, y - remainY)));
            // Y倒到X
            stack.push(new Point(remainX + Math.min(x - remainX, remainY), remainY - Math.min(remainY, x - remainX)));
        }
        return false;
    }
}
