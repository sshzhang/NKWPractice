package com.jianzhioffer.domain;

import java.util.Scanner;
import java.util.Stack;

/**
 * 北大acm直方图最大面积问题问题
 */
public class pk2559Histogram {


    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n > 0) {
            int[] params = new int[n];
            for (int i=0;i<n;i++)
                params[i]=scanner.nextInt();

            Stack<MyNode> nodes = new Stack<MyNode>();

            int tempt = 0,max=0,std=0;
            for (int i=0;i<n;i++) {
                tempt = 0;
                if(nodes.isEmpty()) nodes.push(new MyNode(params[i], 1));
                else if (nodes.peek().value >= params[i]) {
                    while (!nodes.isEmpty() && nodes.peek().value >= params[i]) {

                        nodes.peek().len += tempt;
                        std = nodes.peek().value * nodes.peek().len;
                        max = max > std ? max : std;
                        tempt = nodes.peek().len;
                        nodes.pop();
                    }
                    nodes.push(new MyNode(params[i], 1 + tempt));
                } else {
                    nodes.push(new MyNode(params[i], 1));
                }
            }

            //最后所有元素出栈
            tempt = 0;
            while (!nodes.isEmpty()) {
                nodes.peek().len += tempt;
                std = nodes.peek().value * nodes.peek().len;
                max = max > std ? max : std;
                tempt = nodes.peek().len;
                nodes.pop();
            }
            System.out.println(max);
            n = scanner.nextInt();
        }
    }

    static  class  MyNode{
        int value;
        int len;
        MyNode(int value, int len) {
            this.value = value;
            this.len = len;
        }
    }

}
