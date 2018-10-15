package com.leetcode.domain;

import java.util.HashMap;

public class maxPointsC {

    public int maxPoints(Point[] points) {

        if(points.length<=2) return points.length;

        int max = 0;

        for (int i = 0; i < points.length; i++) {
            HashMap<Float, Integer> floatIntegerHashMap = new HashMap<>();
            Point startPoint = points[i];
            int chuzhi=0;
            int chonghe = 0;
            for (int j = 0; j < points.length; j++) {
                if(j==i) continue;
                Point endPoint = points[j];
                if (endPoint.x == startPoint.x && endPoint.y == startPoint.y) {
                    chonghe++;
                    continue;
                }
                if (endPoint.x == startPoint.x && endPoint.y != startPoint.y) {
                    chuzhi++;
                    continue;
                }
                float dist = ((endPoint.y - startPoint.y) * 1.0f / (endPoint.x - startPoint.x));
                if (floatIntegerHashMap.get(dist) == null) {
                    floatIntegerHashMap.put(dist, 1);
                } else {
                    floatIntegerHashMap.put(dist, floatIntegerHashMap.get(dist) + 1);
                }
            }
            int tempt =chuzhi;
            for (Float sf : floatIntegerHashMap.keySet()) {
                int integer = floatIntegerHashMap.get(sf);
                tempt = tempt > integer ? tempt : integer;
            }
            max = max > tempt + chonghe  + 1 ? max : tempt + chonghe  + 1;

        }
        return max;

    }


    public static void main(String... args) {

        int i = new maxPointsC().maxPoints(new Point[]{new Point(0, 0), new Point(1, 1), new Point(1 ,- 1)});
        System.out.println(i);

    }


   static class Point {
        int x;
        int y;
        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
