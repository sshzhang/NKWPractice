package com.leetcode.domain;

public class findMediumNums4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {


        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int left = 0, right = 0;
        if (len % 2 == 0) {
            left = len / 2 - 1;
            right = left + 1;
        } else {
            left = right = len / 2;
        }

        int index1 = 0, index2 = 0, index = 0;
        int medium = 0;
        while (index1 < len1 || index2 < len2) {
            if (index2 >= len2)
                while (index1 < len1) {
                    index1++;
                    index++;

                    if (index - 1 == left) medium += nums1[index1 - 1];
                    if (index - 1 == right) medium += nums1[index1 - 1];
                }
            else if (index1 >= len1)
                while (index2 < len2) {
                    index2++;
                    index++;
                    if (index - 1 == left) medium += nums2[index2 - 1];
                    if (index - 1 == right) medium += nums2[index2 - 1];
                }
            else if (nums1[index1] < nums2[index2]) {
                index1++;
                index++;
                if (index - 1 == left) medium += nums1[index1 - 1];
                if (index - 1 == right) medium += nums1[index1 - 1];
            } else {
                index2++;
                index++;
                if (index - 1 == left) medium += nums2[index2 - 1];
                if (index - 1 == right) medium += nums2[index2 - 1];
            }
        }

        return medium * 1.0 / 2;
    }


    /**
     * log(m+n) 时间复杂度实现查找中位数
     *  参考  https://blog.csdn.net/hk2291976/article/details/51107778
     * @param
     */
    public double findMedianSortedArraysU(int[] nums1, int[] nums2) {

        int n = nums1.length, m = nums2.length;

        if (n > m)
            return findMedianSortedArrays(nums1, nums2);

        int lo = 0, hi = 2 * n, L1 = 0, R1 = 0, L2 = 0, R2 = 0, c1, c2;

        while (lo <= hi) {
            c1 = (lo + hi) / 2;
            c2 = m + n - c1;
            L1 = c1 == 0 ? Integer.MIN_VALUE : nums1[(c1 - 1) / 2];
            R1 = c1 == 2 * n ? Integer.MAX_VALUE : nums1[c1 / 2];
            L2 = c2 == 0 ? Integer.MIN_VALUE: nums2[(c2 - 1) / 2];
            R2 = c2 == 2 * m ? Integer.MAX_VALUE : nums2[c2 / 2];
            if (L1 > R2) {
                hi = c1 - 1;
            } else if (L2 > R1) {
                lo = c1 + 1;
            } else
                break;
        }
        return (Math.max(L1, L2) + Math.min(R1, R2)) * 1.0 / 2;
    }



    public double findMedianSortedArraysUU(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        if(len1>len2) return findMedianSortedArraysUU(nums2, nums1);
        int minX = 0, maxX = len1, i, j, len = (len1 + len2 + 1) / 2;

        while (minX <= maxX) {
            i = (minX + maxX) / 2;
            j = len - i;

            if (i < maxX && nums1[i] < nums2[j-1]) {
                minX = i + 1;
            } else if (i > minX && nums1[i - 1] > nums2[j]) {
                maxX = i - 1;
            }else{

                int maxLeft = 0;
                if(i==0) maxLeft = nums2[j - 1];
                else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if((len1+len2)%2!=0) return maxLeft;
                int minRight = 0;

                if(i==len1) minRight = nums2[j];
                else if (j == len2) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight) * 1.0 / 2;
            }
        }
        return 0;
    }

    public static void main(String... args) {
        System.out.println(new findMediumNums4().findMedianSortedArraysUU(new int[]{100001}, new int[]{100000}));
    }

}
