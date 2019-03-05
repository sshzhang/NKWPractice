package com.leetcode.domain;

import java.util.HashMap;

public class rob337 {


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //深度遍历二叉树  通过hashMap记住之前计算过的数字，从而避免重复计算。
    public int rob(TreeNode root) {
        int maxValue = ReclusiveRob(root, new HashMap<TreeNode, Integer>());

        return maxValue;
    }

    private int ReclusiveRob(TreeNode root, HashMap<TreeNode, Integer> hashs) {
        if (root == null) return 0;
        if (hashs.containsKey(root)) return hashs.get(root);
        int val = 0;
        if (root.left != null) {//包含root
            val += ReclusiveRob(root.left.left, hashs) + ReclusiveRob(root.left.right, hashs);
        }
        if (root.right != null) {
            val += ReclusiveRob(root.right.left, hashs) + ReclusiveRob(root.right.right, hashs);
        }
        val = Math.max((val + root.val), ReclusiveRob(root.left, hashs) + ReclusiveRob(root.right, hashs));
        hashs.put(root, val);
        return val;
    }
}
