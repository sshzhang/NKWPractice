package com.leetcode.domain;

import java.util.Stack;

public class largestRectangleArea84 {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0)    return 0;
        //最暴力的方法
        int maxValue = heights[0];
        int[][] dp = new int[heights.length][heights.length];
        int lowPartValue = heights[0];
        for (int i = 0; i < heights.length; i++) {
            dp[i][i] = heights[i];
        }

        for (int k = 0; k < dp.length; k++) {
            lowPartValue = heights[k];
            for (int j = k + 1; j < dp.length; j++) {
                //获取最小值
                lowPartValue = lowPartValue > heights[j] ? heights[j] : lowPartValue;
                //求出最小值对应的面积
                int before = lowPartValue * (j + 1-k);
                //取最大面积
                dp[k][j] = heights[j] > before ? heights[j] : before;
                //更新最大值
                maxValue = maxValue > dp[k][j] ? maxValue : dp[k][j];
            }
        }
        return maxValue;
    }


    //通过局部峰值 来求解
        public int largestRectangleAreaU(int[] heights) {
            int maxValue = 0;
            if(heights==null||heights.length==0) return 0;
            for (int k = 0; k < heights.length; k++) {
                if (k + 1 < heights.length && heights[k] < heights[k + 1]) {
                    continue;
                }
                //剪枝
                int currValue = heights[k];
                for (int j = k; j >= 0; j--) {
                    currValue = currValue > heights[j] ? heights[j] : currValue;
                    int area = currValue * (k -j  + 1);
                    maxValue = area > maxValue ? area : maxValue;
                }
            }
        return maxValue;
    }

    //思路好理解 [1,2,5,6,7,5]找出递增的序列[1,2,5,6,7]，然后 取出影响递增的第一个元素[5], pop递增序列中大于5的数据 7, 1*7=7, 6, 6*2=12,
    // 原来大于5的位置用5来填充 [1,2,5,5,5,5]  计算pop 5*1 5*2 5*3 5*4 2*5 1*6
    public int largestRectangleAreaUStack(int heights[]) {

        Stack<Integer> stack = new Stack<>();
        if(heights==null||heights.length==0) return 0;
        int maxValue = heights[0];
        for (int k = 0; k < heights.length; k++) {

            if (stack.isEmpty() || stack.peek() <= heights[k]) {
                stack.push(heights[k]);
            }else{//不满足单调
                int count = 0;//统计个数
                while (!stack.isEmpty()&&stack.peek() > heights[k]) {
                    count++;
                    int area = count * stack.pop();
                    maxValue = maxValue > area ? maxValue : area;
                }

                while (count-- != 0) stack.push(heights[k]);
                stack.push(heights[k]);
            }
        }
        int count = 1;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            maxValue = maxValue > curr * count ? maxValue : curr * count;
            count++;
        }
        return maxValue;
    }


    public static void main(String... args) {
        largestRectangleArea84 area84 = new largestRectangleArea84();
        int i = area84.largestRectangleAreaUStack(new int[]{1, 1});
        System.out.println(i);
    }
}
