package com.leetcode.domain;

public class merge88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        //先设置数据到里面
        for (int i = m; i < n + m; i++) {
            nums1[i] = nums2[i-m];
        }
       //左边数组包含m-1 右边从m 开始
        mergeFromBottomToTop(nums1, 0, m - 1 , m + n - 1);
    }


    public void mergeFromBottomToTop(int a[], int start, int mid, int end) {

        //赋值数据
        int[] copy = new int[end + 1];

        int leftIndex = start, rightIndex = mid + 1;
        int currIndex = start;
        while (leftIndex <= mid && rightIndex <= end) {

            if (a[leftIndex] < a[rightIndex]) {
                copy[currIndex] = a[leftIndex++];
            }else{
                copy[currIndex] = a[rightIndex++];
            }
            currIndex++;
        }


        while (leftIndex <= mid) {
            copy[currIndex++] = a[leftIndex++];
        }

        while (rightIndex <= end) {
            copy[currIndex++] = a[rightIndex++];
        }

        for (int k = 0; k < copy.length; k++) {
            a[k] = copy[k];
        }
        System.out.println(a);

    }


    public static void main(String... args) {

        merge88 ms = new merge88();

        int[] ints = {1, 2, 3, 0, 0, 0};
        ms.merge(ints,3,new int[]{2,5,6},3);
        System.out.println(ints);


    }


}
