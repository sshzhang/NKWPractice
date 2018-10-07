package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class sortListC {


    public ListNode sortList(ListNode head) {

        if(head==null) return null;
        List<ListNode> nodes = new ArrayList<ListNode>();
        ListNode currNode = head;
        ListNode preNode = null;
        while (currNode != null) {
            preNode = currNode.next;
            currNode.next = null;
            nodes.add(currNode);
            currNode = preNode;
        }

        Object[] objects = nodes.toArray();

        //快速排序
        /*int row = 0, high = objects.length - 1;
        QuickSorted(row, high, objects);*/

        //归并排序
        //GuiBingOrder(0, objects.length - 1, objects, new ListNode[objects.length]);

        //小顶堆排序

        for (int i=objects.length/2-1;i>=0;i--) {
            AdjustSmallDui(i, objects.length - 1, objects);
        }


        for (int i=objects.length-1;i>=0;i--) {
            ListNode temptNode = (ListNode) objects[i];
            objects[i] = objects[0];
            objects[0] = temptNode;
            AdjustSmallDui(0, i-1, objects);
        }


        currNode = (ListNode) objects[objects.length-1];
        System.out.println(currNode.val);
        for (int i = objects.length-2; i >=0; i--) {
            ((ListNode) (objects[i +1])).next = ((ListNode) (objects[i]));
            System.out.println(((ListNode) (objects[i])).val);
        }
        return currNode;
    }


    public void AdjustSmallDui(int i, int high, Object[] objs) {


            ListNode currNode = (ListNode) objs[i];
            for (int j=2*i+1;j<=high;j=2*j+1) {
                int left = ((ListNode) (objs[j])).val;
                if (j + 1 <= high && left > ((ListNode) (objs[j + 1])).val) {
                    left = ((ListNode) (objs[j + 1])).val;
                    j++;
                }

                if(currNode.val>left){
                    objs[i] = objs[j];
                    i=j;
                } else break;
        }

        objs[i] = currNode;
    }





    //归并排序
    public void GuiBingOrder(int row, int high, Object[] objects, Object[] copyobjects) {

        if (row < high) {

            int mid = (row + high) / 2;
            GuiBingOrder(row, mid, objects, copyobjects);
            GuiBingOrder(mid + 1, high, objects, copyobjects);


            int index1 = row;
            int index2 = mid + 1;
            int index = row;
            while (index1 <= mid && index2 <= high) {

                if (((ListNode) (objects[index1])).val< ((ListNode) (objects[index2])).val) {
                    copyobjects[index++] = objects[index1++];
                } else {
                    copyobjects[index++] = objects[index2++];
                }
            }
            while (index1 <= mid) {
                copyobjects[index++] = objects[index1++];
            }

            while (index2 <= high) {
                copyobjects[index++] = objects[index2++];
            }

            //更新objetcs中数据
            for (int i=row;i<=high;i++) {
                objects[i] = copyobjects[i];
            }
        }

    }


    //快排
    private void QuickSorted(int row, int high, Object[] objects) {

        if (row < high) {
            int mid = QuickReclusive(row, high, objects);
            QuickSorted(row, mid - 1, objects);
            QuickSorted(mid + 1, high, objects);
        }
    }

    private int QuickReclusive(int row, int high, Object[] objects) {

        ListNode temptNode = (ListNode) objects[row];
        objects[row] = null;
        int rowPosi = row, highPosi = high;
        while (rowPosi < highPosi) {
            while (rowPosi < highPosi && ((ListNode) objects[highPosi]).val > temptNode.val) {
                highPosi--;
            }
            //交换数据
            objects[rowPosi++] = objects[highPosi];
            objects[highPosi] = null;

            while (rowPosi < highPosi && ((ListNode) objects[rowPosi]).val < temptNode.val) {
                rowPosi++;
            }
            if (rowPosi < highPosi) {
                objects[highPosi--] = objects[rowPosi];
                objects[rowPosi] = null;
            }
        }
        objects[highPosi] = temptNode;
        temptNode = null;
        return highPosi;
    }


    public static void main(String... args) {

        sortListC slist = new sortListC();
        ListNode root = new ListNode(3);
        ListNode listNode = new ListNode(8);
        root.next = listNode;
        ListNode listNode1 = new ListNode(5);
        listNode.next = listNode1;
        slist.sortList(root);

    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
