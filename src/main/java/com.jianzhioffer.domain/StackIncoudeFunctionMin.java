package com.jianzhioffer.domain;

import java.util.Stack;

/**
 * 包含min函数的栈
 */
public class StackIncoudeFunctionMin {


    Stack<Integer> s1 = new Stack<Integer>();
    int min = Integer.MAX_VALUE;
    public void push(int node) {
        s1.push(node);
        min = min < node ? min : node;
    }

    public void pop() {
        int sm = s1.pop();
        if (sm == min) {
            for (int i=0;i<s1.size();i++) {
                int tempt = s1.get(i);
                min = min > tempt ? tempt : min;
            }
        }
    }
    public int top() {
        return  s1.peek();
    }

    public int min() {
        return min;
    }

}
