package com.leetcode.domain;

import com.leetcode.domain.addTwoNumbersC.ListNode;
/**
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */
public class reverseKGroupC {


    public ListNode reverseKGroup(ListNode head, int k) {

        if(k<=1) return head;

        int count = 0;
        int indexC = 0;

        ListNode currNode = head;

        while (currNode != null) {
            count++;
            currNode = currNode.next;
        }
        if(count<k) return head;

        int times = count / k;

        currNode = head;
        ListNode newNode = null, cnewNode=null,otherHead = null;
        ListNode newHead = null;
        ListNode tempt = null;
        //总共需要的翻转次数
        for (int i = 0; i < times; i++) {
            indexC = 0;
            while (indexC < k) {
                tempt = currNode.next;
                indexC++;
                if (i == 0) {
                    if (newHead == null) {
                        newHead = currNode;
                        newHead.next = null;
                    } else {
                        currNode.next = newHead;
                        newHead = currNode;
                    }

                }else{

                    if(indexC==1) {
                        cnewNode=otherHead = currNode;
                        currNode.next = null;
                    }else{
                        currNode.next = otherHead;
                        otherHead = currNode;
                    }
                }
                currNode = tempt;
            }

             if(newNode!=null) newNode.next = otherHead;

            newNode = i == 0 ? head : cnewNode;

        }

        newNode.next = currNode;











     /*   if(head==null||head.next==null) return head;
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
        newNode.next = currhead;*/

        return newHead;
    }


    public static void main(String... args) {

        reverseKGroupC reverseKGroupC = new reverseKGroupC();
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2= new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);

        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        ListNode listNode5 = reverseKGroupC.reverseKGroup(listNode, 2);
        System.out.println(listNode5);

    }
}
