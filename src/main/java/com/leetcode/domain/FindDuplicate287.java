package com.leetcode.domain;

/**
 * 寻找重复的数字 187
 */
public class FindDuplicate287 {


    /**二分查找
     *  我们首先找出数字区间中中间的数字，在[low, high] 区间中间数字为mid=(low+high)/2,
     *  先找不大于此数字的数字个数，
     *  假设为cn,若cn>mid表明重复数组出现在[low,mid]区域，否则出现在[mid+1,right]区域
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {

            int mid = (low + high) >> 1;
            int cn = 0;
            for (Integer num : nums) {
                if(num<=mid) cn++;
            }
            if(cn>mid) high = mid;
            else low = mid + 1;
        }
        return low;
    }

}
