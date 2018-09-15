package com.jianzhioffer.domain;


/**
 * 找出两个链表的第一个公共节点
 */

public class FindLinkListFirstCommonNode {


    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        ListNode node1 = pHead1, node2 = pHead2;

        while (node1 != null) {

            while (node2 != null) {

                if (node1 == node2) {
                    return node1;
                }
                node2 = node2.next;
            }
            node2 = pHead2;
            node1 = node1.next;
        }

        return null;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}
