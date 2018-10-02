package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化和反序列化(xianx)
 */
public class SerializeADerializeC {

    private int index = -1;
   /* String Serialize(TreeNode root) {
        int depth = PrintDepth(root);
        StringBuilder builder = new StringBuilder();
        if(depth==0) return "#";
        //奇数层节点
        Queue<TreeNode> s1 = new LinkedList<>();
        s1.offer(root);
        //偶数层
        Queue<TreeNode> s2 = new LinkedList<>();

        int iIndex = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                while (!s1.isEmpty()) {
                    TreeNode pop = s1.poll();
                    int val = pop.val;
                    if (val == '#') {
                        builder.append("#,");
                    }else{
                        builder.append(val + ",");
                    }
                    if(pop.left!=null) s2.offer(pop.left);
                    else if(iIndex<depth-1) s2.offer(new TreeNode('#'));
                    if(pop.right!=null) s2.offer(pop.right);
                    else if(iIndex<depth-1)s2.offer(new TreeNode('#'));
                }
                iIndex++;
            }else{
                ArrayList<Integer> integers = new ArrayList<>();
                while (!s2.isEmpty()) {

                    TreeNode pop = s2.poll();
                    int val = pop.val;
                    if (val == '#') {
                        builder.append("#,");
                    }else{
                        builder.append(val + ",");
                    }
                    if(pop.left!=null) s1.offer(pop.left);
                    else if(iIndex<depth-1) s1.offer(new TreeNode('#'));
                    if(pop.right!=null) s1.offer(pop.right);
                    else if(iIndex<depth-1)s1.offer(new TreeNode('#'));
                }
                iIndex++;
            }
        }

        String s = builder.toString();

        return s.substring(0, s.length() - 1);

    }*/


    TreeNode Deserialize(String str) {
        if(str.length() == 0)
            return null;
        String[] strs = str.split(",");
        return Deserialize2(strs);
    }

    TreeNode Deserialize2(String[] strs) {
        index++;
        if(!strs[index].equals("#")) {
            TreeNode root = new TreeNode(0);
            root.val = Integer.parseInt(strs[index]);
            root.left = Deserialize2(strs);
            root.right = Deserialize2(strs);
            return root;
        }
        return null;
    }

    public int  PrintDepth(TreeNode pRoot) {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();

        if(pRoot==null) return 0;
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
        return iIndex;
    }


   /* TreeNode Deserialize(String str) {

        if(str==null||str.length()==0) return new TreeNode('#');

        String[] split = str.split(",");

        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        TreeNode currroot = root;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int index = 1;

        while (index<split.length&&!que.isEmpty()) {
            TreeNode poll = que.poll();
            String numC = split[index];
            if(poll.val=='#') index += 2;
            else{
                if ("#".equals(numC)) {
                    poll.left = null;
                    que.offer(new TreeNode('#'));
                } else {
                    TreeNode treeNode = new TreeNode(Integer.parseInt(numC));
                    poll.left = treeNode;
                    que.offer(treeNode);
                }
                index++;


                numC = split[index];

                if ("#".equals(numC)) {
                    poll.right = null;
                    que.offer(new TreeNode('#'));
                } else {
                    TreeNode treeNode = new TreeNode(Integer.parseInt(numC));
                    poll.right = treeNode;
                    que.offer(treeNode);
                }
                index++;

            }

        }

        return currroot;
    }*/




    String Serialize1(TreeNode root) {


        if(root==null) return "#";
        TreeNode currNode = root;
        TreeNode preNode = null;
        StringBuilder builder = new StringBuilder();
        Stack<TreeNode> stackNode = new Stack<>();
        while (currNode != null) {
            builder.append(currNode.val+",");
            stackNode.push(currNode);
            currNode = currNode.left;
        }

        while (!stackNode.isEmpty()) {
            TreeNode pop = stackNode.pop();
            TreeNode right = pop.right;
            if(preNode!=null&&right==preNode) {preNode=pop;continue;}
            while (right != null) {
                builder.append(right.val + ",");
                stackNode.push(right);
                right = right.left;
            }
            preNode = pop;
        }

        String s = builder.toString();

        return s.substring(0, s.length()-1);
    }

    TreeNode Deserialize1(String str) {

        return null;
    }



    public static void main(String... args) {
        TreeNode rootNode = new TreeNode(1);
        TreeNode lrootNode = new TreeNode(2);
        TreeNode rrootNode = new TreeNode(3);
        rootNode.left = lrootNode;
        rootNode.right = rrootNode;
        TreeNode llrootNode = new TreeNode(4);
        TreeNode rlrootNode = new TreeNode(5);
        lrootNode.left = llrootNode;
        lrootNode.right = rlrootNode;
        TreeNode lrrootNode = new TreeNode(6);
        TreeNode rrrootNode = new TreeNode(7);
        rrootNode.left = lrrootNode;
        rrootNode.right = rrrootNode;
        lrrootNode.left = new TreeNode(8);


        System.out.println(new SerializeADerializeC().Serializem(rootNode));
        System.out.println(new SerializeADerializeC().Deserializem("1,2,4,#,#,5,#,#,3,6,8,#,#,#,7,#,#"));

    }


    String Serializem(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null){
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val + ",");
        sb.append(Serializem(root.left));
        sb.append(Serializem(root.right));
        return sb.toString();
    }
    TreeNode Deserializem(String str) {
   /*     index++;
        int len = str.length();
        if(index >= len){
            return null;
         }
        String[] strr = str.split(",");
        TreeNode node = null;
        if(!strr[index].equals("#")){
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = Deserializem(str);
            node.right = Deserializem(str);
        }
        return node;
*/


        index++;
       /* int len = str.length();
        if(index >= len){
            return null;
        }*/
        String[] strr = str.split(",");
        TreeNode node = null;
        if(!strr[index].equals("#")){
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = Deserializem(str);
            node.right = Deserializem(str);
        }
        return node;
    }
    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

}
