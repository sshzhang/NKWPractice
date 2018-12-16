package com.leetcode.domain;

public class rotateRightC61 {


    public ListNode rotateRight(ListNode head, int k) {


        if(head==null) return head;
        ListNode currHead = head;

        int count = 0;

        while (currHead != null) {
            count++;
            currHead = currHead.next;
        }

        k = k % count;
        if(k==0) return head;
        count = count - k;
        int i = 1;

        ListNode indexNode = head;

        while (i < count) {
            indexNode = indexNode.next;
            i++;
        }

        ListNode cuhead = indexNode.next;
        indexNode.next = null;

        currHead = cuhead;

        while (cuhead.next != null) {
            cuhead = cuhead.next;
        }
        cuhead.next = head;


        return currHead;
    }





       class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
      }
}
