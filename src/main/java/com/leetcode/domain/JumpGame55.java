package com.leetcode.domain;

import java.util.Stack;

public class JumpGame55 {


    // 模拟跳跃过程
    public boolean canJump(int[] nums) {

        int len = nums.length-1;
        if(len==0) return true;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int index = stack.pop();
            if(index+nums[index]>=len) return true;
            else{
                int dest = -1;
                for (int i = nums[index]; i >= 1; i--) {
                    //dest+nums[dest]>index+nums[index] 优化避免添加无用数据
                    if (nums[dest=i + index] != 0 &&dest+nums[dest]>index+nums[index]&&!stack.contains(dest)) {
                        stack.push(dest);
                    }
                }
            }
        }
        return false;
    }



    // 模拟跳跃过程  每次保存当前最长长度
    public boolean canJumpUU(int[] nums) {

        int len = nums.length-1;
        if(len==0) return true;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int maxDistance=-1;
        int dest = -1;
        int tdest = -1;
        while (!stack.isEmpty()) {
            int index = stack.pop();
            if ((maxDistance = index + nums[index]) >= len ) {
                return true;
            } else {
                tdest = -1;
                for (int i = nums[index]; i >= 1; i--) {
                    if (nums[dest = i + index] != 0 &&dest+nums[dest]>maxDistance&& !stack.contains(dest)) {
                        maxDistance = dest + nums[dest];
                        tdest = dest;
                    }
                }
                if(tdest!=-1) stack.push(tdest);
            }
        }
        return false;
    }

    public static void main(String... args) {

        System.out.println(new JumpGame55().canJumpUU(new int[]{3,2,1,0,4}));

    }
}
