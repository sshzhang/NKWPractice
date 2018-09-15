package com.jianzhioffer.domain;

/**
 * 判断数组中数字是否连续
 */
public class isContinuousC {

    public boolean isContinuous(int [] numbers) {

        //count0 统计０的个数
        int count0 = 0, j = 0;
        if(numbers.length<=1) return false;
        count0 = numbers[0] == 0 ? 1 : 0;
        for (int i = 1; i < numbers.length; i++) {
            int tempt = numbers[i];
            if(tempt==0) count0++;
            for (j = i - 1; j >= 0 && tempt < numbers[j]; j--){
                numbers[j + 1] = numbers[j];
            }
            numbers[j + 1] = tempt;
        }
        for (int i = count0; i < numbers.length-1; i++) {
            //相同的数字肯定组成不了顺子
            if(numbers[i]==numbers[i+1]) return false;
            count0 -= numbers[i + 1] - numbers[i] - 1;
            if(count0<0) return false;
        }
        return true;
    }


    public static void main(String... args) {

        System.out.println(new isContinuousC().isContinuous(new int[]{0, 3, 2, 6, 4}));
    }
}
