package com.leetcode.domain;

/**
 * 移除从end节点开始的第n个节点
 */
public class removeNthFromEndC {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode currhead = head;
        ListNode after = head;
        int count = 0;
        while (count < n && after != null) {
            after = after.next;
            count++;
        }

        if(count<n&&after==null) return head;

        //正好有n个元素
        if(count==n&&after==null){
            return head.next;
        }
        //多余n个元素
        while (after.next != null) {
            currhead = currhead.next;
            after = after.next;
        }
        currhead.next = currhead.next.next;
        return head;
    }

    static  class  ListNode {
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }


}
