package com.leetcode.domain;

import java.util.Arrays;


/**
 * 股票销售问题集合 可能
 * 是至多一次交易 或者两次 最终推到k次交易
 */
public class maxProfitC123 {


    public int maxProfit(int[] prices) {

        if(prices.length==0) return 0;

        int min = prices[0];

        int dp[] = new int[prices.length];
        int nums = 1;
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i] = prices[i] - min >=0 ? prices[i] - min : -1;
            if(dp[i]==-1) nums++;
            min = min > prices[i] ? prices[i] : min;
        }

        // 逆序;
        if(nums==prices.length) return 0;
        // 顺序
//        if(nums==1) return dp[prices.length - 1];

        int maxValue[] = new int[nums];
        int max = -1;
        for (int j = 1, i = 0; j < prices.length; j++) {

            if(dp[j]==-1){
                maxValue[i++] = max>=0?max:0;
                max = -1;
            }else{
                max = max < dp[j] ? dp[j] : max;
            }
        }

        if(max!=-1) maxValue[nums - 1] = max;

        Arrays.sort(maxValue);
        // 顺序中的特殊情况 1,2,3,4,5,6,3
        if (nums==1) return maxValue[0];
        return maxValue[nums - 1] + maxValue[nums - 2];
    }



    /**
     * 至多一次股票交易
     * @param prices
     * @return
     */
    public int maxProfitC(int[] prices) {


        if (prices.length == 0) return 0;

        int min = prices[0];

        int maxValue = 0;

        int[] dp = new int[prices.length];

        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {

            dp[i] = prices[i] - min >= 0 ? prices[i] - min : -1;
            min = min < prices[i] ? min : prices[i];

            maxValue = maxValue > dp[i] ? maxValue : dp[i];
        }

        return maxValue;
    }


    /**
     * 至多进行2次交易
     * global[i][j] 表示前i天的情况下交易j次的利润
     * local[i][j] 表示前i 天的情况下交易j次的利润 且 正好在第i天卖出
     * global[i][j]=Max(global[i-1][j],local[i][j]])
     *
     * local[i][j]
     *
     * 有三种情况
     *
     * 第i天刚买，当天就卖出
     * global[i-1][j-1]
     *
     * 昨天买的(第i-1天)  今天卖出
     * global[i-1][j-1]+diff
     *
     *
     * 昨天之前买的, 今天卖
     * local[i-1,j] +diff
     *
     *
     *
     *
     * @param prices
     * @return
     */
    public int maxProfitC2U(int[] prices) {

        if(prices==null||prices.length==0) return 0;
        int[] global = new int [3];
        int[] local = new int[3];
        int diff = 0;
        for (int i = 1; i < prices.length; i++) {

            for (int j = 2; j >=1; j--) {
                diff = prices[i] - prices[i - 1];
                local[j] = Math.max(global[j - 1] + Math.max(diff,0), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[2];
    }

    public int maxProfitC2(int[] prices) {

        if (prices == null || prices.length == 0) return 0;
        int[][] global = new int[prices.length][3];
        int[][] local = new int[prices.length][3];
        int diff = 0;
        for (int i = 1; i < prices.length; i++) {

            for (int j = 1; j <= 2; j++) {
                diff = prices[i] - prices[i - 1];
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), local[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[prices.length - 1][2];
    }



    // 可以无限次的交易行为
    public int maxProfitII(int[] prices) {

        if(prices==null||prices.length==0) return 0;

        int result = 0;
        for (int i = 1; i < prices.length; i++) {

            if (prices[i - 1] < prices[i]) {
                result += prices[i] - prices[i - 1];
            }
        }

        return result;
    }


    public static void main(String... args) {

        System.out.println(new maxProfitC123().maxProfitC2(new int[]{3,3,5,0,0,3,1,4}));

        System.out.println(1+1>2?1:3);
    }


}
