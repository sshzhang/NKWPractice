package com.leetcode.domain;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Stack;

public class TheLeastKNums1 implements Comparable<String> {


    public int[] getLeastNumbers(int[] arr, int k) {
        int len = arr.length;
        if (k >= len) return arr;
        else if (k == 0) return new int[]{};
        // 开始计算  默认方法先排序在计算
        directHeapOrderSort(arr, k);
        for (int i = k; i < len; i++) {
            if (arr[i] < arr[0]) {
                arr[0] = arr[i];
                heapAdjust(arr, 0, k);
            }
        }
        int[] tmpt = new int[k];

        for (int i = 0; i < k; i++) {
            tmpt[i] = arr[i];
        }


       /* for (int i = 0; i < k; i++) {
            tmpt[i] = arr[0];
            arr[0] = arr[len - i - 1];
            heapAdjust(arr, 0, len - 1 - i);
        }*/
        return tmpt;
    }

    /**
     * 直接插入法 容易把条件tempt<arr[j]变为条件arr[i]<arr[j]
     **/
    public void directInsertOrderSort(int[] arr) {
        int tempt = 0, j = 0;
        for (int i = 1; i < arr.length; i++) {
            tempt = arr[i];
            for (j = i - 1; j >= 0 && tempt < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tempt;
        }
    }

    public void directInsertOrderSortUpdate(int[] arr) {
        int tempt = 0, j = 0;
        for (int i = 1; i < arr.length; i++) {
            tempt = arr[i];
            /*for (j = i - 1; j >= 0 && tempt < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j+1]=tempt
            */

            // 之前 0, i-1都是有序元素
            int low = 0, high = i - 1;
            while (low <= high) { // 二分查找优化
                int mid = (low + high) / 2;
                if (arr[mid] >= tempt) high = mid - 1;
                else low = mid + 1;
            }
            for (j = i - 1; j >= high + 1; j++) {
                arr[j + 1] = arr[j];
            }
            arr[high + 1] = tempt;
        }
    }


    /**
     * * 快速排序
     **/
    public void directQuickOrderSort(int[] arr, int low, int high) {
        /*if (low < high) {
            int mid = rQuickMindIndex(arr, low, high);
            directQuickOrderSort(arr, low, mid - 1);
            directQuickOrderSort(arr, mid + 1, high);
        }*/
        Stack<Integer> stack = new Stack<>();
        if (low < high) { // 非递归
            stack.push(high);
            stack.push(low);
            while (!stack.isEmpty()) {

                low = stack.pop();
                high = stack.pop();
                int mid = rQuickMindIndex(arr, low, high);
                if (mid - 1 > low) {
                    stack.push(mid - 1);
                    stack.push(low);
                }
                if (mid + 1 < high) {
                    stack.push(high);
                    stack.push(mid + 1);
                }
            }
        }
    }

    public int rQuickMindIndex(int arr[], int low, int high) {
        int tempt = arr[low]; // 保存当前需要替换的值
        while (low < high) { //
            while (high > low && arr[high] >= tempt) high--;
            arr[low] = arr[high];
            while (low < high && arr[low] < tempt) low++;
            arr[high] = arr[low];
        }

        arr[low] = tempt;
        return low;
    }


// 归并排序

    public void directReclusiveOrderSort(int[] arr, int[] copy, int low, int high) {

        if (low >= high) return;
        int mid = (low + high) / 2;
        directReclusiveOrderSort(arr, copy, low, mid);
        directReclusiveOrderSort(arr, copy, mid + 1, high);
        directReclusiveAllToOneOrder(arr, copy, low, mid, high);

    }

    public void directReclusiveAllToOneOrder(int[] arr, int[] copy, int low, int mid, int high) {

        // 先每次新创建一个数组， 后期修改未全局创建一个数组
        //int[] copy=new int[1+high-low];

        int l1 = low, l2 = mid + 1, index = low;

        while (l1 <= mid && l2 <= high) {
            if (arr[l1] > arr[l2]) {
                copy[index++] = arr[l2++];
            } else {
                copy[index++] = arr[l1++];
            }
        }

        while (l1 <= mid) {
            copy[index++] = arr[l1++];
        }
        while (l2 <= high) {
            copy[index++] = arr[l2++];
        }

        for (int i = low; i <= high; i++) {
            arr[i] = copy[i];
        }
    }

    /**
     * 堆排序  构建k个元素的大顶堆包括两步 堆调整，
     */
    public void directHeapOrderSort(int[] arr, int k) {
        for (int i = k / 2 - 1; i >= 0; i--) {
            // 调整数组中从i,.....len-1中数据使满足堆排序结构
            heapAdjust(arr, i, k);
        }
    }

    /**
     * 大顶堆的调整,
     *
     * @param arr   数组
     * @param index 需要调整的元素索引位置
     * @param high  堆的元素个数
     */
    public void heapAdjust(int[] arr, int index, int high) {
        // 保存此位置上的数据信息
        int tempt = arr[index];
        for (int i = index * 2 + 1; i < high; i = i * 2 + 1) {
            if (i + 1 < high && arr[i + 1] > arr[i]) i++; //满足右边节点数据更小
            // 比较数据和index位置上数据的大小
            if (tempt < arr[i]) {
                arr[index] = arr[i];
                index = i;
            } else
                break;
        }
        arr[index] = tempt;
    }


    public static void main(String... args) {

        TheLeastKNums1 x = new TheLeastKNums1();
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                System.out.println(c);
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        System.out.println(c);
                }
            }
        }
/*
        int[] arr = new int[]{10, 8, 7, 5, 9};
        int[] leastNumbers = kNums1.getLeastNumbers(arr, 3);

        for (int i = 0; i < 3; i++) {
            System.out.print(leastNumbers[i] + " ");
        }*/

    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
