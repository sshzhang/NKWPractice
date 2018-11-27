package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class combinationSumC39 {
    List<List<Integer>> params = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        params.clear();
        Arrays.sort(candidates);
        ReclusiveTheDatas(new Stack<myNode>(), target, candidates.length - 1, candidates);
        return params;
    }

    public static void main(String... args) {

        List<List<Integer>> lists = new combinationSumC39().combinationSum2(new int[]{1, 1, 5, 5, 5}, 1);
        System.out.println(lists);

    }

    /**
     * 首先找到这个元素的位置，或者最接近这个元素的位置
     * 判断元素可能的个数  依次迭代下去
     *
     * @param param
     * @param target
     * @param end
     * @param candidates
     */

    public void ReclusiveTheDatas(Stack<myNode> param, int target, int end, int[] candidates) {

        if (target == 0) {

            List<Integer> in = new ArrayList<Integer>();

            for (int i = 0; i < param.size(); i++) {
                myNode node = param.get(i);
                for (int j = 1; j <= node.numCount; j++) in.add(node.x);
            }
            params.add(in);
            return;
        }
        int low = 0;
        int high = end;

        int dest = -1;

        while (low <= high) {

            int mid = (low + high) / 2;
            if (candidates[mid] == target) {
                dest = mid;
                break;
            }

            if (candidates[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }
        dest = dest == -1 ? high : dest;

        if (dest == -1 || target < 0) return;

        for (int j = target / candidates[dest]; j >= 0; j--) {
            param.push(new myNode(j, candidates[dest]));
            ReclusiveTheDatas(param, target - j * candidates[dest], dest - 1, candidates);
            param.pop();
        }

    }

    class myNode {

        int numCount = 0;
        int x = 0;

        public myNode(int numCount, int x) {
            this.numCount = numCount;
            this.x = x;
        }
    }


    //Combination Sum II 和上面的主要区别是 每个元素只使用依次，并且元素之间可以重复

    /**
     * 转换为上一道题目
     * 统计单个不同数字的出现次数
     * 统计所有出现的数字
     * 那么其实此问题和上面问题的区别是本问题次数是固定的，就是数字出现的次数，　上面问题次数不限
     * 带入即可
     * @param candidates
     * @param target
     * @return
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        params.clear();
        Arrays.sort(candidates);

        //统计相同数字的个数


        int count = 1;
        for (int i = 1; i < candidates.length; i++) {

            if (candidates[i - 1] != candidates[i]) {
                count++;
            }
        }

        // 增加一个末尾结束标志
        int[] incandidates = Arrays.copyOf(candidates, candidates.length + 1);
        incandidates[incandidates.length - 1] = -1;


        //统计不同数字
        int[] candidatesC = new int[count];
        // 统计每个数字出现的次数
        int[] countN = new int[count];
        if (count != candidates.length) {

            for (int i = 1, j = 0, index = 0; i < incandidates.length; i++) {

                if (incandidates[i - 1] != incandidates[i]) {
                    candidatesC[j] = incandidates[i - 1];
                    countN[j++] += i - index;
                    index = i;
                }
            }

        } else {
            candidatesC = candidates;
            for (int i = 0; i < countN.length; i++) countN[i] = 1;
        }

        ReclusiveTheDatas2(new Stack<>(), target, candidatesC.length - 1, candidatesC, countN);
        return params;

    }


    public void ReclusiveTheDatas2(Stack<myNode> param, int target, int end, int[] candidatesC, int[] countN) {

        if (target == 0) {

            List<Integer> in = new ArrayList<Integer>();

            for (int i = 0; i < param.size(); i++) {
                myNode node = param.get(i);
                for (int j = 1; j <= node.numCount; j++) in.add(node.x);
            }
            params.add(in);
            return;
        }
        int low = 0;
        int high = end;

        int dest = -1;

        while (low <= high) {

            int mid = (low + high) / 2;
            if (candidatesC[mid] == target) {
                dest = mid;
                break;
            }

            if (candidatesC[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }
        dest = dest == -1 ? high : dest;

        if (dest == -1 || target < 0) return;

        for (int j = countN[dest]; j >= 0; j--) {
            param.push(new myNode(j, candidatesC[dest]));
            ReclusiveTheDatas2(param, target - j * candidatesC[dest], dest - 1, candidatesC, countN);
            param.pop();
        }
    }


}
