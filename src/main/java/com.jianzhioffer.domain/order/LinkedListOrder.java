package com.jianzhioffer.domain.order;

//双向链表排序

import java.util.Stack;

public class LinkedListOrder {


    static class Node {

        Node next = null;
        int val;
        Node pre = null;
        //标志是头结点
        boolean head = false;

        void setHead() {
            this.head = true;
        }

        Node(int val) {
            this.val = val;
        }

        Node(Node next, int val, Node pre) {
            this.next = next;
            this.val = val;
            this.pre = pre;
        }
    }


    public static void QuickSorted(int[] datas, int low, int high) {

        if (low < high) {
            int mid = QuickEachSortedIndex(datas, low, high);
            QuickSorted(datas, low, mid - 1);
            QuickSorted(datas, mid + 1, high);
        }

    }

    private static int QuickEachSortedIndex(int[] datas, int low, int high) {

        int dest = datas[low];
        while (low <= high) {
            while (low <= high && datas[high] > dest) {
                high--;
            }

            datas[low] = datas[high];
            low++;
            //low-1 空闲
            while (low <= high && datas[low] <= dest) {
                low++;
            }
            if (low <= high) {
                datas[high] = datas[low];
                high--;
            }

        }

        datas[high] = dest;
        return high;
    }

   /* public static void main(String... args) {
        int[] ints = {8, 5, 4, 34, 23};
        QuickSorted(ints, 0, 4);
        System.out.println(ints);
       *//* Node head = new Node(-1);
        head.setHead();*//*

        Node root = new Node(8);
        Node node1 = new Node(5);
        Node node2 = new Node(41);
        Node node3 = new Node(23);
        Node node4 = new Node(34);
*//*
        head.next = root;
        head.pre = null;*//*
        root.next = node1;
        root.pre = null;
        node1.next = node2;
        node1.pre = root;
        node2.next = node3;
        node2.pre = node1;
        node3.next = node4;
        node3.pre = node2;
        node4.next = null;
        node4.pre = node3;
//        node5.pre = node4;
//        node5.next = null;

        ReclusiveMergeSortLinkList(root, node4);
        System.out.println(root);

    }*/


    //获取中间元素
    public static Node getMiddleNode(Node start, Node end) {

        Node midNode = null;

        while (start.next != null && end.pre != null && start.next != end.pre && start.next != end) {
            start = start.next;
            end = end.pre;
        }
        midNode = start.next == null || start.next == end ? start : start.next;
        return midNode;
    }


    //归并排序  普通数据
    public static void ReclusiveMergeSort(int[] datas, int row, int high) {
        if (row != high) {
            int mid = (row + high) / 2;
            ReclusiveMergeSort(datas, row, mid);
            ReclusiveMergeSort(datas, mid + 1, high);
            MergeSort(datas, row, mid, high);
        }
    }

    private static void MergeSort(int[] datas, int row, int mid, int high) {
        int[] cdatas = new int[high - row + 1];
        for (int i = 0; i < cdatas.length; i++) {
            cdatas[i] = datas[i + row];
        }
        int index1 = row;
        int index2 = mid + 1;
        int index = 0;
        for (; index1 <= mid && index2 <= high; ) {

            if (datas[index1] < datas[index2]) {
                cdatas[index++] = datas[index1++];
            } else {
                cdatas[index++] = datas[index2++];
            }
        }


        while (index1 <= mid) {
            cdatas[index++] = datas[index1++];
        }

        while (index2 <= high) {
            cdatas[index++] = datas[index2++];
        }


        for (int k = row; k <= high; k++) {
            datas[k] = cdatas[k - row];
        }

    }


    //双向链表归并排序
    public static void ReclusiveMergeSortLinkList(Node start, Node end) {

        if (start != end) {
            Node middleNode = getMiddleNode(start, end);
            ReclusiveMergeSortLinkList(start, middleNode);
            ReclusiveMergeSortLinkList(middleNode.next, end);
            MergeSortLinkList(start, middleNode, end);
        }
    }

    private static void MergeSortLinkList(Node start, Node middleNode, Node end) {

//        Node currBeofre = start.pre;

//        Node currAfter = end.next;

        Node currFirst = start;
        Node currSecond = middleNode.next;

        Node curr = start;
        //计算所有相应数据

        int len = 0;

        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int[] tempt = new int[len];

        int index = 0;//数据个数
        while (currFirst != middleNode.next && currSecond != end.next) {

            if (currFirst.val > currSecond.val) {
                tempt[index++] = currSecond.val;
                currSecond = currSecond.next;
            } else {

                tempt[index++] = currFirst.val;
                currFirst = currFirst.next;
            }
        }


        while (currFirst != middleNode.next) {
            tempt[index++] = currFirst.val;
            currFirst = currFirst.next;
        }


        while (currSecond != end.next) {
            tempt[index++] = currSecond.val;
            currSecond = currSecond.next;
        }


        curr = start;
        index = 0;
        while (curr != null && curr != end.next) {
            curr.val = tempt[index++];
            curr = curr.next;
        }
    }


    //双向队列的快速排序算法
    private static boolean isPass(Node start, Node end) {
        if (start == null)
            return false;
        start = start.next;
        while (start != null) {
            if (start == end) {
                return true;
            }
            start = start.next;
        }
        return false;
    }

    public static void QuickSortLinkList(Node start, Node end) {
        if (isPass(start, end)) {
            Node mid = QuickSortReclusiveLinkList(start, end);
            QuickSortLinkList(start, mid.pre);
            QuickSortLinkList(mid.next, end);
        }

    }

    public static Node QuickSortReclusiveLinkList(Node start, Node end) {
        Node currStart = start;
        Node currEnd = end;
        int dest = start.val;

        while (currStart != null && currStart.pre != currEnd) {//start<=end

            while (currEnd != null && currEnd != currStart.pre && currEnd.val > dest) {
                //最高位后撤
                currEnd = currEnd.pre;
            }
            if (currEnd != null)
                currStart.val = currEnd.val;
            currStart = currStart.next;
            //防止数据为空
            while (currStart != null && currStart.pre != currEnd && currStart.val < dest) {
                currStart = currStart.next;
            }
            //防止数据为空
            if (currStart != null && currStart.pre != currEnd) {
                currEnd.val = currStart.val;
                currEnd = currEnd.pre;
            }

        }
        currEnd.val = dest;
        return currEnd;
    }


    //二叉搜素树　转有序双向列表
    static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     * 二叉树的三种遍历方式
     */

    //中序遍历　非递归
    public static void mediumOrder(TreeNode head, Stack<TreeNode> stacks) {
        //先序遍历
        while (head != null) {
            stacks.push(head);
            head = head.left;
        }
        while (!stacks.isEmpty()) {
            TreeNode pop = stacks.pop();
            System.out.println(pop.val);
            TreeNode curr = pop.right;
            while (curr != null) {
                stacks.push(curr);
                curr = curr.left;
            }
        }
    }


    //后序遍历


    public static void postOrder(TreeNode head, Stack<TreeNode> stacks) {

        TreeNode pre = null;
        while (head != null) {
            stacks.push(head);
            head = head.left;
        }
        while (!stacks.isEmpty()) {
            TreeNode right = stacks.peek().right;
            if (right == pre || right == null) {
                pre = stacks.pop();
                System.out.println(pre.val);
            } else {
                while (right != null) {
                    stacks.push(right);
                    right = right.left;
                }
            }
        }

    }


    //先序遍历
    public static void preOrder(TreeNode head, Stack<TreeNode> stacks) {


        while (head != null) {

            System.out.println(head.val);
            stacks.push(head);
            head = head.left;
        }


        while (!stacks.isEmpty()) {

            TreeNode pop = stacks.pop();
            TreeNode left = pop.right;
            while (left != null) {
                System.out.println(left.val);
                stacks.push(left);
                left = left.left;
            }
        }
    }


    public static void main(String... args) {
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        TreeNode node8 = new TreeNode(13);
        TreeNode node9 = new TreeNode(24);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        node7.left = node8;
        node7.right = node9;
        //preOrder(node1, new Stack<TreeNode>());
        stackOrder(new int[]{8, 9, 6, 4, 3, 11,12});

    }


    //最小堆排序
    public static void stackOrder(int[] datas) {

        for (int i = datas.length / 2 - 1; i >= 0; i--) {
            AdjustStack(datas, i);
        }
        System.out.println(datas);
    }


    //调整堆操作
    public static void AdjustStack(int[] datas, int curr) {

        int dest = datas[curr];
        for (int i = 2 * curr + 1; i < datas.length; i = i * 2 + 1) {
            if (i + 1 < datas.length) {
                if (datas[i] < datas[i + 1]) {
                    i++;
                }
            }
            if (datas[i] > dest) {
                datas[curr] = datas[i];
                curr = i;
            }
        }
        datas[curr] = dest;
    }
}
