package com.leetcode.domain;

import java.util.Arrays;

public class plusOne66 {


    public int[] plusOne(int[] digits) {


        int len = digits.length - 1;
        int position = 0;
        int add = 1;
        String dest = "";
        for (int i = len; i >= 0; i--) {
            add = i == len ? 1 : 0;
            dest= (position + digits[i] + add) % 10+dest;
            position = (position + digits[i] + add) / 10;
        }
        if(position==1) dest =1+dest;


        int[] sm = new int[dest.length()];
        for (int j = 0; j < dest.length(); j++) {
            sm[j] = Integer.valueOf(dest.charAt(j)+"");
        }
        return sm;
    }


}
