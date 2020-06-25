package com.leetcode.domain;

import java.util.LinkedList;
import java.util.Queue;

public class matrix542 {

    /**
     * 两遍dp操作  第一遍获取左上角所有数据的dp最小值， 第二遍获取获取右下角最小值。
     *
     * @param matrix 数据矩阵
     * @return
     */
    private int[][] vector = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] updateMatrix(int[][] matrix) {
        // dp[i][j]= {dp[i][j-1], dp[i-1][j], dp[i+1][j], dp[i][j+1]}
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int left, top, right, down;
        left = top = right = down = colLen + rowLen;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = rowLen + colLen;
                    left = j > 0 ? matrix[i][j - 1] : rowLen + colLen;
                    top = i > 0 ? matrix[i - 1][j] : rowLen + colLen;
                    matrix[i][j] = Math.min(left, top) + 1;
                }
            }
        }
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) continue;
                right = j + 1 < colLen ? matrix[i][j + 1] : rowLen + colLen;
                down = i + 1 < rowLen ? matrix[i + 1][j] : rowLen + colLen;
                matrix[i][j] = Math.min(matrix[i][j], Math.min(right, down) + 1);
            }
        }

        return matrix;
    }


    /**
     * 广度优先遍历BFS
     * 对于每一个数据为0的点，开始广度遍历。
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrixU(int[][] matrix) {

        int diX[] = new int[]{0, -1, 0, 1};
        int diY[] = new int[]{-1, 0, 1, 0};
        Queue<int[]> queue = new LinkedList<>();
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        for (int i = 0; i < rowLen; i++) {

            for (int j = 0; j < colLen; j++) {

                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else { // 设置数据为最大值
                    matrix[i][j] = colLen + rowLen;
                }
            }
        }

        int x = 0, y = 0;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int i = 0; i < 4; i++) {
                x = point[0] + diX[i];
                y = point[1] + diY[i];
                if (x >= 0 && x < rowLen && y >= 0 && y < colLen && matrix[x][y] != 0 && matrix[x][y] > matrix[point[0]][point[1]] + 1) {
                    matrix[x][y] = matrix[point[0]][point[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return matrix;
    }


    /**
     * 深度优先遍历 DFS
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrixUU(int[][] matrix) {

        int rowLen = matrix.length;
        int colLen = matrix[0].length;


        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                // 提前优化一部分数据, 周边没有直接的零
                //if (matrix[i][j] == 1 && !((i > 0 && matrix[i - 1][j] == 0) || (i < rowLen - 1 && matrix[i + 1][j] == 0) || (j > 0 && matrix[i][j - 1] == 0) || (j < colLen - 1 && matrix[i][j + 1] == 0))) {
                // 未优化的判断操作。
                if (matrix[i][j] == 0) { //取代上面条件， 那么下面统计的点从0开始
                    matrix[i][j] = colLen + rowLen;
                }
            }
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                //if(matrix[i][j]==1)
                if (matrix[i][j] == 0)
                    updateResultMatrix(matrix, i, j, rowLen, colLen);
            }
        }
        return matrix;
    }


    private void updateResultMatrix(int[][] matrix, int i, int j, int rowLen, int colLen) {
        int x = 0, y = 0;
        if (i < 0 || i >= rowLen || j < 0 || j >= colLen) return;

        for (int r = 0; r < 4; r++) {

            x = i + vector[r][0];
            y = j + vector[r][1];
            if (x < rowLen && y < colLen && x >= 0 && y >= 0 && matrix[x][y] != 0 && matrix[x][y] > matrix[i][j] + 1) {
                matrix[x][y] = matrix[i][j] + 1;
                updateResultMatrix(matrix, x, y, rowLen, colLen);
            }
        }
    }
}
