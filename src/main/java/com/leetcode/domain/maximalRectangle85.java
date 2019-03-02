package com.leetcode.domain;

import java.util.Stack;

public class maximalRectangle85 {

    public int maximalRectangle(char[][] matrix) {

        int row = matrix == null ? -1 : matrix.length;
        int col = matrix.length == 0 ? 0 : matrix[0].length;
        //把每一行转换为84题中求最大面积问题。
        int[] data = new int[col];
        int maxValue = 0;
        for (int k = 0; k < row; k++) {
            for (int j = 0; j < col; j++)
                data[j]=matrix[k][j]=='0'?0:(data[j]+1);
            maxValue = Math.max(largestRectangleArea(data), maxValue);
        }
        return maxValue;
    }


    public int largestRectangleArea(int datas[]) {

        //重新写一遍84题中面积最大问题， 通过局部最大值来实现计算。
        int maxValue = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < datas.length; i++) {

            if (stack.isEmpty() || stack.peek() <=datas[i]) {
                stack.push(datas[i]);
            }else{//出现非单调数据

                int count = 0;
                while (!stack.isEmpty() && stack.peek() > datas[i]) {

                    int curr = stack.pop();
                    count++;
                    maxValue = Math.max(curr * count, maxValue);
                }

                while (count-- > 0) {
                    stack.push(datas[i]);
                }
                stack.push(datas[i]);
            }
        }

        int count = 0;

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            count++;
            maxValue = Math.max(curr * count, maxValue);
        }
        return maxValue;
    }




    public int maximalRectangleDP(char[][] matrix) {

        int row = matrix == null ? -1 : matrix.length;
        int col = matrix.length == 0 ? 0 : matrix[0].length;
        int[] left = new int[col]; //从左到右最晚遇到零
        int[] right = new int[col];//从右到左最早遇到零
        int[] height = new int[col];//高度
        //设置右边界
        for (int i = 0; i < col; i++) {
            right[i] = col;
        }
        int maxValue = 0;
        //针对每一行
        for (int i = 0; i < row; i++) {
            int leftBound = 0;//左边界的位置
            int rightBound = col;//右边界的位置
            for (int j = 0; j < col; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : (height[j] + 1);
            }
            for (int m = 0; m < col; m++) {
                if (matrix[i][m] == '0') { //左边界 可能在m+1的位置
                    left[m] = 0;
                    leftBound = m + 1;
                }else{//原来左边界的位置和leftBound比较取最大值
                    left[m] = Math.max(leftBound, left[m]);
                }
            }

            for (int n = col - 1; n >= 0; n--) {

                if (matrix[i][n] == '0') {//右边界可能取n 为什么不是n-1， 因为方便计算  3-2其实是2因为包括3这个位置
                    rightBound = n;
                    right[n] = col;

                } else {
                    right[n] = Math.min(rightBound, right[n]);
                }
            }


            for (int k = 0; k < col; k++) {
                maxValue = Math.max(maxValue, (right[k] - left[k]) * height[k]);
            }
        }

        return maxValue;
    }

    public static void main(String... args) {

        maximalRectangle85 rectangle85 = new maximalRectangle85();

        char[][] chars = new char[4][5];
        chars[0] = new char[]{'1', '0', '1', '0', '0'};
        chars[1] = new char[]{'1', '0', '1', '1', '1'};
        chars[2] = new char[]{'1', '1', '1', '1', '1'};
        chars[3] = new char[]{'1', '0', '0', '1', '0'};

        System.out.println(rectangle85.maximalRectangleDP(chars));
    }
}
