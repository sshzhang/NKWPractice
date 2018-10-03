package com.leetcode.domain;

import java.util.Stack;

/**
 * 二叉树的最小深度
 */
public class MiniDepthOfBinaryTree {


    private int mini = Integer.MAX_VALUE;

    private int index = 0;

    public int run(TreeNode root) {

        mini = Integer.MAX_VALUE;
        index = 0;
        ReclusiveDepth(root);
        return mini;
    }

    private void  ReclusiveDepth(TreeNode root) {

        if(root==null) return ;
        index++;
        if(root.left==null&&root.right==null) mini = mini > index ? index : mini;
        ReclusiveDepth(root.left);
        ReclusiveDepth(root.right);
        index--;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }


    public int runU(TreeNode root) {

        //中序遍历非递归
        if(root==null) return 0;
        Stack<TreeNode> stackNodes = new Stack<>();
        TreeNode currNode = root;
        while (currNode != null) {
            stackNodes.push(currNode);
            currNode = currNode.left;
        }
        while (!stackNodes.isEmpty()) {
            TreeNode pop = stackNodes.pop();
            System.out.println(pop.val);
            TreeNode right = pop.right;
            while (right != null) {
                stackNodes.push(right);
                right = right.left;
            }
        }
        return 1;
    }


    public int runU2(TreeNode root) {

        //先序遍历
        if(root==null) return 0;

        int index = 0;
        int mini = Integer.MAX_VALUE;
        Stack<TreeNode> stackNodes = new Stack<>();
        TreeNode currNode = root;
        TreeNode preNode = null;

        while (currNode != null) {
            stackNodes.push(currNode);
            currNode = currNode.left;
            index++;
        }

        while (!stackNodes.isEmpty()) {
            TreeNode pop = stackNodes.peek();
            TreeNode right = pop.right;
            if(preNode!=null&&right==preNode){preNode=stackNodes.pop();index--;continue;}
            if (right == null) {
                preNode = stackNodes.pop();
                if(preNode.left==null) mini = mini > index ? index : mini;
                index--;

            }else{
                while (right!=null){
                    stackNodes.push(right);
                    right = right.left;
                    index++;
                }
            }
        }
        return mini;
    }


    public int runU3(TreeNode root) {

        //后序遍历
        TreeNode currNode = root;
        Stack<TreeNode> stackNodes = new Stack<>();

        TreeNode preNode = null;
        while (currNode != null) {
            stackNodes.push(currNode);
            currNode = currNode.left;
        }

        while (!stackNodes.isEmpty()) {
            TreeNode peek = stackNodes.peek();
            TreeNode right = peek.right;
            if(preNode!=null&&right==preNode){preNode=stackNodes.pop();continue;}
            if (right == null) {
                TreeNode pop = stackNodes.pop();
                preNode = pop;
                System.out.println(pop.val);
            }else{

                while (right != null) {
                    stackNodes.push(right);
                    right = right.left;
                }
            }
        }
        return 0;
    }




}
