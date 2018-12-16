package com.leetcode.domain;

import java.util.List;

public class minimumTotalC120 {



    //从上到下
    public int minimumTotal(List<List<Integer>> triangle) {


        int min[][] = new int[triangle.size()][];

        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < triangle.size(); i++) {

            List<Integer> integers = triangle.get(i);
            min[i] = new int[integers.size()];


            for (int j = 0; j < integers.size(); j++) {
                if (i == 0) {
                    min[i][j] = integers.get(j);
                } else {

                    if (j - 1 < 0) {
                        min[i][j] = integers.get(j) + min[i - 1][j];
                    } else if (j == integers.size()-1) {
                        min[i][j] = integers.get(j) + min[i - 1][j - 1];
                    }else{

                        min[i][j] = integers.get(j) + Math.min(min[i - 1][j], min[i - 1][j - 1]);
                    }

                }

                if (i == triangle.size() - 1) {
                    minValue = minValue > min[i][j] ? min[i][j] : minValue;
                }

            }

        }


        return minValue;
    }

    // 从下向上
    public int minimumTotalU(List<List<Integer>> triangle) {

        int n = triangle.size();

        int dp[] = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        for (int layer = n - 2; layer >= 0; layer--) {

            for (int i = 0; i <= layer; i++) {

                dp[i] = Math.min(dp[i], dp[i + 1]) + triangle.get(layer).get(i);
            }
        }


        return dp[0];
    }


}
