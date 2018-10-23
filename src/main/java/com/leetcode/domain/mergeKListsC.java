package com.leetcode.domain;

import com.leetcode.domain.addTwoNumbersC.ListNode;

/**
 * k个有序链表的融合
 */
public class mergeKListsC {

    /**
     * 传统的暴力方法
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;
        ListNode dest = lists[0];

        for (int i = 1; i < lists.length; i++) {

            ListNode currNode = null, head = null;
            ListNode after = lists[i];

            while (after != null && dest != null) {

                if (after.val > dest.val) {
                    if (currNode == null) {
                        head = currNode = dest;
                        dest = dest.next;
                    } else {

                        currNode.next = dest;
                        currNode = dest;
                        dest = dest.next;
                    }

                } else {

                    if (currNode == null) {
                        head = currNode = after;
                        after = after.next;
                    } else {
                        currNode.next = after;
                        currNode = after;
                        after = after.next;
                    }


                }

            }


            if (after != null) {
                if (currNode == null) {
                    currNode = head = after;
                } else
                    currNode.next = after;

            }

            if (dest != null) {
                if (currNode == null) {
                    currNode = head = dest;
                } else
                    currNode.next = dest;
            }
            dest = head;
        }


        return dest;
    }


    /**
     * 通过递归解决问题
     *
     * @param lists
     * @return
     */
    public ListNode mergeKListsU(ListNode[] lists) {
        return mergeReclusive(lists, 0, lists.length - 1);
    }


    public ListNode mergeReclusive(ListNode[] lists, int row, int high) {

        if (row == high) return lists[row];
        else if (row < high) {
            int mid = (row + high) / 2;
            ListNode left = mergeReclusive(lists, row, mid);
            ListNode right = mergeReclusive(lists, mid + 1, high);

            return merge(left, right);
        } else
            return null;
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }

    }

    public static void main(String... args) {

        int[] ints = OrderArrays(new int[]{6, 4, 8, 3, 9, 2});
        System.out.println(ints);
    }


    public static int[] OrderArrays(int[] params) {

        int[] copyParams = new int[params.length];

       return  ReclusiveArrays(params, copyParams, 0, params.length - 1);

//        return params;
    }

    public static int[] ReclusiveArrays(int[] params, int[] copyParams, int row, int high) {

        if(row==high) return new int[]{params[row]};

        else if (row < high) {

            int mid = (row + high) / 2;
            int[] left = ReclusiveArrays(params, copyParams, row, mid);
            int[] right = ReclusiveArrays(params, copyParams, mid + 1, high);

            return Merge(left, right);
        }else{
            return null;
        }
        /*if (row >= high) return;
        int mid = (row + high) / 2;
        ReclusiveArrays(params, copyParams,row, mid);
        ReclusiveArrays(params, copyParams,mid + 1, high);
        int left = row, right = mid + 1;
        int index = row;
        while (left <= mid && right <= high) {

            if (params[left] < params[right]) {
                copyParams[index++] = params[left++];
            }else{
                copyParams[index++] = params[right++];
            }
        }

        while (left <= mid) {
            copyParams[index++] = params[left++];
        }

        while (right <= high) {
            copyParams[index++] = params[right++];
        }


        for (int i = row; i <= high; i++) {
            params[i] = copyParams[i];
        }*/
    }

    private static int[] Merge(int[] left, int[] right) {

        if(left==null) return right;
        if(right==null) return left;

        int len1 = left.length;
        int len2 = right.length;

        int[] sm = new int[len1 + len2];
        int index = 0, leftInde = 0, rightIndex = 0;

        while (leftInde < len1 && rightIndex < len2) {

            if (left[leftInde] < right[rightIndex]) {
                sm[index++] = left[leftInde++];

            }else{
                sm[index++] = right[rightIndex++];
            }
        }

        while (leftInde < len1) {
            sm[index++] = left[leftInde++];
        }

        while (rightIndex < len2) {
            sm[index++] = right[rightIndex++];
        }
        return sm;
    }


}
