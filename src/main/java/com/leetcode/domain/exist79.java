package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class exist79 {

    public static boolean isPath = false;

    public boolean exist(char[][] board, String word) {
        if (word == null || "".equals(word) || word.length() == 0) return true;
        if(board==null||board.length==0||board[0].length==0) return false;
        boolean[][] visited = new boolean[board.length][board[0].length];
        isPath = false;
        DFS(new Stack<>(),board,word,0, visited);
        return isPath;
    }

    public List<Point> getTheNextCharPointList(Point point, char[][] boards, char dest, int indexSignal, boolean[][]visited) {
        List<Point> points = new ArrayList<>();
        if (indexSignal == 1) {
            for (int i = 0; i < boards.length; i++) {
                for (int j = 0; j < boards[i].length; j++) {
                    if (visited[i][j]==false&&boards[i][j] == dest) {
                        points.add(new Point(i, j));
                    }
                }
            }
        }else{
            Point newPoint = AdjustDirection(point, dest, boards, visited);
            points.add(newPoint);
            return newPoint == null ? null : points;
        }
        return points;
    }

    private Point AdjustDirection(Point point, char dest, char[][] boards, boolean[][]visited) {

        final  int direction = point.direction;
        int x = point.x;
        int y = point.y;
//        point.setDirection((direction + 1)%5);
        if (direction <4&&direction>=0) {//上

            if (direction == 0 && x != 0 && visited[x - 1][y] == false && boards[x - 1][y] == dest) {
                point.setDirection(1);
                return new Point(x - 1, y);
            }
            if (direction <= 1) {//右
                if (y != boards[0].length - 1 && visited[x][y + 1] == false && boards[x][y + 1] == dest) {
                    point.setDirection(2);
                    return new Point(x, y + 1);
                }
            }  if (direction <= 2) {//下

                if (x != boards.length - 1 && visited[x + 1][y] == false && boards[x + 1][y] == dest) {

                    point.setDirection(3);
                    return new Point(x + 1, y);
                }
            }  if (direction <= 3) {

                if (y != 0 && visited[x][y - 1] == false && boards[x][y - 1] == dest) {
                    point.setDirection(4);
                    return new Point(x, y - 1);
                }
            }
        }
        return null;
    }

    public void DFS(Stack<Point> stack, char[][] boards, String word, int index, boolean[][]visited) {
        if(isPath) return;
        if (index >= word.length()) {
            isPath = true;
            return;
        }
        if (index == 0&&stack.isEmpty()) {//初始赋值
            List<Point> headPoints = getTheNextCharPointList(null, boards, word.charAt(index), 1, visited);
            if(headPoints==null) return;
            for (int k = 0; k < headPoints.size(); k++) {
                if(isPath) return;
                Point point = headPoints.get(k);
                stack.push(point);
                visited[point.x][point.y] = true;
                DFS(stack, boards, word, 1, visited);
                for (int m = 0; m < visited.length; m++) {
                    for (int n = 0; n < visited[m].length; n++) {
                        visited[m][n] = false;
                    }
                }
            }
        }else{
            List<Point> theNextCharPointList = getTheNextCharPointList(stack.peek(), boards, word.charAt(index), 0, visited);
            if (theNextCharPointList == null) {//为空
                Point popState = stack.pop();
                visited[popState.x][popState.y] = false;
                if (!stack.isEmpty() && index - 1 >= 0) {

                    DFS(stack, boards, word, index - 1, visited);
                }
            }else{
                Point point = theNextCharPointList.get(0);
                stack.push(point);
                visited[point.x][point.y] = true;
                DFS(stack, boards, word, index+1, visited);
            }
        }
    }

    class Point {
        int x;
        int y;
        int direction = 0; //0上, 1右， 2下, 3左
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }
    }


    public static void main(String... args) {


        exist79 ob = new exist79();
        char[][] chars = new char[3][4];
        chars[0] = new char[]{'A', 'A','A','A'};
        chars[1] = new char[]{'A', 'A','A','A'};
        chars[2] = new char[]{'A', 'A','A','A'};
        boolean abccde = ob.exist(chars, "AAAAAAAAAAAAA");
        System.out.println(abccde);
    }


    public boolean existU(char[][] board, String word) {
        if(word==null||"".equals(word)||word.length()==0) return true;
        if(board==null||board.length==0||board[0].length==0) return false;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (DFSU(board, word, 0, i, j, visited))
                    return true;
            }
        }
        return false;
    }

    private boolean DFSU(char[][] board, String word, int index, int x, int y, boolean[][] visited) {
        if(index==word.length()) return true;
        if(x<0||y<0||x>=board.length||y>=board[0].length||visited[x][y]||word.charAt(index)!=board[x][y]) return false;
        visited[x][y] = true;
        boolean b = DFSU(board, word, index+1, x, y - 1, visited) || DFSU(board, word, index+1, x, y + 1, visited) || DFSU(board, word, index+1, x + 1, y, visited) || DFSU(board, word, index+1, x - 1, y, visited);
        visited[x][y] = false;
        return b;
    }
}
