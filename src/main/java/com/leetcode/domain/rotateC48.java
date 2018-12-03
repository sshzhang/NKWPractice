package com.leetcode.domain;

public class rotateC48 {


    /**
     * 思路　四个角度元素之间的替换
     * @param matrix
     */
    public void rotate(int[][] matrix) {


        int n = matrix.length;
        int num = n / 2;
        for (int k = 0; k < num; k++) {


            for (int m = k; m <= n - 2 - k; m++) {


                int tempt = matrix[k][m];

                matrix[k][m] = matrix[m][n - 1 - k];

                matrix[m][n - 1 - k] = tempt;

                tempt = matrix[k][m];
                matrix[k][m] = matrix[n - 1 - k][n - 1 - m];
                matrix[n - 1 - k][n - 1 - m] = tempt;

                tempt = matrix[k][m];

                matrix[k][m] = matrix[n - 1 - m][k];
                matrix[n - 1 - m][k] = tempt;

            }

        }

    }

}
