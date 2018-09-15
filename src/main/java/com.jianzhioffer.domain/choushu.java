package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 丑数问题
 */
public class choushu {


   /* public int GetUglyNumber_Solution(int index) {

        int[] pa = new int[]{1, 2, 3,4,5,6,8};
        int count = pa.length;
        int result= 8,tempt;

        if (index <= pa.length) return pa[index - 1];
        else{
            while (count < index) {

                result++;
                tempt = result;
                for (int i = 1; i < 4; ) {

                    while (tempt % pa[i] == 0) {
                        tempt = tempt / pa[i];
                    }
                    i++;
                }

                if (tempt == 1) {
                    count++;
                }
            }
        }
        return result;
    }*/



    public int GetUglyNumber_Solution(int index) {


        List<Integer> params = new ArrayList<Integer>();

        params.add(1);

        int t2 = 0, t3 = 0, t5 = 0;

        for (int i = 1; i < index; ++i) {
            int min = Math.min(params.get(t2) * 2, Math.min(params.get(t3) * 3, params.get(t5) * 5));
            params.add(min);
            if(min==params.get(t3)*3) t3++;
            if(min==params.get(t2)*2) t2++;
            if(min==params.get(t5)*5) t5++;
        }

        return params.get(index - 1);
    }

    public static void main(String... args) {

        System.out.println(new choushu().GetUglyNumber_Solution(220));
    }



}
