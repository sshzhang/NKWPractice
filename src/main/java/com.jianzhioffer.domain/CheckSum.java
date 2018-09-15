package com.jianzhioffer.domain;

public class CheckSum {

    /**
     * 计算数组中数据之后为sum的两个数字
     * @param array
     * @param sum
     */
    public void findTheSum(int array[], int sum) {

        //Arrays.sort(array);

        int[] copy = new int[array.length];

        MergeOrder(array, copy, 0, array.length - 1);
//        fastOrder(array, 0, array.length - 1);
        System.out.println(array);
        int row = 0, high = array.length - 1;
        while (row < high) {

            if (array[row] + array[high] > sum) {
                high--;
            } else if (array[row] + array[high] < sum) {
                row++;
            } else {
                System.out.println(array[row] + " " + array[high]);
                if (high - 1 >= 0 && array[high] == array[high - 1]) {
                    high--;
                    continue;
                }

                if (high - 1 >= 0 && array[row] == array[row + 1]) {
                    row++;
                    continue;
                }

                row++;
                high--;
            }

        }
    }


    //小堆排序
    public void duiSortedOrder(int array[], int index,int len) {

        int tempt = array[index];
        for (int i = 2 * index + 1; i <= len; i = i * 2 + 1) {
            if(i<len&&array[i]>array[i+1]) i++;
            if(tempt<array[i]) break;
            array[index] = array[i];
            index = i;
        }
        array[index] = tempt;
    }


    //直接插入法
    public void InsertOrder(int array[]) {

        for (int i = 1; i < array.length; i++) {
            int tempt = array[i], j = i - 1;
            for (; j >= 0 && tempt < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = tempt;
        }
    }


    /**
     * 归并排序
     * @param array
     * @param copy
     * @param low
     * @param high
     */
    public void MergeOrder(int[] array, int[] copy, int low, int high) {

        if (low == high) return;

        int mid = (low + high) >> 1;

        MergeOrder(array, copy, low, mid);

        MergeOrder(array, copy, mid+1, high);

        //把元素聚合在一起
        int left = low;
        int right = mid+1;
        int cindex = low;
        while (left<=mid && right <=high) {

            if (array[left] < array[right]) {
                copy[cindex++] = array[left++];
            } else {
                copy[cindex++] = array[right++];
            }
        }

        for (; left <= mid; ) {
            copy[cindex++] = array[left++];
        }

        for (; right <=high; ) {
            copy[cindex++] = array[right++];
        }


        //把排序之后的元素放置到 array中

        for (int i = low; i < cindex; i++) {
            array[i] = copy[i];
        }
        return;
    }


    /**
     * 快速排序
     * @param array
     * @param row
     * @param high
     */
    public void fastOrder(int[] array, int row, int high) {

        if (row < high) {
            int mid = Order(array, row, high);
            fastOrder(array, row, mid - 1);
            fastOrder(array, mid + 1, high);
        }

    }


    public int Order(int[] array, int row, int high) {

        int tempt = array[row];

        while (row < high) {

            while (tempt < array[high] && high > row) {

                high--;
            }

            array[row] = array[high];

            while (tempt > array[row] && row < high) {
                row++;
            }
            array[high] = array[row];
        }

        array[row] = tempt;
        return row;

    }


     public static void main(String... args) {
        CheckSum checkSum = new CheckSum();

        int[] params = new int[]{8, 7, 6, 5, 4, 3, 2};
        for (int i = params.length / 2 - 1; i >= 0; i--) {
            checkSum.duiSortedOrder(params, i,params.length-1);
        }

        //输出排序后的数据

        for (int i = 0; i <= params.length-1; i++) {
            System.out.println(params[0]);
            params[0] = params[params.length - 1 - i];
            checkSum.duiSortedOrder(params, 0, params.length - 1 - i);
        }


        checkSum.InsertOrder(params);
        System.out.println(params);

    }
}
