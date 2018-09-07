package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉搜索树到排序的双向链表
 * <p>
 * 其实就是一个中序遍历
 */
public class BinarysrarchTreeToLinkList {


    private static final List<TreeNode> params = new ArrayList<>();
    private static final Stack<TreeNode> staks = new Stack<>();

    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree==null) return null;
        immediateDepthTraverseNoReclusive(pRootOfTree);
        params.get(0).left = null;
        TreeNode root = params.get(0);
        for (int i = 0; i < params.size(); i++) {
            params.get(i).right = i + 1 < params.size() ? params.get(i + 1) : null;
            params.get(i).left = i > 0 ? params.get(i - 1) : null;
        }
        params.clear();
        staks.clear();
        return root;
    }
   /* public void immediateDepthTraverse(TreeNode node) {
        if(node==null) return;
        immediateDepthTraverse(node.left);
        params.add(node);
        immediateDepthTraverse(node.right);
    }*/


    //非递归中序遍历
    public void immediateDepthTraverseNoReclusive(TreeNode node) {

        while (node != null) {
            staks.push(node);
            node = node.left;
        }
        while (!staks.isEmpty()) {
            TreeNode pop = staks.pop();
            params.add(pop);
            TreeNode right = pop.right;
            if (right != null) {
                while (right != null) {
                    staks.push(right);
                    right = right.left;
                }
            }
        }

        /*    staks.push(node);
        while (node != null || !staks.isEmpty()) {
            if (node != null) {
                staks.push(node);
                node = node.left;
            } else {
                TreeNode pop = staks.pop();
                params.add(pop);
                node = pop.right;
            }
        }*/
    }


    static  class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }


    public static void main(String... args) {
//10,6,14,4,8,12,16
        TreeNode root = new TreeNode(10);

        TreeNode rootleft = new TreeNode(6);

        TreeNode rootright = new TreeNode(14);

        root.left = rootleft;
        root.right = rootright;

        rootleft.left = new TreeNode(4);
        rootleft.right = new TreeNode(8);

        rootright.left = new TreeNode(12);
        rootright.right = new TreeNode(16);
        TreeNode convert = new BinarysrarchTreeToLinkList().Convert(root);
        System.out.println(convert);

    }
}
