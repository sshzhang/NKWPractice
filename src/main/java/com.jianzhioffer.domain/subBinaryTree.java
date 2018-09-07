package com.jianzhioffer.domain;

import java.util.Stack;

/**
 * 二叉树子结构问题
 * 判断 root2是不是root1的子结构
 * ps 我们约定 空树不是任意一个树的子结构
 */
public class subBinaryTree {


    public boolean HasSubtree(TreeNode root1,TreeNode root2) {

        if(root1==null||root2==null) return false;
        //层次遍历
        Stack<TreeNode> quu = new Stack<TreeNode>();
        quu.push(root1);
        while (!quu.isEmpty()) {
            TreeNode poll = quu.pop();
            if (poll.val == root2.val) {
                //比较操作
                if (!SameStructure(poll, root2)) {
                    if(poll.left!=null) quu.push(poll.left);
                    if(poll.right!=null) quu.push(poll.right);
                }else{
                    return true;
                }
            }
        }
        return false;
    }

    private boolean  SameStructure(TreeNode poll, TreeNode root2) {

        Stack<TreeNode> qu1 = new Stack<TreeNode>();
        Stack<TreeNode> qu2 = new Stack<TreeNode>();
        qu1.push(poll);
        qu2.push(root2);
        while (!qu1.isEmpty() && !qu2.isEmpty()) {
            TreeNode poll1 = qu1.pop();
            TreeNode poll2 = qu2.pop();
            if (poll1.val != poll2.val) {
                return false;
            }
            if (poll2.left != null) {
                if(poll1.left==null) return false;
                qu1.push(poll1.left);
                qu2.push(poll2.left);
            }
            if (poll2.right != null) {
                if(poll1.right==null) return false;
                qu1.push(poll1.right);
                qu2.push(poll2.right);
            }
        }
        if(qu1.isEmpty()&&!qu2.isEmpty()) return false;
        return true;
    }


    public static void main(String... args) {

        TreeNode treeNode = new TreeNode(12);
        treeNode.left = new TreeNode(13);
        treeNode.right = new TreeNode(14);

        TreeNode treeNode2 = new TreeNode(12);
        treeNode2.left = new TreeNode(13);
        System.out.println(new subBinaryTree().HasSubtree(treeNode, treeNode2));

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
