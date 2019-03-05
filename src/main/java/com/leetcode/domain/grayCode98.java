package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class grayCode98 {


    public List<Integer> grayCode(int n) { //分别以 2 的0------n-1次方进行分割

        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        for (int i = 0, k=1; i < n; i++) {
            k = i >= 1 ? k * 2 : k;
            int len = integers.size();
            for (int j = len - 1; j >= 0; j--) {
                integers.add(integers.get(j) + k);
            }
        }
        return integers;
    }

    public static void main(String... args) {
        grayCode98 code = new grayCode98();
        System.out.println(code.grayCode(4));
    }


}
