package com.leetcode.domain;

/**
 * 二分查找方法解决开方问题
 */
public class mySqrtC69 {
    public  int mySqrt(int x) {
        if(x==0||x==1) return  x;
        int left = 1, right = x;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (x / mid < mid) {
                right = mid-1;
            } else {
                left = mid + 1;
            }
        }
        return left-1;
    }


    //牛顿法
    public boolean isNewTownMethod(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }

    public boolean isPerfectSquare(int num) {
        if(num<0) return false;
        if(num==1||num==0) return true;
        int row = 1, high = num;
        while (row <= high) {
            long mid = (row + high) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                row = (int) (mid + 1);
            } else {
                high = (int) (mid - 1);
            }
        }
        return false;
    }

    public static void main(String... args) {
        System.out.println(new mySqrtC69().isNewTownMethod(808201));
    }
}
