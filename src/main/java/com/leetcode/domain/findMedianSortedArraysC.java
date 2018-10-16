package com.leetcode.domain;

/**
 * 两个排序数组的中位数
 *
 * 求出两个有序数组nums1和nums2的中位数
 *
 */
public class findMedianSortedArraysC {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        //中位数位置 对于奇数 left==right
        int left = (len1 + len2 + 1) / 2;
        int right = (len1 + len2 + 2) / 2;
        if (left == right) {
            return findKth(nums1, 0, nums2, 0, left);
        }
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2;
    }

    //position 表示第几个元素
    private double findKth(int[] nums1, int index1, int[] nums2, int index2, int positon) {

        if(index1>=nums1.length) return nums2[index2 + positon - 1];

        if(index2>=nums2.length) return nums1[index1 + positon - 1];

        if(positon==1) return Math.min(nums1[index1], nums2[index2]);

        int midVal1 = index1 + positon / 2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[index1 + positon / 2 - 1];
        int midVal2 = index2 + positon / 2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[index2 + positon / 2 - 1];

        if(midVal1<midVal2){//nums1前position/2个元素不可能存在中位数
            return findKth(nums1, index1 + positon / 2, nums2, index2, positon - positon / 2);
        }else{
            return findKth(nums1, index1, nums2, index2 + positon / 2, positon - positon / 2);
        }
    }

    public static void main(String... args) {

        findMedianSortedArraysC sot = new findMedianSortedArraysC();
        double medianSortedArrays = sot.findMedianSortedArrays(new int[]{1, 2}, new int[]{3,4});
        System.out.println(medianSortedArrays);
    }

}
