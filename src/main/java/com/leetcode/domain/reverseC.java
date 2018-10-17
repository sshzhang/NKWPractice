package com.leetcode.domain;

/**
 * 整型反转
 */
public class reverseC {

    public int reverse(int x) {

        long flage = x > 0 ? 1 : -1;
        long nx = 0;
        x = x > 0 ? x : -x;
        while (x !=0) {
            nx = 10 * nx + x % 10;
            x = x / 10;
        }
        nx = nx * flage;
        if (nx > Integer.MAX_VALUE || nx < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) nx;
    }


    public static void main(String... args) {
        int reverse = new reverseC().reverse(1534236469);
        System.out.println(reverse);
    }

}
