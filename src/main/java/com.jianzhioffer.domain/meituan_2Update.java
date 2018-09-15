package com.jianzhioffer.domain;

import java.util.Scanner;

/**
 * 美团 用一维数组保存 相应权重下的物品的最大价值
 *
 *
 * 0,1 背包问题
 *
 */
public class meituan_2Update {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] paramas = new int[n + 1][4];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < paramas[i].length; j++) {
                paramas[i][j] = scanner.nextInt();
            }
        }

        int[] dp = new int[120 + 1];
        for (int i = 0; i < dp.length; i++) dp[i] = 0;

        Solve(n, 120, paramas, dp);
        System.out.println(dp[dp.length-1]);

    }

    private static void Solve(int n, int j, int[][] paramas,int[]dp) {

        for (int i = 1; i <=n; i++) {

            for (int k = j; k >= 0; k--) {
                if(paramas[i][0]>k&&paramas[i][2]>k){//不放
                    dp[k] = dp[k];
                } else if (paramas[i][0] > k && paramas[i][2] <= k) {
                    dp[k] = Math.max(dp[k], dp[k - paramas[i][2]] + paramas[i][3]);
                } else if (paramas[i][0] <= k && paramas[i][2] > k) {
                    dp[k] = Math.max(dp[k], dp[k - paramas[i][0]] + paramas[i][1]);
                } else {
                    int tempt1 = dp[k];
                    int tempt2 = dp[k - paramas[i][0]] + paramas[i][1];
                    int tempt3 = dp[k - paramas[i][2]] + paramas[i][3];
                    dp[k] = tempt1 > tempt2 ? tempt1 > tempt3 ? tempt1 : tempt3 : tempt2 > tempt3 ? tempt2 : tempt3;
                }

            }

        }

    }


}
