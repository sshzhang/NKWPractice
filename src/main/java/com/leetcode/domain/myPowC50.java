package com.leetcode.domain;

public class myPowC50 {

    /**
     * 其实就是模拟　pow(x,n)
     * 不过需要记住的是 n == Integer.MIN_VALUE情況
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {

        if (n == 0) return 1;
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                return 1 / (x * myPow(x, Integer.MAX_VALUE));
            } else {
                n = -n;
            }
            x = 1 / x;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);

    }
}
