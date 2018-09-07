package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 链表
 */
public class lianbiao {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> params = new ArrayList<Integer>();
        while (listNode != null) {

            params.add(listNode.val);
            listNode = listNode.next;
        }

        Collections.reverse(params);
        return params;
    }

    public class ListNode {
        int val;
        ListNode next = null;
               ListNode(int val) {
            this.val = val;
        }
    }
}
