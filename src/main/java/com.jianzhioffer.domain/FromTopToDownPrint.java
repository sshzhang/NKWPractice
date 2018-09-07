package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 从上到下打印打印二叉树的每个节点
 *
 * 其实就是一个队列问题
 */
public class FromTopToDownPrint {


    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {

        ArrayList<Integer> params = new ArrayList<>();
        if(root==null) return params;
        Queue<TreeNode> que = new LinkedBlockingQueue<TreeNode>();
        que.add(root);
        while (!que.isEmpty()) {
            TreeNode poll = que.poll();
            params.add(poll.val);
            if(poll.left!=null) que.add(poll.left);
            if(poll.right!=null) que.add(poll.right);
        }

        return params;
    }


    static  class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;

        }
    }

    public  int inc() {
        int x;
        try {

            x = 1;
           /* if(x==1)
            throw new Exception();*/
            return x;
        } catch (Exception ex) {
            x = 2;
            return x;
        }finally {
            System.out.println("s");
            x = 3;
        }
    }


    public static void main(String... args) {

        System.out.println(new FromTopToDownPrint().inc());
    }

}
