package com.leetcode.domain;

/**
 * 找出两个只出现一次的数字
 * 例如 [4,1,4,6] 输出[1,6]
 */
public class SigleNumbers56 {

    /**
     * 把两个不同的数字划分到两个不同的组中，然后直接异或对应组中的数据求出最后的元素。
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {

        int dest = 0;
        // 先求出最终两个元素的异或值
        for (int i = 0; i < nums.length; i++) {
            dest ^= nums[i];
        }
        int difPos = 1;
        // 然后利用最终值对应二进制编码为1，来把整个数组分割成两部分。从而这两个值在两个不同的部分中。
        while ((difPos & dest) ==0) {
            difPos <<= 1;
        }

        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {

            if ((nums[i] & difPos) == 0) {
                a ^= nums[i];
            }else{
                b ^= nums[i];
            }
        }
        return new int[]{a, b};
    }

}
