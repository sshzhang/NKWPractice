package com.leetcode.domain;

public class rob198 {

    public int rob(int[] nums) {
        int len=nums.length;
        if(len==0) return 0;
        if(len==1) return nums[0];
        if(len==2) return Math.max(nums[0],nums[1]);
        int dp[]=new int[len];
        int maxValue=nums[0];
        for(int i=0;i<len-1;i++){
            if (i > 2) {
                dp[i]=dp[i-2]>dp[i-3]?dp[i-2]+nums[i]:dp[i-3]+nums[i];
            } else if (i == 2) {
                dp[i] = nums[0] + nums[2];
            }else{
                dp[i] = nums[i];
            }

            maxValue=maxValue>dp[i]?maxValue:dp[i];
        }



        for(int i=1;i<len;i++){
            if (i > 3) {
                dp[i]=dp[i-2]>dp[i-3]?dp[i-2]+nums[i]:dp[i-3]+nums[i];
            } else if (i == 3) {
                dp[i] = nums[1] + nums[3];
            }else{
                dp[i] = nums[i];
            }

            maxValue=maxValue>dp[i]?maxValue:dp[i];
        }
        return maxValue;
    }

    public static void main(String... args) {
        System.out.println(new rob198().rob(new int[]{4,1,2,7,5,3,1}));
        //  dp[0]=4 dp[1]=1  dp[2]=6, dp[3]=11  dp[4]=11  dp[5]=14
       // dp[1]=1 dp[2]=2 dp[3]=8 dp[4]=7 dp[5]=11 dp[6]=9
    }
}
