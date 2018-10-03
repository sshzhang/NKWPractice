package com.jianzhioffer.domain;

/**
 * 机器人运动范围问题
 *
 */
public class movingCountC {


    public int movingCount(int threshold, int rows, int cols)
    {
        boolean[][] visited = new boolean[rows][cols];
       return ReclusiveMovingDirection(threshold, rows, cols, 0, 0,visited);
    }

    private int ReclusiveMovingDirection(int threshold, int rows, int cols, int r, int c,boolean[][]visited) {

        if(r<0||c<0||r>=rows||c>=cols||sum(r,c)>threshold||visited[r][c]) return 0;
        visited[r][c] = true;
        return ReclusiveMovingDirection(threshold, rows, cols, r, c + 1,visited) + ReclusiveMovingDirection(threshold, rows, cols, r+1, c ,visited) +
                ReclusiveMovingDirection(threshold, rows, cols, r, c - 1,visited) + ReclusiveMovingDirection(threshold, rows, cols, r-1, c,visited) + 1;
    }


    public int sum(int r, int c) {

        int sum = 0;

        while (r != 0) {
            sum += r % 10;
            r = r / 10;
        }

        while (c != 0) {
            sum += c % 10;
            c = c / 10;
        }

        return sum;
    }

}
