package com.leetcode.domain;

public class partition86 {


    static  class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {

        ListNode curr = head;
        ListNode newhead = null;
        ListNode left = null;
        ListNode right = null;
        ListNode newright = null;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = null;
            if (curr.val < x) {
                if (left == null) {
                    left = newhead = curr;
                }else{
                    left.next = curr;
                    left = left.next;
                }
            } else {

                if (right == null) {
                    right = newright = curr;
                }else{
                    right.next = curr;
                    right = right.next;
                }

            }
            curr = next;
        }

        if (newhead == null) {
            newhead = newright;
        }else{
            left.next = newright;
        }
        return newhead;
    }

}
