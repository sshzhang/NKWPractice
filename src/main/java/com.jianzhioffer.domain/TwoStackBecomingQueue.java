package com.jianzhioffer.domain;

import java.util.Stack;

/**
 * 两个栈实现一个队列
 *
 * 思路很简单  通过 一个栈存储最新的数据  另一个存储pop出来的数据
 */
public class TwoStackBecomingQueue {


    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        //stack1中存放新push过来的数据
        stack1.push(node);
    }

    public int pop() {

        if (stack2.isEmpty()) {//如果stack2中为空

            if (stack1.isEmpty()) {
                throw new RuntimeException("队列为空");
            }else{
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
        }
        return stack2.pop();
    }
}
