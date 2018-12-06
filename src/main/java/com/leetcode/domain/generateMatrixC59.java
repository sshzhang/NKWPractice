package com.leetcode.domain;

public class generateMatrixC59 {

    public int[][] generateMatrix(int n) {


        int dest = n / 2 + n % 2;

        int[][] params = new int[n][n];
        int index = 1;
        for (int k = 0; k < dest; k++) {

            for (int j = k; j <= n - 1 - k ;j++) {
                params[k][j] = index++;
            }

            for (int i = k + 1; i <= n - 1 - k; i++) {
                params[i][n - 1 - k] = index++;
            }

            for (int j = n - 2 - k; j >= k&&n-1-k>k; j--) {
                params[n - 1 - k][j] = index++;
            }

            for (int i = n - 2 - k; i >= k + 1&&n-1-k>k; i--) {
                params[i][k] = index++;
            }
        }

        return params;
    }
}
