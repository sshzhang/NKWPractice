package com.leetcode.domain;

import java.util.Stack;

/**
 * 逆波兰
 */
public class evalRPNC {


    public int evalRPN(String[] tokens) {
        Stack<String> sm = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String tempt = tokens[i];
            if ("+".equals(tempt)) {
                String num1 = sm.pop();
                String num2 = sm.pop();
                sm.push((Integer.parseInt(num1) + Integer.parseInt(num2))+"");
            } else if ("-".equals(tempt)) {
                String num1 = sm.pop();
                String num2 = sm.pop();
                sm.push((Integer.parseInt(num2) - Integer.parseInt(num1))+"");
            } else if ("*".equals(tempt)) {
                String num1 = sm.pop();
                String num2 = sm.pop();
                sm.push((Integer.parseInt(num1) *Integer.parseInt(num2))+"");
            } else if ("/".equals(tempt)) {
                String num1 = sm.pop();
                String num2 = sm.pop();
                sm.push((Integer.parseInt(num2) / Integer.parseInt(num1))+"");

            }else{
                sm.push(tempt);
            }
        }

        String pop = sm.pop();
        return Integer.parseInt(pop);
    }
}
