package com.leetcode.domain;

/**
 * 容器中包含最多的水
 */
public class maxAreaC {

    public int maxArea(int[] height) {


        int maxVal = 0;
        int tmpt = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                tmpt = height[i] > height[j] ? height[j] : height[i];
                tmpt = tmpt * j - i;
                maxVal = maxVal > tmpt ? maxVal : tmpt;
            }
        }
        return maxVal;
    }


    /**
     * 通过两个指针 一个指向初始位置　　一个指向最后位置
     * 容量大小由最小值决定
     * @param height
     * @return
     */
    public int maxAreaU(int[] height) {

        int p1 = 0;
        int max = 0;
        int p2 = height.length - 1;

        while (p1 != p2) {

            if (height[p1] > height[p2]) {
                max = max < height[p2] * (p2 - p1) ? height[p2] * (p2 - p1) : max;
                p2--;
            }else{

                max = max < height[p1] * (p2 - p1) ? height[p1] * (p2 - p1) : max;
                p1++;
            }
        }

        return max;
    }
}
