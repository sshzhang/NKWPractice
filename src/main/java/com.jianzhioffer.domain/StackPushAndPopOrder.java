package com.jianzhioffer.domain;

import java.util.Stack;

/**
 * 栈的压入 弹出序列
 * 模拟出栈过程
 *     先找到第一个出栈元素对应位置时，有多少个元素已经入栈
 *
 *     在把当前元素出栈
 *                 接着 拿下一个出栈元素和栈中元素进行比较
 *                               如果当前元素存在栈中 直接pop出栈 并且比较出栈元素和popA中元素是否相等
 *                                              不相等  return false 出栈序列不可能
 *
 * 最后记得 判断nums栈是否空  排除 pushA中找不到 popA中元素 导致 pushA 一直push元素
 *例如
 *[1]   [2]
 *
 * [1,2,3]  [2,1]
 */
public class StackPushAndPopOrder {

    private Stack<Integer> nums = new Stack<Integer>();
    public boolean IsPopOrder(int [] pushA,int [] popA) {

        if(pushA==null||popA==null) return false;

        if (pushA.length != popA.length) {
            return false;
        }else {
            boolean flages = true;
            int position = 0, popPosition = 0;
                for (position=0;position<pushA.length;position++) {
                    nums.push(pushA[position]);
                    if (pushA[position] == popA[popPosition]) {
                        popPosition++;
                        nums.pop();
                        while (!nums.isEmpty()&&nums.contains(popA[popPosition])) {//栈中包含下一个元素
                            if(popA[popPosition++]!=nums.pop()) {flages=false;break;}
                        }
                        if(!flages) return false;
                        if(popPosition>=pushA.length-1) break;
                    }
                }
                //最后还有元素  表明有数据还没有弹出
                if(!nums.isEmpty()) flages = false;
            return flages;
        }
    }

}
