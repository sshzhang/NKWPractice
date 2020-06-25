package com.leetcode.domain;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class MapAnalysis1162 {
    public static void main(String... args) {
        System.out.println(new MapAnalysis1162().findMinHeightTrees(1,new int[][]{{}}));
    }

    class Node {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    /**
     * 暴力求解  对于每一个海洋利用BFS计算到所有陆地的最小距离，然后选择距离最大的最小值。, 总的时间为O(n^4)
     * 超时
     */
    public int maxDistance1(int[][] grid) {

        // 首先排除所有元素都为0,或者1情况
        boolean isbothSame = true;
        int len = grid.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != grid[0][0]) {
                    isbothSame = false;
                    break;
                }
            }
        }


        if (isbothSame) return -1;
        int max = -1, curr = -1;
        boolean isVisited[][] = new boolean[len][grid[0].length];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) continue;
                int key = i;
                int value = j;
                // key, vaue,  找key相同value
                Queue<Node> queue = new LinkedBlockingQueue<>();
                queue.offer(new Node(key, value));
                while (!queue.isEmpty()) {
                    Node pop = queue.poll();
                    isVisited[pop.x][pop.y] = true;
                    if (grid[pop.x][pop.y] == 1) {
                        curr = Math.abs(key - pop.x) + Math.abs(value - pop.y);
                        break;
                    }
                    // 上
                    if (pop.x > 0 && !isVisited[pop.x - 1][pop.y]) {
                        queue.offer(new Node(pop.x - 1, pop.y));
                    }
                    // 下
                    if (pop.x < len - 1 && !isVisited[pop.x + 1][pop.y]) {
                        queue.offer(new Node(pop.x + 1, pop.y));
                    }
                    // 左
                    if (pop.y > 0 && !isVisited[pop.x][pop.y - 1]) {
                        queue.offer(new Node(pop.x, pop.y - 1));
                    }
                    // 右
                    if (pop.y < grid[0].length - 1 && !isVisited[pop.x][pop.y + 1]) {
                        queue.offer(new Node(pop.x, pop.y + 1));
                    }
                }
                for (int k = 0; k < len; k++) {
                    for (int l = 0; l < grid[0].length; l++) {
                        isVisited[k][l] = false;
                    }
                }
                max = Math.max(max, curr);
            }
        }

        return max;
    }


    /**
     * 和第一种类似，也是使用BFS. 但是，此方法计算所有陆地到海洋的距离，
     * 最后一个达到的海洋一定是离所有陆地最远的哪个海洋。同时也是这个海洋到所有陆地的最近距离
     * 时间复杂度为O(n^2) 空间复杂度为(n^2)
     *
     * @param grid
     * @return
     */
    public int maxDistance2(int[][] grid) {
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        int len = grid.length;
        int coln = grid[0].length;
        // 保存数据元素
        Queue<int[]> queue = new LinkedBlockingQueue<>();
        // 针对所有的陆地, 执行多源BFS，最后一个遍历的元素对用的距离就是离所有陆地最远的哪个海洋，
        // 同时也是海洋到所有陆地的最近距离
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < coln; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int[] point = null;
        boolean hasOceation = false;
        while (!queue.isEmpty()) {
            point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];
                if (nx < 0 || nx >= len || ny < 0 || ny < coln || grid[nx][ny] != 0) continue;
                queue.offer(new int[]{nx, ny});
                grid[nx][ny] = grid[point[0]][point[1]] + 1;
                hasOceation = true;
            }
        }
        // 没陆地或者没有海洋
        if (point == null || !hasOceation) {

            return -1;
        }
        return grid[point[0]][point[1]] - 1; // 减去最开始陆地状态节点1
    }


    class Graph {
        int n; //节点个数
        GNode elemnets[];

        public Graph(int n) {
            this.n = n;
            elemnets = new GNode[n];
        }
    }

    class GNode {
        GNode next;
        int adjvex;
        int degree = 0;
        public GNode(int adjvex) {
            this.adjvex = adjvex;
        }
    }


    /**
     * 超时 O(n^2)
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        // 构建无向图
        Graph graph = new Graph(n);
        for (int i = 0; i < n; i++) {
            graph.elemnets[i] = new GNode(i);
        }
        for (int i = 0; i < edges.length; i++) {
            int m = edges[i][0];
            int k = edges[i][1];
            GNode fnode = new GNode(m);
            GNode snode = new GNode(k);
            snode.next = graph.elemnets[m].next;
            graph.elemnets[m].next = snode;
            fnode.next = graph.elemnets[k].next;
            graph.elemnets[k].next = fnode;
        }

        int distance[] = new int[n];
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        int min = n, curr = n, poll = -1;
        for (int i = 0; i < n; i++) {
            queue.offer(i);
            while (!queue.isEmpty()) {
                poll = queue.poll();
                for (GNode sr = graph.elemnets[poll]; sr != null; sr = sr.next) {
                    if (sr.adjvex != i && distance[sr.adjvex] == 0) {
                        queue.offer(sr.adjvex);
                        distance[sr.adjvex] = distance[poll] + 1;
                    }
                }
            }

            curr = distance[poll];
            if(curr<min){
                min = curr;
                result.clear();
                result.add(i);
            }else if(curr==min){
                result.add(i);
            }
            queue.clear();
            for (int k = 0; k < n; k++) {
                distance[k] = 0;
            }
        }
        return result;
    }

    /**
     * 拓扑排序
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 构建无向图
        Graph graph = new Graph(n);
        for (int i = 0; i < n; i++) {
            graph.elemnets[i] = new GNode(i);
        }
        for (int i = 0; i < edges.length; i++) {
            int m = edges[i][0];
            int k = edges[i][1];
            GNode fnode = new GNode(m);
            GNode snode = new GNode(k);
            snode.next = graph.elemnets[m].next;
            graph.elemnets[m].next = snode;
            fnode.next = graph.elemnets[k].next;
            graph.elemnets[k].next = fnode;
            graph.elemnets[m].degree++;
            graph.elemnets[k].degree++;
        }


        List<Integer> params = new ArrayList<>();
        int dNum = 0;
        while(n-dNum>2){
            for (int i = 0; i < n; i++) {
                if (graph.elemnets[i].degree == 1) {
                    params.add(i);
                }
            }
            // 更新节点的度
            for (Integer param : params) {
                for (GNode scr = graph.elemnets[param]; scr != null; scr = scr.next) {
                    // 度为-1表示数据已经删除了
                    if(graph.elemnets[scr.adjvex].degree!=-1)
                    graph.elemnets[scr.adjvex].degree--;
                }
                // 删除
                graph.elemnets[param].degree = -1;
            }
            dNum += params.size();
            params.clear();
        }
        params.clear();
        for (int i = 0; i < n; i++) {
            if(graph.elemnets[i].degree!=-1) params.add(i);
        }
        return params;
    }

}
