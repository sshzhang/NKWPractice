package com.leetcode.domain;

public class mergeTwoListsC {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null,currhead=null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (head == null) {
                    currhead=head = l1;
                    l1 = l1.next;
                    head.next = null;
                }else{
                    head.next = l1;
                    head = l1;
                    l1 = l1.next;
                    head.next = null;
                }
            }else {
                if (head == null) {
                    currhead=head = l2;
                    l2 = l2.next;
                    head.next = null;
                }else{
                    head.next = l2;
                    head = l2;
                    l2 = l2.next;
                    head.next = null;
                }

            }
        }

        if (l1 != null) {
            if(head==null) currhead=head=l1;
            else
                head.next = l1;
        }

        if (l2 != null) {
            if(head==null) currhead=head=l2;
            else
                head.next = l2;
        }
        return currhead;
    }


    public static void main(String... args) {

        mergeTwoListsC merge = new mergeTwoListsC();


        ListNode root2 = new ListNode(1);

        merge.mergeTwoLists(null, root2);

    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}
