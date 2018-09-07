package com.jianzhioffer.domain;


import java.util.ArrayList;
import java.util.Stack;

/**
 * 二叉树中和为某一值的路径
 * 非递归算法
 *
 * 本质上时先序遍历的非递归算法
 *
 * 记住要标识已经删除过的有孩子节点
 * 避免 重复循环进入同一条路径
 */
public class TwoBinarySumAllPathNoRecursion {

    public static ArrayList<ArrayList<Integer>> params = new ArrayList<>();
    public static Stack<TreeNode> temtStk = new Stack<>();
    public static int sum = 0;


    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        params.clear();
        DeepVisitedNoRecursion(root, target);
        return params;
    }

    public void DeepVisitedNoRecursion(TreeNode root, int target) {
        while (root != null) {//一直push 左边的元素到栈中
            sum += root.val;
            temtStk.push(root);
            root = root.left;
        }
        TreeNode visitedNode = null;
        while (!temtStk.isEmpty()) {
            TreeNode peek = temtStk.peek();
            //判断右孩子节点是否被删除过
            if(visitedNode!=null&&peek.right==visitedNode){visitedNode=temtStk.pop(); sum-=visitedNode.val; continue;}
            TreeNode right = peek.right;
            if (right == null) {//右孩子为空
                if(peek.left==null) {//左孩子为空  说明叶子节点
                    if (sum == target) {//路径和等于target
                        ArrayList<Integer> integers = new ArrayList<>();
                        for (int i = 0; i < temtStk.size(); i++) {
                            integers.add(temtStk.get(i).val);
                        }
                        params.add(integers);
                    }
                }
                // 元素出栈
                visitedNode=temtStk.pop();
                sum -= peek.val;
            } else {//右孩子不为空 入栈  左孩子开始入栈
                while (right != null) {//左节点
                    sum += right.val;
                    temtStk.push(right);
                    right = right.left;
                }
            }
        }
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String... args) {

        TreeNode root = new TreeNode(10);
        TreeNode leftroot = new TreeNode(5);
        root.left = leftroot;
        leftroot.left = new TreeNode(4);
        leftroot.right = new TreeNode(7);
        root.right = new TreeNode(12);
        System.out.println(new TwoBinarySumAllPathNoRecursion().FindPath(root, 22));

    }
}
