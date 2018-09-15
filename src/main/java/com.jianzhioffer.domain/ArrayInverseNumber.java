package com.jianzhioffer.domain;

/**
 * 数组中逆序对数问题
 *
 */
public class ArrayInverseNumber {

    public int InversePairs(int[] array) {

        if(array==null||array.length==1) return 0;

        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++)
            copy[i] = array[i];
        return  DepthVisitedTimes(array, copy, 0, array.length-1);
    }

    /**
     * 通过归并排序 来求解逆序对问题  首先标准的归并分割  最后合并的时候需要计算每一个子数组中的逆序对个数 同事两个子数组之间的逆序对数
     *
     * @param array  原始数组
     * @param copy 合并之后的辅助数组 递增排序
     * @param row
     * @param high
     * @return
     */
    public int DepthVisitedTimes(int[] array, int[] copy, int row, int high) {

        if(row==high) return 0;

        int mid = (row + high) >> 1;

        int left = DepthVisitedTimes(array, copy, row, mid)%1000000007;

        int right = DepthVisitedTimes(array, copy, mid + 1, high)%1000000007;

        int i = mid;

        int j = high;

        int count = 0;

        int cindex = high;

        while (i >= row && j >= mid + 1) {

            if (array[i] > array[j]) {
                copy[cindex--] = array[i--];
                count += j - mid;
                if(count>=1000000007)//数值过大求余
                {
                    count%=1000000007;
                }
            }else{
                copy[cindex--] = array[j--];
            }
        }

        for (; i >= row; i--) {
            copy[cindex--] = array[i];
        }

        for (; j >= mid + 1; j--) {
            copy[cindex--] = array[j];
        }

        //把辅助数组中有序数据赋值给 array
        for (int k = row; k <= high; k++) {
            array[k] = copy[k];
        }

        return (left + right + count)%1000000007;
    }

    public static void main(String... args) {
        System.out.println(new ArrayInverseNumber().InversePairs(new int[]{1, 2, 3, 4, 5, 6, 7, 0}));
    }
}
