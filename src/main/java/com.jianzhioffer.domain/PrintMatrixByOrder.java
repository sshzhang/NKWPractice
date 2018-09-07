package com.jianzhioffer.domain;

import java.util.ArrayList;

/***
 * 顺序打印矩阵
 *
 *  每一圈包含两行两列    从左到右  从上到下   从右到左   从下到上
 *
 */

public class PrintMatrixByOrder {


    public ArrayList<Integer> printMatrix(int[][] matrix) {

        ArrayList<Integer> params = new ArrayList<Integer>();
        int row = matrix.length;
        int columns = matrix[0].length;
        //计算总的圈数
        int circles = ((row > columns ? columns : row) - 1) / 2 + 1;

        for (int i=0;i<circles;i++) {//每一圈

            //从左到右
            for (int j=i;j<columns-i;j++)
                params.add(matrix[i][j]);

            //从上到下
            for (int k=i+1;k<row-i;k++)
                params.add(matrix[k][columns-i-1]);

            //从右到左  row-1-i!=i 避免 两行的重复问题
            for (int m=columns-2-i;m>=i&&row-1-i!=i;m--)
                params.add(matrix[row-1-i][m]);

            //从下到上   columns-i-1!=i 避免 两列的重复问题
            for (int n=row-2-i;n>=i+1&&columns-i-1!=i;n--)
                params.add(matrix[n][i]);

        }
        return params;
    }


    public static void main(String... args) {

        int[][] pa = new int[5][1];
        int count = 1;
        for (int i=0;i<pa.length;i++) {
            for (int j=0;j<pa[0].length;j++) {
                pa[i][j] = count++;
            }
        }
        System.out.println(new PrintMatrixByOrder().printMatrix(pa));

    }


}
