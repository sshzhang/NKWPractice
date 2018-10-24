package com.leetcode.domain;

import com.leetcode.domain.addTwoNumbersC.ListNode;
/**
 * 成对交换相邻的节点
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3
 */
public class swapPairsC {


    public ListNode swapPairs(ListNode head) {

        if(head==null||head.next==null) return head;
        ListNode currhead = head;
        //表示新链表的头节点
        ListNode newHead = null;
        //每一对的后一个节点 例如1->2 中的2->1   1
        ListNode newNode = null;
        while (currhead != null && currhead.next != null) {
            //保存下一个循环
            ListNode tempt = currhead.next.next;
            currhead.next.next = currhead;
            if(newNode==null) newHead = currhead.next;
            if(newNode!=null) newNode.next = currhead.next;
            currhead.next = null;
            newNode = currhead;
            currhead = tempt;
        }

        //链接最后一个节点 可能为空
        newNode.next = currhead;

        return newHead;
    }

}
