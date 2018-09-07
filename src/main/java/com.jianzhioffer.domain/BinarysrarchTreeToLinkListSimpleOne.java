package com.jianzhioffer.domain;

import java.util.Stack;

public class BinarysrarchTreeToLinkListSimpleOne {

    private static final Stack<TreeNode> staks = new Stack<>();

    public TreeNode Convert(TreeNode pRootOfTree) {
        return immediateDepthTraverseNoReclusive(pRootOfTree);
    }

    //非递归中序遍历
    public TreeNode immediateDepthTraverseNoReclusive(TreeNode node) {

        TreeNode previous = null, root = null;

        while (node != null) {
            staks.push(node);
            node = node.left;
        }
        while (!staks.isEmpty()) {
            TreeNode pop = staks.pop();
            if (previous != null) {//链接数据操作
                previous.right = pop;
                pop.left = previous;
                previous = pop;
            }
            //第一个元素
            if (previous == null) root = pop;
            previous = pop;
            TreeNode right = pop.right;
            if (right != null) {
                while (right != null) {
                    staks.push(right);
                    right = right.left;
                }
            }
        }
        return root;

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
