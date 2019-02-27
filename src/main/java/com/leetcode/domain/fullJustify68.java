package com.leetcode.domain;

import java.util.List;

public class fullJustify68 {

    public List<String> fullJustify(String[] words, int maxWidth) {


        return null;
    }


    public int mySqrt(int x) {

        if(x==1||x==0) return x;

        for (int i = 1; i <= x / 2; i++) {
            if(i*i>=x) return i;
        }

        return -1;
    }

}
