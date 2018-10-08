package com.leetcode.domain;

//使用插入法对链表进行排序
public class insertionSortListC {


    public ListNode insertionSortList(ListNode head) {

        //指向链表的第二个元素
        ListNode currfirstNode = head.next, nextNode = null;
        //指向链表的第一个元素    pre表示之前的记录
        ListNode newcurrsecondNode = new ListNode(head.val), pre = null;
        ListNode newhead = newcurrsecondNode;
        for (; currfirstNode != null; currfirstNode=nextNode) {
            for (newcurrsecondNode = newhead,pre=null; newcurrsecondNode != null && currfirstNode.val > newcurrsecondNode.val;pre=newcurrsecondNode, newcurrsecondNode = newcurrsecondNode.next);
            nextNode = currfirstNode.next;
            if (newcurrsecondNode == null) {//最后一个节点
                pre.next = currfirstNode;
                currfirstNode.next = null;
            }else{
                //头结点插入
                if (pre == null) {
                    currfirstNode.next = newhead;
                    newhead = currfirstNode;
                }else{//中间节点插入
                    pre.next = currfirstNode;
                    currfirstNode.next = newcurrsecondNode;
                }
            }
        }
        return newhead;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
