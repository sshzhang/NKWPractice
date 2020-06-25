package com.leetcode.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class IsAnotherTree572 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {

        // 利用层次遍历来判断是否是子树
        if (t == null || s == null) return false;
        isReclusiveVisistedTree(s, t);
        return flage;
    }

    boolean flage = false;

    public void isReclusiveVisistedTree(TreeNode s, TreeNode t) {
        if (s.val == t.val) {
            if (isTheSameTree(s, t)) flage = true;
        }
        if (!flage && s.left != null)
            isReclusiveVisistedTree(s.left, t);
        if (!flage && s.right != null)
            isReclusiveVisistedTree(s.right, t);
    }

    private boolean isTheSameTree(TreeNode s, TreeNode t) {
        // 遍历s和t看是否时其子树
        Stack<TreeNode> Stacks = new Stack<>();
        Stack<TreeNode> Stackt = new Stack<>();
        Stacks.push(s);
        Stackt.push(t);
        while (!Stacks.isEmpty() || !Stackt.isEmpty()) {
            if (Stacks.isEmpty() || Stackt.isEmpty()) return false;
            TreeNode currs = Stacks.pop();
            TreeNode currt = Stackt.pop();
            // 两个元素都为空, 或者两元素都不为空此时两者相等
            if (currs.left != null && currt.left != null && currt.left.val == currs.left.val) {
                Stacks.push(currs.left);
                Stackt.push(currt.left);
            } else if (!(currt.left == null && currs.left == null))
                return false;
            // 两个元素都为空, 或者两元素都不为空此时两者相等
            if (currs.right != null && currt.right != null && currt.right.val == currs.right.val) {
                Stacks.push(currs.right);
                Stackt.push(currt.right);
            } else if (!(currt.right == null && currs.right == null))
                return false;
        }
        return Stacks.isEmpty() && Stackt.isEmpty();
    }

    /*public static void main(String... args) {


        String str = "1lnull1lnull1lnull1lnull1lnull12lnullrnullrnull";

        String std = "1lnull1lnull1lnull1lnull1lnull1lnull1lnull1lnull1lnull1lnull12lnullrnullrnull";

        System.out.println(std.contains(str));
        IsAnotherTree572 isAnotherTree572 = new IsAnotherTree572();

        TreeNode s = new TreeNode(3);
        //TreeNode t = new TreeNode(4);
        s.right = new TreeNode(5);
        TreeNode sl = new TreeNode(4);
        TreeNode sll=new TreeNode(1);
        s.left = sl;
        sl.left = sll;
        sl.right = new TreeNode(2);


        System.out.println(isAnotherTree572.isSubtreeU(s, sl));

    }*/


    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode s = stringToTreeNode(line);
            line = in.readLine();
            TreeNode t = stringToTreeNode(line);

            boolean ret = new IsAnotherTree572().isSubtreeU(s, t);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    /**
     * 第二种方法 如果一个树是另一个树的子树，那么它们的中序遍历连续在一起，  相反利用lnull和rnull节点，如果中序遍历连续，那么我们可以知道其中一个树是另一个树的子树
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtreeU(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        List<String> resultsStr = getThePreVisitedResults(s);
        List<String> resulttStr = getThePreVisitedResults(t);
        // 利用k-means查找匹配字符串
        String[] tArrays = resulttStr.toArray(new String[resulttStr.size()]);
        String[] sArrays=resultsStr.toArray(new String[resultsStr.size()]);
        for (String str : tArrays) {
            System.out.print(str);
        }
        System.out.println();

        for (String str : sArrays) {
            System.out.print(str);
        }

        int[] next = new int[tArrays.length + 1];
        // 构建next数组, 第一个元素为-1标志位,
        next[0] = -1;
        for (int i = 0, j = -1; i < tArrays.length;) {
            if (j == -1 || tArrays[j].equals(tArrays[i])) {// 当前元素没有可用的前缀或者j位置和i位置元素相等，那么直接在原有的基础上加1
                ++i;
                ++j;
                next[i] = j;
            } else {// 不相等，接着寻找下一个
                j = next[j];
            }
        }
        //开始查找元素KMP 主要是利用next数组
        int index = 0, k = 0;
        while (index < sArrays.length && k < tArrays.length) {
            if(k==-1||sArrays[index].equals(tArrays[k])){
                index++; // 当k==-1表示第一个元素就不符合条件，那么两者都加1
                k++;
            }else{
                k=next[k];
            }
        }
        return k >=tArrays.length;
    }

    /**
     * 获取带空节点的树的先序遍历
     *
     * @param s 表示对应的树
     * @return
     */
    private List<String> getThePreVisitedResults(TreeNode s) {

        Stack<TreeNode> stack = new Stack<>();
        List<String> results = new ArrayList<>();
        TreeNode curr = s;
        while (curr != null) {
            stack.push(curr);
            results.add(curr.val + "");
            curr = curr.left;
        }

        while (!stack.isEmpty()) {

            TreeNode pop = stack.pop();

            TreeNode right = pop.right;

            if (pop.left == null) results.add("lnull");
            if (right == null) results.add("rnull");

            while (right != null) {
                stack.push(right);
                results.add(right.val + "");
                right = right.left;
            }
        }
        return results;
    }
}
