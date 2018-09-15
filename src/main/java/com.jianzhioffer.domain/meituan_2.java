package com.jianzhioffer.domain;

import java.util.Scanner;

/**
 * 美团笔试
 *
 * 假设最长时间为2小时
 * 输入
 * 作业的个数
 * 话费pi分钟 获得的分数  做完整个项目的分钟数  获得的分数
 * 话费pi分钟 获得的分数  做完整个项目的分钟数  获得的分数
 * 话费pi分钟 获得的分数  做完整个项目的分钟数  获得的分数
 * 话费pi分钟 获得的分数  做完整个项目的分钟数  获得的分数
 *
 *
 * 4
 * 20 20 100 60
 * 50 30 80 55
 * 100 60 110 88
 * 5 3 10 6
 *
 * 输出
 * 94
 *
 * 此问题其实就是0 1 背包问题的变种
 *
 * 原来每一个物品分为装入或者不装入
 *
 * 现在变成 不解答  解答部分  解答全部
 *
 *
 */
public class meituan_2 {
    private static int[][] paras = null;
    private static int[][] dp = null;
    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] params = new int[n+1][4];
        for (int i=1;i<=n;i++) {//取值从1开始
            for (int j=0;j<4;j++) {
                params[i][j] = scanner.nextInt();
            }
        }
        paras = params;
        dp = new int[n + 1][120 + 1];
        for (int j = 0; j < dp[0].length; j++) dp[0][j] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) dp[i][j] = 0;
        }
        System.out.println(new meituan_2().solve(n, 120));
    }

    /**
     * dp[i][j] 表示 前1-i个物品 在重量为w的前提下 最大的价值
     *
     * @param n 表示物品的个数
     * @param w 表示总的重量
     * @return
     */
    public int solve(int n, int w) {

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <=w; j++) {
                if (j < paras[i][0] && j < paras[i][2]) {//不放入
                    dp[i][j] = dp[i - 1][j];
                } else if (j < paras[i][0] && j >= paras[i][2]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - paras[i][2]] + paras[i][3]);
                } else if (j >= paras[i][0] && j < paras[i][2]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - paras[i][0]] + paras[i][1]);
                } else {
                    int tempt1 = dp[i - 1][j];
                    int tempt2 = dp[i - 1][j - paras[i][0]] + paras[i][1];
                    int tempt3 = dp[i - 1][j - paras[i][2]] + paras[i][3];
                    dp[i][j] = tempt1 > tempt2 ? (tempt1 > tempt3 ? tempt1 : tempt3) : (tempt2 > tempt3 ? tempt2 : tempt3);
                }
            }
        }
        return dp[n][w];
    }
}
