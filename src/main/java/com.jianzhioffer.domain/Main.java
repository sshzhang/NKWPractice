package com.jianzhioffer.domain;

public class Main {

    public static void main(String... args) {

        int[] datas = new int[]{7, 1, 5, 3, 6, 4};
        int max = Integer.MIN_VALUE;
        for (int i=0;i<datas.length;i++) {
            for (int j=i+1;j<datas.length;j++) {
                if(datas[j]-datas[i]>max) max = datas[j] - datas[i];
            }
        }
        System.out.println(max);
    }

}
