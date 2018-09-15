package com.jianzhioffer.domain;

import java.util.ArrayList;

/**
 * 和为S的连续正数序列
 *
 * 找出和为S的所有连续正数序列
 *
 * 其实通过两个元素　　三个元素　　到　　最多是从1加到ｎ个元素和最接近S
 */
public class lianxuSumk {

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if(sum==0) return arrayLists;
        int maxindex = (int) ((Math.sqrt(1 + 8 * sum) - 1) / 2);
        for (int i = maxindex; i >=2 ; i--) {

            int cvalue = (i - 1) * (1 + i - 1) / 2;
            cvalue = sum +cvalue;
            if (cvalue % i == 0) {//存在一个连续序列满足条件

                ArrayList<Integer> integers = new ArrayList<>();
                for (int j = i; j >=1; j--) {
                    integers.add(cvalue / i - (j - 1));
                }
                arrayLists.add(integers);
            }
        }

        return arrayLists;
    }

    public static void main(String... args) {

        new lianxuSumk().FindContinuousSequence(3);
    }
}
