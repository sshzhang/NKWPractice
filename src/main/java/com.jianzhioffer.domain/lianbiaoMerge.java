package com.jianzhioffer.domain;

/**
 * 两个递增链表合并
 *
 * 结果满足单调不减
 *
 */
public class lianbiaoMerge {

    public ListNode Merge(ListNode list1,ListNode list2) {

        //list1前一个元素
        ListNode prelist1 = null,start1=list1;
        ListNode tempt1 = null, tempt2 = null;
        while (list1 != null && list2 != null) {
            int val1 = list1.val;
            int val2 = list2.val;
            tempt1 = list1.next;
            tempt2 = list2.next;
            if (val1 > val2) {//插入  list2 到 list1
                if (prelist1 == null) {//list1为首节点
                    list2.next = list1;
                    prelist1 = list2;
                    start1 = list2;//新链表的首节点变化
                }else{
                    list2.next = list1;
                    prelist1.next = list2;
                    prelist1 = list2;
                }
                list2 = tempt2;
            }else{
                prelist1 = list1;
                list1 = tempt1;
            }
        }
        while (list2 != null) {
            prelist1.next = list2;
            list2 = list2.next;
        }
        return start1;
    }
static class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}


    public static void main(String... args) {

        ListNode listNode = new ListNode(12);
        listNode.next = new ListNode(15);
        listNode.next.next = new ListNode(17);
        ListNode listNode2 = new ListNode(11);
        listNode2.next = new ListNode(14);
        listNode2.next.next = new ListNode(19);
        ListNode merge = new lianbiaoMerge().Merge(listNode, listNode2);
        System.out.println(merge);
    }

}
