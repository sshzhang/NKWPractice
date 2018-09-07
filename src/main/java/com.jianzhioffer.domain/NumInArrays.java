package com.jianzhioffer.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中出现次数超过一半的数字
 */
public class NumInArrays {

    public static final Map<Integer, Integer> counts = new HashMap<>();

    public int MoreThanHalfNum_Solution(int [] array) {

        counts.clear();
        int key = 0;
        int value = 0;
        for (int i=0;i<array.length;i++) {
            if (counts.containsKey(array[i])) {
                counts.put(array[i], counts.get(array[i]) + 1);
            } else {
                counts.put(array[i], 1);
            }
            if(value<counts.get(array[i])){
                value = counts.get(array[i]);
                key = array[i];
            }
        }
        return value > array.length / 2 ? key : 0;
    }

    public static void main(String... args) {
        System.out.println(new NumInArrays().MoreThanHalfNum_Solution(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

}
