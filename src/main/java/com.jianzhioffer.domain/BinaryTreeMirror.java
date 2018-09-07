package com.jianzhioffer.domain;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树的镜像
 *
 * 其实就是左右节点交换一下
 */
public class BinaryTreeMirror {

    public void Mirror(TreeNode root) {

        Queue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode poll = queue.poll();
            if (!(poll.left == null && poll.right == null)) {
                TreeNode left = poll.left;
                poll.left = poll.right;
                poll.right = left;
            }
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }

        System.out.println(root);

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

        TreeNode treeNode = new TreeNode(8);
        TreeNode treeNode1 = new TreeNode(6);
        TreeNode treeNode2 = new TreeNode(10);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = new TreeNode(5);
        treeNode1.right = new TreeNode(7);
        treeNode2.left = new TreeNode(9);
        treeNode2.right = new TreeNode(11);
        new BinaryTreeMirror().Mirror(treeNode);

    }

}
