package com.jianzhioffer.domain.order;

/**
 * 归并排序算法
 */
public class ReclusiveSplitter {


    public static void main(String... args) {

        int[] pa = new int[]{6, 5, 4, 3};
        new ReclusiveSplitter().DepthOrderReclusive(pa, new int[]{1, 2, 3, 4}, 0, 3);

        System.out.println(pa);
    }

    public void DepthOrderReclusive(int[] array, int[] copy, int row, int high) {

        if (row == high) return;
        int mid = (row + high) >> 1;
        DepthOrderReclusive(array, copy, row, mid);
        DepthOrderReclusive(array, copy, mid + 1, high);
        int i = row;
        int j = mid + 1;
        int cindex = row;
        //每组元素的合并
        while (i <= mid && j <= high) {
            if (array[i] < array[j]) {
                copy[cindex++] = array[i++];
            } else {
                copy[cindex++] = array[j++];
            }
        }

        for (; i <= mid; i++) copy[cindex++] = array[i];

        for (; j <= high; j++) copy[cindex++] = array[j];

        //把数组排序好的更新到array
        for (int k = row; k <= high; k++) {
            array[k] = copy[k];
        }
    }

}
