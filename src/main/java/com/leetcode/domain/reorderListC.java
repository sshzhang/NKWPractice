package com.leetcode.domain;


import java.util.Stack;

/**
 * recorder list
 */
public class reorderListC {


    public void reorderList(ListNode head) {

        if(head==null||head.next==null||head.next.next==null) return;


        ListNode currNode = head;

        ListNode quicNode = head;

        int n = 0;
        while (currNode != null) {
            n++;
            currNode = currNode.next;
        }
        currNode = head;

        int k = n;
        while (k != 2 && k != 1) {

            for (int i = 1; i <= k - 1; i++) {
                quicNode = quicNode.next;
            }
            quicNode.next = currNode.next;
            currNode.next = quicNode;
            currNode = currNode.next.next;
            k = k - 2;
            quicNode = currNode;
        }

    }



    public void reorderListU(ListNode head) {


        if(head==null||head.next==null||head.next.next==null) return;

        ListNode currNode = head;

        Stack<ListNode> stack = new Stack<>();
        int n = 0;
        while (currNode != null) {
            n++;
            currNode = currNode.next;
        }
        currNode = head;

        ListNode newNode = null;

        ListNode storeNode = null;
        for (int i = 0; i < n / 2; i++) {
            storeNode = currNode.next;
            currNode.next = null;
            stack.push(currNode);
            currNode = storeNode;

        }


        storeNode = null;

        if (n % 2 != 0) {
            storeNode = currNode.next;
            newNode = currNode;
            newNode.next = null;
            currNode=storeNode;

        }

        while (!stack.isEmpty()) {
            storeNode = currNode.next;
            ListNode pop = stack.pop();
            pop.next = currNode;
            currNode.next = newNode;
            newNode = pop;
            currNode = storeNode;
        }
    }



    //通过把链表拆分为两个链表　　　然后把后一个链表逆序    最后合并两个链表

    public void reorderListUU(ListNode head) {

        if(head==null||head.next==null||head.next.next==null) return;

        ListNode currNode = head;

        ListNode slowNode = head;

        ListNode fastNode = head;

        while (slowNode.next != null && fastNode.next!=null&&fastNode.next.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        ListNode newhead = slowNode.next;
        slowNode.next = null;
        currNode = newhead.next;
        newhead.next = null;

        //把链表逆序

        while (currNode != null) {

            ListNode tmpt = currNode.next;
            currNode.next = newhead;
            newhead = currNode;
            currNode = tmpt;
        }


        //合并
        currNode=head;
        while (newhead != null && currNode!=null) {

            ListNode tmptnew = newhead.next;
            ListNode tmpt = currNode.next;
            newhead.next = null;
            currNode.next = newhead;
            newhead.next = tmpt;
            currNode = tmpt;
            newhead = tmptnew;
        }

    }


    public static void main(String... args) {

        ListNode root = new ListNode(12);

        ListNode left1 = new ListNode(45);

        ListNode left2 = new ListNode(78);



        root.next = left1;
        left1.next = left2;
        new reorderListC().reorderListUU(root);

        System.out.println(root.val);

    }






  static  class  ListNode{

      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }

  }



}
