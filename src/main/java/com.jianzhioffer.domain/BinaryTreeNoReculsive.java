package com.jianzhioffer.domain;
import java.util.*;

/**
 * 不同字符的全排列
 *
 * 及二叉树的所有非递归方式
 *
 * 抽取掉其中一个 求抽取的那个排序
 */
public class BinaryTreeNoReculsive {

    public  void text() {

        ArrayList<String> strings = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i=0;i<n;i++) {
            strings.add(scanner.next());
        }
        char[] chars = strings.get(0).toCharArray();
        Arrays.sort(chars);

        int[][] size = new int[chars.length][chars.length];
        for (int i=0;i<size.length;i++) {
            for (int j = 0; j < size.length; j++) size[i][j] = 0;

        }
        for (int i = 0; i < chars.length; i++) {//列号
            String s = "";
            for (int j = 0; j < n; j++) {//每条记录
                s=strings.get(j);
                int theIndexRow = findTheIndexRow(s.charAt(i), chars);
                size[i][theIndexRow] += 1;
            }
        }
        //包含n个元素的记录
        int[] remIndex = new int[chars.length];
        int min = Integer.MAX_VALUE;
        int position = 0;
        for (int i = 0; i < remIndex.length; i++) {
            for (int j = 0; j < remIndex.length; j++) {
                if(min>size[i][j]) {min=size[i][j];position=j;}
            }
            remIndex[i] = position;
            min = Integer.MAX_VALUE;
        }

        for (int i=0;i<remIndex.length;i++) {
            System.out.print(chars[remIndex[i]]+" ");
        }
    }

    private static int  findTheIndexRow(char c,char[] chars) {
        for (int i=0;i<chars.length;i++) if(chars[i]==c) return i;
        return 0;
    }


    static  class  TreeNode{
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     * 三种非递归遍历
     * <p>
     * RootDepthTraverse()
     */

    public static final Stack<TreeNode> stacks = new Stack<>();
    //先序遍历非递归
    public void RootDepthTraverse(TreeNode root) {

        while (root != null) {
            System.out.println(root.val);
            stacks.push(root);
            root = root.left;
        }
        TreeNode vistedNode = null;
        while (!stacks.isEmpty()) {
            TreeNode peek = stacks.peek();
            if (peek.right != null) {
                TreeNode right = peek.right;
                if(right==vistedNode) {vistedNode=stacks.pop();continue;}
                while (right != null) {
                    System.out.println(right.val);
                    stacks.push(right);
                    right = right.left;
                }
            } else {
                vistedNode = stacks.pop();
            }
        }
    }


    //非递归中序遍历
    public void ImmedeateDepathTraverse(TreeNode root) {
        while (root != null) {
            stacks.push(root);
            root = root.left;
        }
        while (!stacks.isEmpty()) {
            TreeNode pop = stacks.pop();
            System.out.println(pop.val);
            if (pop.right != null) {
                TreeNode right = pop.right;
                while (right != null) {
                    stacks.push(right);
                    right = right.left;
                }
            }
        }
    }

    //后续非递归遍历
    public void lastDepathTraverse(TreeNode root) {

        while (root != null) {
            stacks.push(root);
            root = root.left;
        }
        TreeNode vistedNode = null;
        while (!stacks.isEmpty()) {
            TreeNode peek = stacks.peek();
            if (peek.right != null) {
                TreeNode right = peek.right;
                if(right==vistedNode){vistedNode=stacks.pop();System.out.println(vistedNode.val);continue;}
                while (right != null) {
                    stacks.push(right);
                    right = right.left;
                }
            }else{
                vistedNode = stacks.pop();
                System.out.println(vistedNode.val);
            }
        }
    }


    public static void main(String... args) {
        TreeNode root = new TreeNode(10);

        TreeNode rootleft = new TreeNode(6);

        TreeNode rootright = new TreeNode(14);

        root.left = rootleft;
        root.right = rootright;

        rootleft.left = new TreeNode(4);
        rootleft.right = new TreeNode(8);

        rootright.left = new TreeNode(12);
        rootright.right = new TreeNode(16);
        root.right.right.right = new TreeNode(18);

        new BinaryTreeNoReculsive().lastDepathTraverse(root);

    }

}
