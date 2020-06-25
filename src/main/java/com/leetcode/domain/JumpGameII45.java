package com.leetcode.domain;

public class JumpGameII45 {


    /**
     * 跳跃游戏 利用贪心每次正向获取最远距离。
     * 每次获取能访问的最远边界，如果当前位置到达上一次能到达地边界，则更新end边界值
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        // 边界 特指上一次的边界位置
        int end = 0;
        // 保存上一次边界中所有的点最远跳动距离，如果i遍历到end则利用此值更新end边界值
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    /**
     * 反向方法， 从最后一个节点开始，每次查找离这个节点最远的跳动节点相应的位置，
     * 从而实现全局最优
     * @param nums
     * @return
     */
    public int jumpU(int[] nums) {
        // 最后一个节点开始
        int position = nums.length-1;
        int steps = 0;
        while (position != 0) {
            for (int i = 0; i < nums.length; i++) {
                if(nums[i]+i>=position) {
                    steps++;
                    position=i;
                    break;
                }
            }
        }
        return steps;
    }

}


