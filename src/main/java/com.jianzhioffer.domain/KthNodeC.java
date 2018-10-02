package com.jianzhioffer.domain;

import java.util.Stack;

/**
 * 　搜索树中的第k小元素
 */
public class KthNodeC {


    TreeNode KthNode(TreeNode pRoot, int k)
    {

        int index = 0;

        TreeNode currNode = pRoot;

        Stack<TreeNode> sNode = new Stack<>();
        while (currNode != null) {
            sNode.push(currNode);
            currNode = currNode.left;
        }


        while (!sNode.isEmpty()) {

            TreeNode pop = sNode.pop();
            index++;
            if(index==k) return  pop;

            TreeNode right = pop.right;
            while (right != null) {
                sNode.push(right);
                right = right.left;
            }

        }
        return null;
    }

    static  class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
