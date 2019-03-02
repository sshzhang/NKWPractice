package com.leetcode.domain;

public class maximalSquare221 {
//找出二维数组中的最大的正方形面积

    //由于是正方形   推到出动态规划方程
    //动态规划  dp[i][j]= Min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1  matrix[i][j]=='1'
    //       dp[i][j]=0   matrix[i][j]='0'  获得的只是一条边的长度，记住要平方。
    public int maximalSquare(char[][] matrix) {

        int row = matrix != null ? matrix.length : -1;
        int col = matrix.length > 0 ? matrix[0].length : 0;
        int[][] dp = new int[row + 1][col + 1];

        int maxValue = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = (dp[i - 1][j] > dp[i][j - 1] ?
                            (dp[i][j - 1] > dp[i - 1][j - 1] ? dp[i - 1][j - 1] : dp[i][j - 1]) :
                            (dp[i - 1][j] > dp[i - 1][j - 1] ? dp[i - 1][j - 1] : dp[i - 1][j]));
                    dp[i][j]++;
                    maxValue = maxValue > dp[i][j] ? maxValue : dp[i][j];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return maxValue * maxValue;
    }


    //暴力破解
    public int maximalSquareForceBaoli(char[][] matrix) {
        //尝试把每一个点作为正方形的左上角坐标，然后判断矩阵长度。
        int row = matrix == null ? -1 : matrix.length;
        int col = matrix.length > 0 ? matrix[0].length : 0;
        int parseLength = 0;
        boolean flages = true;
        boolean isbreak = false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != '0') {
                    flages = true;
                    while (flages) {
                        if(i+parseLength>=row||j+parseLength>=col) {flages=false;break;}
                        parseLength++;
                        for (int r = i; r < i + parseLength && r < row && flages; r++) {
                            for (int c = j; c < j + parseLength && c < col && flages; c++) {
                                if (matrix[r][c] == '0') {
                                    flages = false;
                                }
                            }
                        }
                        if(!flages) {parseLength--;}
                    }
                }
            }

        }
        return parseLength * parseLength;
    }



    public static void main(String... args) {

        maximalSquare221 square221 = new maximalSquare221();

        char[][] chars = new char[1][2];
        chars[0] = new char[]{'1','1'};
//        chars[1] = new char[]{'1', '0', '1', '1', '1'};
//        chars[2] = new char[]{'1', '1', '1', '1', '1'};
//        chars[3] = new char[]{'1', '0', '0', '1', '0'};
        int m = square221.maximalSquareForceBaoli(chars);
        System.out.println(m);

    }


}





