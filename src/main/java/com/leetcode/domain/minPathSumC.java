package com.leetcode.domain;

public class minPathSumC {

    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++) grid[i][0] += grid[i - 1][0];
        for (int j = 1; j < n; j++) grid[0][j] += grid[0][j - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (i - 1 >= 0 && j - 1 >= 0)
                    grid[i][j] += grid[i - 1][j] > grid[i][j - 1] ? grid[i][j - 1] : grid[i - 1][j];
                else if (i - 1 >= 0)
                    grid[i][j] += grid[i - 1][j];
                else if (j - 1 >= 0)
                    grid[i][j] += grid[i][j - 1];

            }
        }


        return grid[m - 1][n - 1];
    }

    public static void main(String... args) {

        minPathSumC pathSumC = new minPathSumC();

        int[][] datas = new int[3][3];
        datas[0] = new int[]{1, 3, 1};
        datas[1] = new int[]{1, 5, 1};
        datas[2] = new int[]{4, 2, 1};
        int i = pathSumC.minPathSum(datas);
        System.out.println(i);
    }
}
