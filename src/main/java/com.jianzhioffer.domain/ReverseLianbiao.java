package com.jianzhioffer.domain;

/**
 * 反转链表
 *
 */
public class ReverseLianbiao {

    public ListNode ReverseList(ListNode head) {


        ListNode copyhead = null;
        ListNode tempt = null;
        while (head != null) {
            //提前保存一下一个状态
            tempt = head.next;
            if (copyhead == null) {
                copyhead = head;
                copyhead.next = null;
            }else{
                head.next = copyhead;
                copyhead = head;
            }
            //更新状态节点
            head = tempt;
        }
        return copyhead;
    }
    static class ListNode implements  Comparable<Integer>{
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        public int compareTo(Integer o) {
            return 0;
        }
    }




    public static void main(String... args) {

        ListNode listNode = new ListNode(12);
        listNode.next = new ListNode(13);
        listNode.next.next = new ListNode(14);
        ListNode listNode1 = new ReverseLianbiao().ReverseList(listNode);
        System.out.println(listNode1.val);
    }
}
