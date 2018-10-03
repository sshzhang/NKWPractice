package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 矩阵中的路径
 */
public class hasPathC {


    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {

        if(str==null||matrix==null||str.length>matrix.length) return false;


        int[] addRow = new int[]{0,0, 1, 0, -1};
        int[] addCol = new int[]{0,1,0,-1,0};

        int[] visited = new int[matrix.length];
        for (int i=0;i<visited.length;i++) visited[i]=0;
        ArrayList<PointPosition> startPoints= new ArrayList<>();
        //找到所有startPoint
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == str[0]) {
                startPoints.add(new PointPosition(getRowIndex(i, cols), getColsIndex(i, cols), str[0]));
            }
        }

        if (startPoints.size() == 0) {
            return false;
        }else {
            for (int i = 0; i < startPoints.size(); i++) {
                PointPosition startPoint = startPoints.get(i);
                int position = getPosition(startPoint.row, startPoint.col, cols);
                visited[position] = 1;
                Stack<PointPosition> sks = new Stack<>();
                sks.push(startPoint);
                int index = 1;
                while (!sks.isEmpty()&&index<str.length) {
                    PointPosition peek = sks.peek();
                    //下一个方向
                    int direction = peek.direction+1;
                    while (direction <= 4) {
                        int currRow = peek.row + addRow[direction];
                        int currCol = peek.col + addCol[direction];
                        if(currCol>cols-1||currRow>rows-1||currCol<0||currRow<0) {direction++;continue;}
                        int positioncurr = getPosition(currRow, currCol, cols);
                        if(visited[positioncurr]==0&&matrix[positioncurr]==str[index]){//可以入栈
                            peek.direction = direction;
                            sks.push(new PointPosition(currRow, currCol, matrix[positioncurr]));
                            visited[positioncurr] = 1;
                            index++;
                            break;

                        }else{
                            direction++;
                        }

                    }

                    if(direction>4) {
                        PointPosition pop = sks.pop();
                        visited[getPosition(pop.row, pop.col, cols)] = 0;
                        index--;
                    }
                }
                 // 匹配所有字符
                if(!sks.isEmpty()&&index>=str.length) return true;
                //说明不存在满足条件
                //if(sks.isEmpty()) return false;
            }
        }



        return false;
    }


    public static void main(String... args) {
        boolean b = new hasPathC().hasPath(new char[]{'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'}, 3, 4, new char[]{'a', 'b', 'c', 'b'});
        System.out.println(b);
    }


    public int getRowIndex(int i,int cols) {

        return i / cols;
    }

    public int getColsIndex(int i, int cols) {
        return i - i / cols * cols;
    }

    public int getPosition(int rowIndex, int colsIndex,int cols) {

        return rowIndex * cols + colsIndex;
    }




    static  class  PointPosition{

        int row;//行号
        int col;//列号
        char val;
        int direction = 0;//0　1 2 3 分别表示 右　下　左　上
        PointPosition(int row, int col, char val) {

            this.row = row;
            this.col = col;
            this.val = val;
        }

    }

}
