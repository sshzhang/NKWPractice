package com.leetcode.domain;

import java.util.*;

/**
 * 保存每一层中最后一个元素  广度优先遍历  深度优先遍历
 */
public class TreeView199 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public static void main(String... args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);

        TreeNode rl = new TreeNode(4);
        root.left = left;
        root.right = right;
        left.right = new TreeNode(5);
        right.right = rl;
        rl.left = new TreeNode(9);
        right.left = new TreeNode(7);
        List<Integer> integers = new TreeView199().rightSideView(root);
        System.out.println(integers);

    }

    /**
     * 广度优先遍历解决
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        TreeNode curr = root;
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        int count = 0, levelNums = 1;
        queue.offer(curr);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            count++;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
            if (count == levelNums) {
                levelNums = queue.size();
                count = 0;
                result.add(poll.val);
            }
        }
        return result;
    }

    /**
     * 深度优先遍历， 本质上就是利用先序遍历相反的顺序, 根右左，来获取每一层中最后一个数据
     * @param root
     * @return
     */
    public List<Integer> rightSideViewDFSReclusive(TreeNode root) {
        List<Integer> results = new ArrayList<Integer>();
        rightSideReclusive(root, 0, results);
        return results;
    }

    public void rightSideReclusive(TreeNode root, int step, List<Integer> results) {
        if (root != null) {
            if (results.size() == step) {
                results.add(root.val);
            }
            rightSideReclusive(root.right, step + 1, results);
            rightSideReclusive(root.left, step + 1, results);
        }
    }


    /**
     * DFS 非递归版本
     * @param root
     * @return
     */
    public List<Integer> rightSideViewDFSNoReclusive(TreeNode root) {
        List<Integer> results = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> levelNum = new Stack<>();
        stack.push(root);
        levelNum.push(0);
        while (!stack.isEmpty()) {

            TreeNode pop = stack.pop();
            int lnums = levelNum.pop();
            if(pop!=null){
                if(results.size()==lnums) results.add(pop.val);
                stack.push(pop.left);
                stack.push(pop.right);
                levelNum.push(lnums + 1);
                levelNum.push(lnums + 1);

            }
        }
        return results;
    }

}
