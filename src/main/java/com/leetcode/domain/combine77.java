package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class combine77 {


    public static List<List<Integer>> params = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        params.clear();
        if(n<k||k<=0||n<=0) return params;
        for (int cu=1;cu<=n-k+1;cu++)
        ReclusiveVisiteTheCombine(new Stack<Integer>(), cu, n, k);
        return params;
    }

    private void ReclusiveVisiteTheCombine(Stack<Integer> stack, int current, int sumCount, int askSize) {

        stack.push(current);
        if (stack.size() == askSize) {
            List<Integer> param = new ArrayList<>();
            for (int i = 0; i < askSize; i++) {
                param.add(stack.get(i));
            }
            params.add(param);
            return;
        }

        for (int k = current + 1; k <= sumCount; k++) {
            ReclusiveVisiteTheCombine(stack, k, sumCount, askSize);
            stack.pop();
        }
    }


    public static void main(String... args) {

        List<List<Integer>> combine = new combine77().combine(5, 3);
        System.out.println(combine.size());

    }
}
