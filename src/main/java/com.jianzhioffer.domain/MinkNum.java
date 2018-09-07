package com.jianzhioffer.domain;


import java.util.ArrayList;

/**
 * 从数组中选取k个最小的数据
 *
 * 通过构造大顶堆来实现
 */
public class MinkNum {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        ArrayList<Integer> params = new ArrayList<>();
        if(input.length<k) return null;
        if(input.length==k){
            for (int i = 0; i < input.length; i++) params.add(input[i]);
            return params;
        }

        //构建一个元素个数为k的大顶堆
       /* for (int j=k/2-1;j>=0;j--) {
            HeapAdjust(input, j, k - 1);
        }
        for (int i = k; i < input.length; i++) {
            //查看后面的数据和大顶堆中第一个元素比较一下
            if(input[i]<input[0]) {
                int tempt = input[i];
                input[i] = input[0];
                input[0] = tempt;
                HeapAdjust(input, 0, k - 1);
            }
        }
        */


       //构建小顶堆
       for (int j=k/2-1;j>=0;j--) {
           HeapSmallAdjust(input, j, k - 1);
       }

       for (int i=k;i<input.length;i++) {

           if(input[i]>input[0]){  //大于最小值替换  调整堆
               int tempt = input[i];
               input[i] = input[0];
               input[0] = tempt;
               HeapSmallAdjust(input, 0, k - 1);
           }

       }





        for (int i=0; i<k;i++) {
            params.add(input[i]);
        }
        return params;
    }


    //大顶堆
    public void HeapAdjust(int[] input, int s, int m) {
        int result = input[s];
        for (int j=2*s+1;j<=m;j=2*j+1) {
            if(j<m&&input[j]<=input[j+1]) ++j;
            if(result>=input[j]) break;
          input[s]=input[j];s = j;
        }
        input[s] = result;
    }


    //小顶堆
    public void HeapSmallAdjust(int[] input, int s, int m) {

        int result = input[s];
        for (int i=2*s+1;i<=m;i=2*i+1) {
            if(i<m&&input[i]>=input[i+1]) ++i;
            if(result<=input[i]) break;
           input[s]=input[i];
            s = i;
        }
        input[s] = result;
    }


    public static void main(String... args) {
//        System.out.println(new MinkNum().GetLeastNumbers_Solution(new int[]{8, 7, 6, 5, 4, 3, 2, 1}, 5));

        String sm = "sn  st sg ";
        System.out.println(sm.split("[ ]+").length);
    }

}



