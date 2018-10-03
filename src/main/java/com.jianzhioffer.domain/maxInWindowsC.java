package com.jianzhioffer.domain;

import java.util.ArrayList;

/**
 * 滑动窗口
 *
 */
public class maxInWindowsC {



    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {

        if(num==null) return null;
        if(num.length<size) return null;
        int len = num.length - size + 1;
        ArrayList<Integer> integers = new ArrayList<>();

        for (int j = 1; j < size; j++) {//总共遍历size-1次
            // 每次取出最大值
            for (int i = 0; i < num.length - j; i++) {
                num[i] = Math.max(num[i], num[i + 1]);
            }
        }

        for (int k = 0; k < len; k++) {
            integers.add(num[k]);
        }
        return integers;
    }

    public static void main(String... args) {

        new maxInWindowsC().maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3);

    }
}


