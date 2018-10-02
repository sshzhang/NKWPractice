package com.jianzhioffer.domain;

import java.util.LinkedList;

/**
 * 判断一颗二叉树是否对称
 */
public class isSymmetricalC {


    public static final LinkedList<TreeNode> leftQue = new LinkedList<>();

    public static final LinkedList<TreeNode> rightQue = new LinkedList<>();

    boolean isSymmetrical(TreeNode pRoot)
    {

        leftQue.clear();
        rightQue.clear();
        TreeNode currNode = pRoot;
        if (currNode.left != null &&currNode.right!=null&& currNode.left.val == currNode.right.val) {
            leftQue.add(currNode.left);
            rightQue.add(currNode.right);

            while (!leftQue.isEmpty() && !rightQue.isEmpty()) {

                TreeNode pollleft = leftQue.poll();
                TreeNode pollright = rightQue.poll();

                if (pollleft.left != null && pollright.right != null && pollleft.left.val == pollright.right.val) {
                    leftQue.offer(pollleft.left);
                    rightQue.offer(pollright.right);
                } else if (pollleft.left == null && pollright.right == null) {

                }else{
                    return false;
                }

                if (pollleft.right != null && pollright.left != null && pollleft.right.val == pollright.left.val) {
                    leftQue.offer(pollleft.right);
                    rightQue.offer(pollright.left);
                } else if (pollleft.right == null && pollright.left == null) {

                }else{
                    return false;
                }

            }

            if(!leftQue.isEmpty()||!rightQue.isEmpty()) return false;

        } else if (pRoot.left == null && pRoot.right == null) {
            return true;
        }else
            return false;
        return true;
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
