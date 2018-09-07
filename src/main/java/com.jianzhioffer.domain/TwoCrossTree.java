package com.jianzhioffer.domain;

/**
 * 通过前序和中序遍历 得到后序便利
 */

public class TwoCrossTree {


    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {

        TreeNode root = new TreeNode(-1);
        TreeNode treeNode = ReClusive(pre, in, root, -1);

        System.out.println(treeNode);
        System.out.println(root);
        return root;
    }


    public TreeNode ReClusive(int[] pre, int[] in,TreeNode node,int flages) {
        //找出中序遍历的根节点位置
        if(in.length==0&&pre.length==0)
            return node;
        int root = pre[0];
        if (flages==-1) {
            //为空
            node.val = root;
        } else if (flages == 0) {
            node.left = new TreeNode(root);
            node = node.left;
        } else if (flages == 1) {
            node.right = new TreeNode(root);
            node = node.right;
        }
        int index = 0;
        for (int i=0;i<in.length;i++) {//找出根节点所在位置
            if (in[i] == root){
                index = i;
                break;
            }
        }
        int[] in1 = new int[index];
        int[] in2 = new int[in.length - index - 1];
        int[] pre1 = new int[index];
        int[] pre2 = new int[in.length - index - 1];
        for (int i=0;i<index;i++) {
            in1[i] = in[i];
            pre1[i] = pre[i + 1];
        }
        for (int j=0;j<in.length-index-1;j++) {
            in2[j] = in[j + index + 1];
            pre2[j] = pre[j + index + 1];
        }

        ReClusive(pre1, in1, node,0);
        ReClusive(pre2, in2, node, 1);
        return node;
    }

    public static void main(String... args) {
        TwoCrossTree twoCrossTree = new TwoCrossTree();
        TreeNode treeNode = twoCrossTree.reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        System.out.println(treeNode);
    }









    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

}

