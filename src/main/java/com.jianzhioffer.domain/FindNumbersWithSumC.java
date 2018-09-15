package com.jianzhioffer.domain;

import java.util.ArrayList;

/**
 * 和为S的两个数字
 */
public class FindNumbersWithSumC {


    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {

        int max = Integer.MAX_VALUE;

        ArrayList<Integer> integers = new ArrayList<>();


        for (int i = 0; i < array.length; i++) {

            if (array[i] < sum) {

                for (int j = 0; j < array.length; j++) {
                    if (j != i) {
                        if (array[j] + array[i] == sum) {
                            int std = array[j] * array[i];

                            if (std<max) {
                                max = std;
                                integers.clear();
                                integers.add(array[i]);
                                integers.add(array[j]);
                            }
                        }
                        if(array[j] + array[i]>sum) break;
                    }
                }


            }else{
                break;
            }
        }

        return integers;
    }

}
