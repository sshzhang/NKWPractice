package com.jianzhioffer.domain;

import java.util.ArrayList;

/**
 * 二叉树的下一个节点
 */
public class GetNextC {


    public TreeLinkNode GetNext(TreeLinkNode pNode) {

        if (pNode == null) return null;

        TreeLinkNode parentNode = pNode.next;

        if (parentNode == null) {//根节点

            TreeLinkNode currNode = pNode.right;
            if (currNode == null) return null;
            while (currNode.left != null) {
                currNode = currNode.left;
            }
            return currNode;
        } else {
            //左孩子
            if (parentNode.left == pNode) {
                if (pNode.right == null) return parentNode;
                TreeLinkNode currNode = pNode.right;
                while (currNode.left != null) {
                    currNode = currNode.left;
                }
                return currNode;
            } else {//右孩子


                //最后节点判断
                if (pNode.right == null) {
                    TreeLinkNode endNode = pNode;
                    //外加一个判断  是否是中序遍历的最后一个节点
                    while (endNode.next!=null&&endNode.next.right==endNode){
                        endNode = endNode.next;
                    }
                    //表示是中序遍历的最后一个节点
                    if(endNode.next==null) return null;
                    else{
                        return parentNode.next == null ? null : parentNode.next;
                    }
                }

                TreeLinkNode currNode = pNode.right;
                while (currNode.left != null) {
                    currNode = currNode.left;
                }
                return currNode;
            }
        }

    }


    static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public static void main(String... args) {

        ArrayList<ArrayList<Integer>> sm = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        sm.add(integers);
        integers.clear();
        System.out.println(sm);

    }
}


