package com.leetcode.domain;


import java.util.ArrayList;
import java.util.List;

public class numTreesC {


    /**
     *  dp[0]=1   dp[1]=1  dp[2]=2  dp[3]=5  dp[4]=14
     *
     * n=5 时
     *  {1,2,3,4,5}  当1为root时    左节点为null,右节点为{2,3,4,5}  和dp[4] 的个数相同  所以  1*dp[4]  dp[0]*dp[4]
     *               当 2 为root 时　　　做节点{1}  1中情况   右节点{3,4,5} 和dp[3]{1,2,3} 个数相同  所以 1*dp[3]  dp[1]*dp[3]
     *
     *               ....
     *
     * @param n
     * @return
     */
    public  int numTrees(int n) {

        int dp[] = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int level = 2; level <= n; level++) {

            for (int root = 1; root <= level; root++) {

                dp[level] += dp[level - root] * dp[root - 1];
            }

        }
        return dp[n];

    }
    public List<TreeNode> generateTrees(int n) {

        List<TreeNode> res = new ArrayList<>();
        if(n<1) return res;
        return helper(1, n);
    }

    private List<TreeNode> helper(int left, int right) {

        List<TreeNode> res = new ArrayList<TreeNode>();

        if (left > right) {
            res.add(null);
            return res;
        }

        for (int i = left; i <= right; i++) {
            List<TreeNode> leftRes = helper(left, i - 1);
            List<TreeNode> rightRes = helper(i + 1, right);
            for (int m = 0; m < leftRes.size(); m++) {
                for (int n = 0; n < rightRes.size(); n++) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = leftRes.get(m);
                    treeNode.right = rightRes.get(n);
                    res.add(treeNode);
                }
            }
        }

        return res;
    }

    public static void main(String... args) {

        numTreesC treesC = new numTreesC();
        List<TreeNode> treeNodes = treesC.generateTrees(1);
        System.out.println(treeNodes);

    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
