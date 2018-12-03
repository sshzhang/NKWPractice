package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class spiralOrderC54 {


    public List<Integer> spiralOrder(int[][] matrix) {


        List<Integer> params = new ArrayList<Integer>();
        if(matrix==null||matrix.length==0) return params;
        int m = matrix.length;
        int n = matrix[0].length;

        int dest = m > n ? n : m;
        for (int k = 0; k < dest / 2 + dest % 2; k++) {

            //从左到右
            for (int i = k; i <= n - 1 - k; i++) params.add(matrix[k][i]);

            //从上到下

            for (int j = k + 1; j <= m - k - 1 && n - k - 1 >= 0; j++) params.add(matrix[j][n - k - 1]);


            for (int p = n - k - 2; p >= k && k < m - 1 - k && m - 1 - k >= 0; p--) params.add(matrix[m - 1 - k][p]);


            for (int q = m - 2 - k; q >= k + 1 && k < n - k - 1; q--) params.add(matrix[q][k]);

        }

        return params;
    }

    public static void main(String... args) {
        int[][] params = new int[10][1];
        for (int i = 0; i < params.length; i++) {
            params[i] = new int[]{i + 1,11+i};
        }
        System.out.println(new spiralOrderC54().spiralOrder(params));
    }
}
