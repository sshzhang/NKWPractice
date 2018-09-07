package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * 二叉树中和为某一值得所有路径
 *
 * 先通过递归求解
 *
 *
 * 再通过非递归求解
 *
 *
 */
public class TwoBinarySumAllPath {

    public static Stack<Integer> stacks = new Stack<>();
    public static ArrayList<ArrayList<Integer>> params = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        params.clear();
        DepthVerbose(root, target);
        params.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o2.size() - o1.size();
            }
        });
        return params;
    }

    public void DepthVerbose(TreeNode node,int target) {
        if(node==null) return;
        stacks.push(node.val);
        DepthVerbose(node.left,target);
        DepthVerbose(node.right,target);
        if(node.left==null&&node.right==null) {
            //计算
            if (target == CountSum()) {
                ArrayList<Integer> integers = new ArrayList<>();
                Iterator<Integer> iterator =
                        stacks.iterator();
                while (iterator.hasNext()) {
                    integers.add(iterator.next());
                }
                params.add(integers);
            }
        }
        stacks.pop();
    }
    public static int CountSum() {
        int sum = 0;
        Iterator<Integer> iterator =
                stacks.iterator();
        while (iterator.hasNext()) {
            sum+=iterator.next();
        }
        return sum;
    }


    static   class TreeNode {
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
        System.out.println(new TwoBinarySumAllPath().FindPath(root, 12));


    }
}
