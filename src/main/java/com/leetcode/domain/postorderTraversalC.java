package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Stack;

public class postorderTraversalC {


    //后续遍历非递归
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> integers = new ArrayList<>();
        TreeNode currNode = root;
        TreeNode pre = null;
        if(currNode==null) return integers;

        while (currNode != null) {

            stack.push(currNode);
            currNode = currNode.left;
        }

        while (!stack.isEmpty()) {

            TreeNode peek = stack.peek();

            TreeNode right = peek.right;

            if(pre!=null&&pre==right){pre=stack.pop();integers.add(pre.val);continue;}

            if (right != null) {

                while (right != null) {
                    stack.push(right);
                    right = right.left;
                }

            }else{
                pre = stack.pop();
                integers.add(pre.val);
            }


        }

        return integers;
    }



    //先序遍历非递归
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> integers = new ArrayList<>();
        TreeNode currNode = root;
        TreeNode pre = null;
        if(currNode==null) return integers;

        while (currNode != null) {

            integers.add(currNode.val);
            stack.push(currNode);
            currNode = currNode.left;
        }

        while (!stack.isEmpty()) {

            TreeNode peek = stack.peek();

            TreeNode right = peek.right;

            if(pre!=null&&pre==right){pre=stack.pop();continue;}

            if (right != null) {

                while (right != null) {
                    integers.add(right.val);
                    stack.push(right);
                    right = right.left;
                }

            }else{
                pre = stack.pop();
            }


        }

        return integers;
    }

    ArrayList<Integer> nodes = new ArrayList<>();

    public ArrayList<Integer> postorderTraversalU(TreeNode root) {

        nodes.clear();
        ReclusiveData(root);

        return nodes;
    }

    public void ReclusiveData(TreeNode head) {

        if(head==null) return ;
        ReclusiveData(head.left);
        ReclusiveData(head.right);
        nodes.add(head.val);
    }




    static  class TreeNode{

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }

    }

}
