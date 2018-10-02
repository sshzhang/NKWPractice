package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 之字型按层打印二叉树
 */
public class PrintC {


    /**
     * 通过两个栈来实现之字型层次遍历  一个存储奇数层的数据　　一个存储偶数层的数据
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> PrintU(TreeNode pRoot) {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();

        if(pRoot==null) return arrayLists;
        //奇数层节点
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(pRoot);
        //偶数层
        Stack<TreeNode> s2 = new Stack<>();

        int iIndex = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                ArrayList<Integer> integers = new ArrayList<>();
                while (!s1.isEmpty()) {
                    TreeNode pop = s1.pop();
                    integers.add(pop.val);
                    if(pop.left!=null) s2.push(pop.left);
                    if(pop.right!=null) s2.push(pop.right);
                }
                iIndex++;
                arrayLists.add(integers);
            }else{
                ArrayList<Integer> integers = new ArrayList<>();
                while (!s2.isEmpty()) {

                    TreeNode pop = s2.pop();
                    integers.add(pop.val);
                    if(pop.right!=null) s1.push(pop.right);
                    if(pop.left!=null) s1.push(pop.left);
                }
                iIndex++;
                arrayLists.add(integers);
            }
        }
        System.out.println(iIndex);
        return arrayLists;
    }

    //层次打印
    public ArrayList<ArrayList<Integer>> PrintU2(TreeNode pRoot) {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();

        if(pRoot==null) return arrayLists;
        //奇数层节点
        Queue<TreeNode> s1 = new LinkedList<>();
        s1.offer(pRoot);
        //偶数层
        Queue<TreeNode> s2 = new LinkedList<>();

        int iIndex = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {


            if (!s1.isEmpty()) {

                ArrayList<Integer> integers = new ArrayList<>();

                while (!s1.isEmpty()) {
                    TreeNode pop = s1.poll();
                    integers.add(pop.val);
                    if(pop.left!=null) s2.offer(pop.left);
                    if(pop.right!=null) s2.offer(pop.right);
                }
                iIndex++;
                arrayLists.add(integers);
            }else{
                ArrayList<Integer> integers = new ArrayList<>();
                while (!s2.isEmpty()) {

                    TreeNode pop = s2.poll();
                    integers.add(pop.val);
                    if(pop.left!=null) s1.offer(pop.left);
                    if(pop.right!=null) s1.offer(pop.right);
                }
                iIndex++;
                arrayLists.add(integers);

            }

        }

        return arrayLists;
    }


    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        LinkedList<TreeNode> listQue = new LinkedList<>();
        TreeNode currNode = pRoot;
        if(currNode==null) return arrayLists;
        TreeNode preNode = null;
        listQue.offer(currNode);
        int iIndex = 0;
        int layflages = 1;
        ArrayList<Integer> parms = null;
        while (!listQue.isEmpty()) {
            TreeNode poll = listQue.poll();
            if (preNode == null) {//对于根节点
                iIndex++;
                parms = new ArrayList<>();
            } else if (preNode == poll) {
                if(iIndex%2==0){//反向
                    ArrayList copys = new ArrayList<Integer>();
                    for (int i = parms.size() - 1; i >= 0; i--) {
                        copys.add(parms.get(i));
                    }
                    arrayLists.add(copys);
                }else{//正向
                    arrayLists.add(parms);
                }
                parms = new ArrayList<>();
                iIndex++;
                layflages = 1;
            }

            if (poll.left != null) {
                listQue.offer(poll.left);

                if(layflages==1){ //查找下一个层的第一个非空节点
                    preNode =poll.left;
                    layflages=0;
                }
            }

            if (poll.right != null) {

                listQue.offer(poll.right);
                if (layflages == 1) { //查找下一个层的第一个非空节点
                    preNode = poll.right;
                    layflages=0;
                }
            }
                parms.add(poll.val);
        }

        if (parms.size() > 0) {//最后一组元素

            if(iIndex%2==0){//反向
                ArrayList copys = new ArrayList<Integer>();
                for (int i = parms.size() - 1; i >= 0; i--) {
                    copys.add(parms.get(i));
                }
                arrayLists.add(copys);
            }else{//正向
                arrayLists.add(parms);
            }
        }

        System.out.println(iIndex);
        return arrayLists;
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

        TreeNode rootNode = new TreeNode(8);
        TreeNode lrootNode = new TreeNode(6);
        TreeNode rrootNode = new TreeNode(10);
        rootNode.left = lrootNode;
        rootNode.right = rrootNode;
        TreeNode llrootNode = new TreeNode(5);
        TreeNode rlrootNode = new TreeNode(7);
        lrootNode.left = llrootNode;
        lrootNode.right = rlrootNode;
        TreeNode lrrootNode = new TreeNode(9);
        TreeNode rrrootNode = new TreeNode(11);
        rrootNode.left = lrrootNode;
        rrootNode.right = rrrootNode;
        lrrootNode.left = new TreeNode(13);
        new PrintC().PrintU(rootNode);
    }
}
