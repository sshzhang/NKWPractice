package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 完全背包问题
 * 4 10
 * 2 1
 * 3 3
 * 4 5
 * 7 9
 */
public class meituan_2CompleteBackpack {


    public static void main(String... args) {

        new meituan_2CompleteBackpack().NewCommpleteBackPackProblem();

    }

    //原始的完全背包问题  转换为01背包问题
    public void OriginalCommpleteBackPageProblem() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int[][] params = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                params[i][j] = scanner.nextInt();
            }
        }

        int[][] dp = new int[n + 1][w + 1];
        for (int j = 0; j < dp[0].length; j++) dp[0][j] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                int max = dp[i - 1][j];
                for (int k = 1; k <= j / params[i][0]; k++) {
                    int tempt = dp[i - 1][j - params[i][0] * k] + params[i][1] * k;
                    max = max < tempt ? tempt : max;
                }
                dp[i][j] = max;
            }
        }

        System.out.println(dp[n][w]);
    }

    //把二维数组转换为一维数组
    public void OriginalCommpleteBackPageProblemModifyArray() {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int[][] params = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                params[i][j] = scanner.nextInt();
            }
        }

        /**
         *
         * 简单优化问题答案
         *
         * 如果物品i和j Ci<Cj 且 Wi>Wj  表明 物品j 不可能会被放入其中 移除
         * 那些重量(也叫花费)大于w的话 直接移除
         */

        List<Integer> indexRemoveParams = new ArrayList<Integer>();
        for (int i = 1; i <= n; ) {

            if (params[i][1] > w) {
                if (!indexRemoveParams.contains(i)) indexRemoveParams.add(i);
                i++;
                continue;
            }
            for (int j = i + 1; j <= n; j++) {
                if (params[i][0] <= params[j][0] && params[i][1] > params[j][1]) {
                    if (!indexRemoveParams.contains(j)) {
                        indexRemoveParams.add(j);
                    }
                }
            }
            while (indexRemoveParams.contains(i + 1)) {
                i++;
            }
            i++;
        }
        System.out.println(indexRemoveParams);

        n = n - indexRemoveParams.size();
        int[][] newparams = new int[n + 1][2];
        for (int i = 1; i < params.length; i++) {
            if (!indexRemoveParams.contains(i)) {
                newparams[i][0] = params[i][0];
                newparams[i][1] = params[i][1];
            }
        }


        int[] dp = new int[w + 1];
        for (int i = 0; i < dp.length; i++) dp[i] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = w; j >= 0; j--) {

                int max = dp[j];
                for (int k = 1; k <= j / newparams[i][0]; k++) {
                    int tempt1 = dp[j - k * newparams[i][0]] + k * newparams[i][1];
                    max = max < tempt1 ? tempt1 : max;
                }
                dp[j] = max;
            }
        }

        System.out.println(dp[w]);

    }


    //完全背包的动态规划问题 F[i][j]=max{F[i-1][j],F[i][j-w[i]]+v[i]}
    /**
     * 其实就是转换为两种情况  i不放入,  i 放入的话，到底放入几个物品i
     */
    public void NewCommpleteBackPackProblem() {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int[][] params = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                params[i][j] = scanner.nextInt();
            }
        }

        int[][] dp = new int[n + 1][w + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) dp[i][j] = 0;

        }

        //初始化元素 dp[1]时候的值
        /*for (int j = 0; j < dp[1].length; j++) {
            if (j < params[1][0]) {
                dp[1][j] = 0;
            } else {
                dp[1][j] = j / params[1][0] * params[1][1];
            }
        }*/
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (j < params[i][0]) {
                    dp[i][j] = dp[i - 1][j];
                }else{//求出最大值  从中可以看出 dp[i][j] 和dp[i-1][j] dp[i][k] k<j 有关 可以装换为以为数组 不过 取值必须从小到大
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - params[i][0]] + params[i][1]);
                }
            }
        }

        System.out.println(dp[n][w]);

    }


    public void NewCommpleteBackPackProblemUpdateArray() {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int[][] params = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                params[i][j] = scanner.nextInt();
            }
        }

        int[] dp = new int[w + 1];

        //从第一个数据开始  也可以从0 开始，效果不影响
        for (int i = 0; i < dp.length; i++) {
            if (i < params[1][0]) {
                dp[i] = 0;
            } else {
                dp[i] = i / params[1][0] * params[1][1];
            }
        }

        for (int i = 2; i <= n; i++) {
            // 完全背包和01 背包的区别
            for (int j = 0; j <=w; j++) {
                if (j < params[i][0]) {
                    dp[j] = dp[j];
                } else {

                    dp[j] = dp[j] > dp[j - params[i][0]] + params[i][1] ? dp[j] : dp[j - params[i][0]] + params[i][1];
                }
            }
        }
        System.out.println(dp[w]);

    }



}
