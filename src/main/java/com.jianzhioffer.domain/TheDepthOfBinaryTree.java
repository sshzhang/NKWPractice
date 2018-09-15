package com.jianzhioffer.domain;

import java.util.Stack;

/**
 * 二叉树的深度
 *
 *后序遍历解决问题
 *
 */
public class TheDepthOfBinaryTree {


    public Stack<TreeNode> stacks = new Stack<>();

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

   /* private int count = 0, max = 0;

    public int TreeDepth(TreeNode root) {

        if(root==null) return 0;
        count++;
        TreeDepth(root.left);
        TreeDepth(root.right);
        if (root.left == null && root.right == null) {
            max = max > count ? max : count;
        }
        count--;
        return max;
    }*/


    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }


    public static void main(String... args) {

        TreeNode treeNode = new TreeNode(12);
        TreeNode treeleft = new TreeNode(13);
        TreeNode treeright = new TreeNode(14);
        treeNode.left = treeleft;
        treeNode.right = treeright;
        treeleft.right = new TreeNode(16);
        treeleft.right.left = new TreeNode(15);
        System.out.println(new TheDepthOfBinaryTree().TreeDepth(treeNode));

    }

}
