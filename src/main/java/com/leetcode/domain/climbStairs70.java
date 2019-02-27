package com.leetcode.domain;

public class climbStairs70 {

    public int climbStairs(int n) {

        if(n<=0) return 0;
        if(n==1||n==2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
