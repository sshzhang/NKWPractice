package com.leetcode.domain;

public class addTwoNumbersC {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        ListNode currL1 = l1, tempt = null;

        ListNode currL2 = l2;


     /*   while (currL1 != null) {//反转链表
            tempt = currL1.next;
            if (temptHead1 == null) {
                temptHead1 =currL1;
                temptHead1.next = null;
            } else {
                currL1.next = temptHead1;
                temptHead1 = currL1;
            }
            currL1 = tempt;
        }


        while (currL2 != null) {
            tempt = currL2.next;

            if (temptHead2 == null) {
                temptHead2 =currL2;
                temptHead2.next = null;
            } else {
                currL2.next = temptHead2;
                temptHead2 = currL2;
            }
            currL2 = tempt;
        }*/


        int position = 0;
        while (currL1 != null && currL2 != null) {
            int val = currL1.val;
            int val1 = currL2.val;
            currL1.val = (val + val1+position) % 10 ;
            position = (val + val1) / 10;
            tempt = currL1;
            currL1 = currL1.next;
            currL2= currL2.next;
        }

        if (currL1 == null) {

            if (currL2 == null) {
                if (position == 1)
                    tempt.next = new ListNode(1);
            } else {
                if(position==0)
                    tempt.next = currL2;
                else{

                    while(currL2!=null){
                        int val=currL2.val;
                        currL2.val=(val+position)%10;
                        tempt.next=currL2;
                        tempt=currL2;
                        position=(val+position)/10;
                        if(position==0){
                            break;
                        }
                        currL2 = currL2.next;

                    }

                    if (position == 1) {
                        tempt.next = new ListNode(1);
                    }

                }

            }

        } else {

            if (position == 1){
                while(currL1!=null){
                    int val=currL1.val;
                    currL1.val=(val+position)%10;
                    position=(val+position)/10;
                    if(position==0) break;
                    currL1=currL1.next;
                }

                if(position==1) currL1.next = new ListNode(1);
            }
        }
        return l1;
    }


    public static void main(String... args) {

        addTwoNumbersC addTwoNumbersC = new addTwoNumbersC();
        ListNode listNode1 = new ListNode(3);
        ListNode listNode3 = new ListNode(7);
        listNode1.next = listNode3;

        ListNode listNode4 = new ListNode(9);
        ListNode listNode2= new ListNode(2);
        listNode4.next = listNode2;

        ListNode listNode = addTwoNumbersC.addTwoNumbers(listNode4, listNode1);
        System.out.println(listNode);


    }

    static  class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
