package com.leetcode.domain;

/**
 * Unique Paths
 */
public class uniquePathsWithObstaclesC {


    public int uniquePaths(int m, int n) {


        int[][] params = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                params[i][j] = 0;
            }
        }
        for (int j = 0; j < m; j++) {
            params[0][j] = 1;
        }

        for (int i = 0; i < n; i++) {
            params[i][0] = 1;
        }


        for (int i = 1; i < n; i++) {

            for (int j = 1; j < m; j++) {
                params[i][j] = params[i - 1][j] + params[i][j - 1];
            }
        }
        return params[n - 1][m - 1];
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid == null) return 0;
        if (obstacleGrid[0][0] == 1) return 0;
        int datas[][] = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) datas[i][j] = 0;
        }


        for (int i = 1; i < datas.length; i++) {
            if (obstacleGrid[i][0] == 0) {
                if (obstacleGrid[i - 1][0] == 1) obstacleGrid[i][0] = 1;
                else datas[i][0] = 1;
            }
        }

        for (int j = 1; j < datas[0].length; j++) {

            if (obstacleGrid[0][j] == 0) {
                if (obstacleGrid[0][j - 1] == 1) obstacleGrid[0][j] = 1;
                else datas[0][j] = 1;
            }
        }


        for (int i = 1; i < datas.length; i++) {
            for (int j = 1; j < datas[i].length; j++) {

                if (obstacleGrid[i][j] == 0) {

                    if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1) {
                        obstacleGrid[i][j] = 1;
                    } else if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 0) {
                        datas[i][j] = datas[i][j - 1];
                    } else if (obstacleGrid[i - 1][j] == 0 && obstacleGrid[i][j - 1] == 1) {
                        datas[i][j] = datas[i - 1][j];
                    } else {
                        datas[i][j] = datas[i - 1][j] + datas[i][j - 1];
                    }
                }

            }
        }

        return datas[datas.length - 1][datas[0].length - 1];
    }

    public static void main(String... args) {
        uniquePathsWithObstaclesC sum = new uniquePathsWithObstaclesC();
        int i = sum.uniquePaths(3, 2);
        System.out.println(i);
    }

}
