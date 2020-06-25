package com.leetcode.domain;

public class CoinDp0811 {
    public int waysToChange(int n) {


        /*  背包问题  dp[i][j] 值为i，在前k+1中取值的情况可能的次数
            dp[i][j]=dp[i][j-1]+dp[i-nums[j]][j]+dp[i-nums[j]*2][j]+....+dp[i-nums[j]*k][j]

            dp[i-nums[j]][j]=dp[i-nums[j]][j-1]+dp[i-nums[j]*2][j]....+dp[i-nums[j]*k][j]
            所以问题转换为
            dp[i][j]=dp[i][j-1]+ dp[i-num[j]][j]

            内存数据可以用一维数组，
            首先dp[i]依赖与之前计算的dp[i]和最新计算的dp[i-nums[j]],则我们需要按照从i小到达修改数组数据。
         * 15=1+1+1+1+....+1
         * 15=5+5+1+...+1
         * 15=5+5+5
         * 15=5+1+1+...+1
         * 15=10+1+..+1
         * 15=10+5
         *  dp[i][j]=dp[i][j-1]+ dp[i-num[j]][j]  最优结构
         */
        int nums[]=new int[]{1,5,10,25};
        int dp[]=new int[n+1];
        // 边界条件
        dp[0] = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = nums[i]; j <= n; j++) {
                dp[j] = dp[j] + dp[i - nums[j]];
            }
        }

        return dp[n];
    }

    public static void main(String... args) {

        System.out.println(new CoinDp0811().waysToChange(1000000));

    }
}
