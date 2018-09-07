package com.jianzhioffer.domain;

import java.util.LinkedList;

/**
 * 链表中倒数第k
 */
public class lianbiaodaoshuknode {

    public ListNode FindKthToTail(ListNode head,int k) {


        if(k<=0) return null;
        ListNode first = head;
        ListNode second = head;

        while ( second!=null&&k > 1) {
            second = second.next;
            k--;
        }

        if(second==null) return null;
        else
            while (second.next!= null) {
                first = first.next;
                second = second.next;
            }
        return first;
    }

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
