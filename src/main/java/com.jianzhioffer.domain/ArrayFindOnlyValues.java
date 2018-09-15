package com.jianzhioffer.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中找出只出现一次的数字
 */
public class ArrayFindOnlyValues {

    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {

        Map<Integer, Integer> params = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {

            if (params.containsKey(array[i])) {
                params.put(array[i], params.get(array[i]) + 1);
            } else {
                params.put(array[i], 1);
            }
        }

        int index = 1;
        for (Integer sm : params.keySet()) {
            if (params.get(sm) % 2 != 0 && index == 1) {
                num1[0] = sm;
                index++;
            } else if (params.get(sm) % 2 != 0 && index != 1) {
                num2[0] = sm;
                break;
            }
        }

    }

}
