package com.leetcode.domain;

public class deleteDuplicates82 {
    //删除重复元素
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode currNode = head;
        ListNode nextNode = head.next;
        //新链表的头节点
        ListNode newHead = null, rHead = null;
        while (nextNode != null) {//不为空
            if (nextNode.val == currNode.val) {
                int val = nextNode.val;
                nextNode = nextNode.next;
                //找到不相等的元素位置
                while (nextNode != null && nextNode.val == val) {
                    nextNode = nextNode.next;
                }
                if (nextNode == null) {//下一个节点为null
                    return newHead;
                } else if (nextNode.val != val) { //下一个节点的值不相等
                    currNode = nextNode;
                    if(nextNode.next==null){
                     if(newHead==null)  //处理最后一个和其它不同的问题 1 2 2 2 3
                         newHead = rHead = currNode;
                     else rHead.next = currNode;
                    }
                    nextNode = nextNode.next;
                }
            }else{//不相等
//                ListNode stayNext = currNode.next;
                if (newHead == null) {//初始化头节点
                    rHead = newHead = currNode;
                    rHead.next = null;
                }else{
                    rHead.next = currNode;
                    rHead = rHead.next;
                    rHead.next = null;
                } //条件补充  如果出现 1  1 2 3, 导致无法判断最后一位
                if(nextNode.next==null&&currNode.val!=nextNode.val) rHead.next = nextNode;
                currNode = nextNode;
                nextNode = nextNode.next;
            }
        }
        return newHead;
    }


    public static void main(String... args) {

        deleteDuplicates82 de = new deleteDuplicates82();
        ListNode listnode = new ListNode(1);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(3);
        listnode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = null;
        ListNode head = de.deleteDuplicates(listnode);
        System.out.println(head);

    }

    static  class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicatesl(ListNode head) {

        if(head==null||head.next==null) return head;
        ListNode currNode = head;
        ListNode nextNode = head.next;

        while (nextNode != null) {
            if (currNode.val == nextNode.val) {
                if(nextNode.next==null) currNode.next = null;
                nextNode = nextNode.next;
            }else{
                currNode.next = nextNode;
                currNode = currNode.next;
                nextNode = nextNode.next;
            }
        }

        return head;
    }
}