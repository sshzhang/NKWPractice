package com.jianzhioffer.domain;

import java.util.Scanner;

/**
 * 招商银行第二道题目
 * <p>
 * 求出 买入卖出的最大值
 * <p>
 * 7 1 5 3 6 4
 * <p>
 * 5
 */
public class zhaoshangBank2 {

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split("  ");
        int num[] = new int[split.length];
        for (int i = 0; i < num.length; i++) num[i] = Integer.valueOf(split[i]);

        int min = num[0];
        int max = Integer.MIN_VALUE;
        for (int j=1;j<num.length;j++) {
            int v = num[j] - min;
            max = max > v ? max : v;
            min = min > num[j] ? num[j] : min;
        }
        System.out.println(max);
    }



}
