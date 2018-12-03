package com.leetcode.domain;

public class firstMissingPositiveC41 {


    /**
     * 此题思路　主要通过找出正数的个数假设为k
     * 最小的正数必定在[1,k+1] 其实就相当于把k个数放置到k+1个桶中　结果必定有一个桶为空
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {


        if (nums.length == 0) return 1;

        //左边为正数　右边为其他
        int q = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                Parition(nums, i, ++q);
            }
        }

        if (q == -1) return 1;

        //总共 [0,k-1]
        int k = q + 1;

        for (int i = 0; i < k; i++) {

            int tempt =Math.abs(nums[i]);
            //数据tempt 对应的nums中位置为tempt-1
            if (tempt <= k) {
                nums[tempt - 1] = nums[tempt - 1] > 0 ? -nums[tempt - 1] : nums[tempt - 1];
            }
        }

        int dest = k;//或者是最后一个元素
        for (int i = 0; i < k; i++) {
            if (nums[i] > 0) {
                dest = i;
                break;
            }
        }

        return dest + 1;

    }


    private void Parition(int[] nums, int i, int j) {
        int tempt = nums[i];
        nums[i] = nums[j];
        nums[j] = tempt;
    }


    public static void main(String... args) {
        new firstMissingPositiveC41().firstMissingPositive(new int[]{3,4,-1,1});
    }
}
