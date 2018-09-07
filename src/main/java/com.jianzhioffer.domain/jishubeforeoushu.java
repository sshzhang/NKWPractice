package com.jianzhioffer.domain;

/**
 * 调整数组顺序使奇数位于偶数前面
 */
public class jishubeforeoushu {

    public void reOrderArray(int [] array) {

        int[] jishu = new int[array.length];
        int jCount = 0, oCount = 0;
        int[] oushu = new int[array.length];
        for (int i=0;i<array.length;i++) {
            if(array[i]%2!=0) jishu[jCount++]=array[i];
            else oushu[oCount++]=array[i];
        }

        for (int j=0;j<array.length;j++) {
            if(j<jCount) array[j]=jishu[j];
            else array[j]=oushu[j-jCount];
        }



    }




}
