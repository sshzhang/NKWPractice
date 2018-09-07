package com.jianzhioffer.domain;

/**
 * 旋转数组的最小数字
 */
public class xuanzhuangminnumber {

    public int minNumberInRotateArray(int [] array) {

        if (array == null || array.length == 0) {
            return 0;
        }
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) result = array[i];
        }
        return result;
    }

}
