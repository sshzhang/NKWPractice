package com.leetcode.domain;

public class AddTwoNums445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if(l1==null) return  l2;
        if(l2==null) return l1;
        int len1 = 0, len2 = 0;
        ListNode curr1=l1, curr2=l2;
        while (curr1 != null) {
            len1++;
            curr1 = curr1.next;
        }

        while (curr2!=null){
            len2++;
            curr2 = curr2.next;
        }


        // 只能获取 l1长度大于或者等于l2

        ListNode tempt = l1;
        if (len1 < len2) {
            l1=l2;
            l2 = tempt;
        }

        int len = Math.abs(len1 - len2);
        curr1 = l1;
        curr2 = l2;
        int tlen = Math.max(len1, len2);
        int[] array = new int[tlen];
        int index = tlen-1;
        int anotherNum = -1;
        while (len > 0) {
            array[index--] = curr1.val;
            curr1 = curr1.next;
            len--;

        }
        while (curr1 != null) {
            curr1.val = curr1.val + curr2.val;
            array[index--] = curr1.val;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        int extraNum = 0;
        for (int i = 0; i < tlen; i++) {
            int tvalue = extraNum + array[i];
            array[i] = tvalue % 10;
            extraNum = tvalue/ 10;
        }

        if(extraNum==1) anotherNum = 1;


        curr1=l1;
        index = tlen - 1;
        while (curr1 != null) {
            curr1.val = array[index--];
            curr1 = curr1.next;
        }

        if(anotherNum==1){
            ListNode root = new ListNode(1);
            root.next = l1;
            l1 = root;
        }

        return l1;
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

}
