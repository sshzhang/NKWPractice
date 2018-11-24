package com.leetcode.domain;

import java.util.HashSet;

public class isValidSudokuC {


    public boolean isValidSudoku(char[][] board) {

        // 在小矩阵看来　ｉ 表示第几个矩阵
        for (int i = 0; i < 9; i++) {

            HashSet<Character> row = new HashSet<>();
            HashSet<Character> colum = new HashSet<>();
            HashSet<Character> square = new HashSet<>();

            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !row.add(board[i][j]))
                    return false;
                // 行列互相转化
                if (board[j][i] != '.' && !colum.add(board[j][i]))
                    return false;

                // 表示对应矩阵的行号3*(i/3)+j/3
                // 表示对应矩阵的列号3*(i%3)+j%3
                if (board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3] != '.' && !square.add(board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3]))
                    return false;
            }

        }

        return true;
    }

    public boolean isValidSudokuP(char[][] board) {


        boolean[][] rows = new boolean[9][9];
        boolean[][] colums = new boolean[9][9];
        boolean[][] squares = new boolean[9][9];

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                if (board[i][j] != '.') {

                    int index = board[i][j] - '1';

                    if (rows[i][index] || colums[index][j]) return false;
                    rows[i][index] = true;
                    colums[index][j] = true;
                }

                if (board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3] != '.') {

                    int val = board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3] - '1';
                    if (squares[i][val]) return false;
                    squares[i][val] = true;

                }

            }

        }
        return true;

    }


    public boolean isRightValue(char ch) {

        if (ch >= '1' && ch <= '9') return true;
        return false;
    }


    /**
     *
     * 37. Sudoku Solver
     *
     * @param board
     */

    public void solveSudoku(char[][] board) {
        if (board==null || board.length != 9 || board[0].length!= 9) return;
        solveReclusiveSudoku(board, 0, 0);
    }

    public boolean solveReclusiveSudoku(char[][] board, int i, int j) {

        if (i >= 9) return true;
        if (j >= 9) return  solveReclusiveSudoku(board, i + 1, 0);
        if(i>=9||j>=9) System.out.println("i="+i+" j="+j);
        if (board[i][j] == '.') {//表示需要填充
            for (int k = 1; k <= 9; k++) {
                // 设置数据到数组中
                board[i][j] = (char) (k + '0');
                // 是否合理
                if (isValidateFill(board, i, j, (char) (k + '0'))) {
                    if (solveReclusiveSudoku(board, i, j + 1)) return true;
                }
                // 不合理设置为初始值
                    board[i][j] = '.';
            }
        } else

            // 此位置的元素为数字
            return  solveReclusiveSudoku(board, i, j + 1);
        return false;

    }

    private boolean isValidateFill(char[][] board, int i, int j, char c) {

        for (int k = 0; k < 9; k++) {

            if ((k!=j&&board[i][k] == c) || (k!=i&&board[k][j] == c) || (3 * (i / 3) + k / 3!=i&&3 * (j / 3) + k % 3!=j&&board[3 * (i / 3) + k / 3][3 * (j / 3) + k % 3] == c))
                return false;
        }
        return true;
    }

    public static void main(String... args) {
        isValidSudokuC isValidSudokuC = new isValidSudokuC();
        char[][] datas = new char[9][9];

        datas[0] = new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'};
        datas[1] = new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'};
        datas[2] = new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'};
        datas[3] = new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'};
        datas[4] = new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'};
        datas[5] = new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'};
        datas[6] = new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'};
        datas[7] = new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'};
        datas[8] = new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'};
        isValidSudokuC.solveSudoku(datas);
        System.out.println(datas);

    }
}
