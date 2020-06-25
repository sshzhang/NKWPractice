package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NumIsland200 {

    public int numIslands(char[][] grid) {
        int[] pointX = new int[]{-1, 0, 1, 0};
        int[] pointY = new int[]{0, -1, 0, 1};
        // 深度优先遍历
        int num = 0;
        Stack<int[]> stack = new Stack<>();
        int rlen = grid.length;
        if (rlen == 0) return num;
        int clen = grid[0].length;
        int[] onePosition = null;

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (grid[i][j] == '1') {
                    list.add(new int[]{i, j});
                }
            }
        }
        while (list.size() > 0 && (onePosition = list.get(0)) != null) {
            if (grid[onePosition[0]][onePosition[1]] == '0') {
                list.remove(onePosition);
            } else {
                stack.push(onePosition);
                num++;
                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();
                    grid[pop[0]][pop[1]] = '0';
                    for (int i = 0; i < 4; i++) {
                        if (pop[0] + pointX[i] >= 0 && pop[0] + pointX[i] < rlen && pop[1] + pointY[i] >= 0 && pop[1] + pointY[i] < clen && grid[pop[0] + pointX[i]][pop[1] + pointY[i]] == '1') {
                            stack.push(new int[]{pop[0] + pointX[i], pop[1] + pointY[i]});
                        }
                    }
                }
            }
        }
        return num;
    }


    /**
     * 利用并查集来实现
     *
     * @param grid
     * @return
     */
    public int numIslandsU(char[][] grid) {

        int[] pointX = new int[]{-1, 0, 1, 0};
        int[] pointY = new int[]{0, -1, 0, 1};
        int count = 0;
        int rlen = grid.length;
        if (rlen == 0) return count;
        int clen = grid[0].length;

        UnionFind unionFind = new UnionFind(rlen * clen);
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (grid[i][j] == '1') count++;
                unionFind.parent[i * clen + j] = i * clen + j;
            }
        }

        unionFind.count = count;
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    for (int c = 0; c < 4; c++)
                        if (i + pointX[c] >= 0 && i + pointX[c] < rlen && j + pointY[c] >= 0 && j + pointY[c] < clen && grid[i + pointX[c]][j + pointY[c]] == '1') {
                            unionFind.Merge(i * clen + j, (i + pointX[c]) * clen + j + pointY[c]);
                        }
                }
            }
        }
        return unionFind.getCount();
    }

        public static void main (String...args){

            System.out.println(new NumIsland200().numIslandsU(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}));
        }

    }

    /**
     * 并查集类
     */
    class UnionFind {

        int count; // 表示最终具有的并查集个数

        int parent[]; // 表示相应的父节点

        int rank[]; // 表示当前节点所在并查集的最大长度

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
        }

        public int Find(int i) {
            while (i != parent[i]) i = parent[i];
            return parent[i];
        }

        public void Merge(int i, int j) {
            int parentI = Find(i);
            int parentJ = Find(j);
            if (parentI != parentJ) {
                if (rank[parentI] < rank[parentJ]) {
                    parent[parentI] = parentJ;
                } else if (rank[parentI] > rank[parentJ]) {
                    parent[parentJ] = parentI;
                } else {
                    parent[parentI] = parentJ;
                    rank[parentJ]++;
                }
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }