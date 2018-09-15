package com.jianzhioffer.domain;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 判断一个树是不是平衡二叉树
 */
public class BalanceBinaryTreeProblem {


    public Stack<TreeNode> stacks = new Stack<>();
    public Queue<TreeNode> queue = new LinkedBlockingQueue<>();
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            int left = TreeDepth(poll.left);
            int right = TreeDepth(poll.right);
            if(Math.abs(left-right)>1) return false;

            if(poll.left!=null) queue.add(poll.left);
            if(poll.right!=null) queue.add(poll.right);
        }
        return true;
    }
    public int TreeDepth(TreeNode root) {
        int max = 0, count = 0;
        TreeNode previous = null;
        while (root != null) {
            count++;
            stacks.push(root);
            root = root.left;
        }

        while (!stacks.isEmpty()) {

            TreeNode right = stacks.peek().right;
            if (right == previous && right != null) {//加一个非null判断是为了后面处理
                count--;
                previous = stacks.pop();
                System.out.println(previous.val);
                continue;
            }

            if (right != null) {
                while (right != null) {
                    count++;
                    stacks.push(right);
                    right = right.left;
                }
            }else{
                TreeNode pop = stacks.pop();
                if (pop.left == null && pop.right == null) {
                    max = max > count ? max : count;
                }
                count--;
                previous = pop;
                System.out.println(pop.val);
            }

        }
        return max;
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

}
