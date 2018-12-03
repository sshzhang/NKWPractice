package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class solveNQueensC51 {


    public List<List<String>> results = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        results.clear();
        char[][] params = new char[n][n];

        for (int i = 0; i < params.length;i++) {

            for (int j = 0; j < params[i].length; j++) {
                params[i][j] = '.';
            }
        }

        ReclusiveTheQueue(n, 0, new Stack<MyNode>(),params);
        return results;
    }


    public void ReclusiveTheQueue(int n, int startI, Stack<MyNode> stacks, char[][]params) {

        if (startI == n) {

            List<String> result = new ArrayList<String>();

            for (int i = 0; i < params[0].length; i++) {
                result.add(String.valueOf(params[i]));
            }
            results.add(result);
            return;
        }

        for (int j = 0; j < n; j++) {

            if (isPassWays(stacks, startI, j)) {
                stacks.push(new MyNode(startI, j));
                params[startI][j] = 'Q';
                ReclusiveTheQueue(n, startI + 1, stacks, params);
                params[startI][j] = '.';
                stacks.pop();
            }
        }


    }

    public boolean isPassWays(Stack<MyNode> stacks, int x, int y) {

        for (int i = 0; i < stacks.size(); i++) {

            MyNode myNode = stacks.get(i);
            if (x + y == myNode.x + myNode.y || x - y == myNode.x - myNode.y || y == myNode.y) {
                return false;
            }
        }

        return true;
    }




    class MyNode {

        int x;
        int y;

        public MyNode(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof MyNode) {

                MyNode dest = (MyNode) obj;

                return this.x == dest.x && this.y == dest.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(x) + Integer.hashCode(y);
        }
    }

}
