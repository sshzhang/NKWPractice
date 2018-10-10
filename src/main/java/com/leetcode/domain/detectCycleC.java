package com.leetcode.domain;

public class detectCycleC {


    /**
     * 首先通过快慢指针  求出相遇点设为Z　　　a 点为入口  b 为入口岛Z的距离  c 为　Z 岛b 的顺时针距离
     * ２（a+b）=a+b+n*(b+c)
     * a=(n-1)(b+c)+c
     *b+c为环的大小  从而  使用两个指针 一个从初始位置出发  另一个从相遇点出发　　速度相同　　最终必定到达环的入口
     * @param head
     * @return
     */

    public ListNode detectCycle(ListNode head) {

        ListNode slowNode = head;
        ListNode quicNode = head;
        if(quicNode==null||quicNode.next==null||quicNode.next.next==null) return null;


        while (slowNode.next != null && quicNode.next != null && quicNode.next.next != null) {
            slowNode = slowNode.next;
            quicNode = quicNode.next.next;
            if(slowNode==quicNode) break;
        }


        if(slowNode!=quicNode) return null;

        slowNode = head;

        while (slowNode != quicNode) {
            slowNode = slowNode.next;
            quicNode = quicNode.next;
        }

        return slowNode;
    }

    public boolean hasCycle(ListNode head) {

        ListNode currNode = head;
        ListNode quicNode = head;

        if(head==null|| head.next==null||head.next.next==null) return false;

        while (currNode != null && quicNode.next != null) {
            currNode = currNode.next;
            ListNode next = quicNode.next;
            if(next==null||next.next==null) return false;
            quicNode = next.next;

            if(quicNode==currNode) return true;
        }


        return false;
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
