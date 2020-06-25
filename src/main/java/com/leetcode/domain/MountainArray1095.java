package com.leetcode.domain;

/**
 * 从山脉数组中查找目标值
 * 先找到山脉峰值，然后二分法查找峰值两边
 */
public class MountainArray1095 {

    public int findInMountainArray(int target, MountainArray mountainArr) {

        int low = 0, high = mountainArr.length() - 1;

        while (low < high) {

            int mid = (low + high) >> 1;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) low = mid + 1;
            else high = mid;
        }

        // 左边二分查找
        int left = BinarySearch(mountainArr, target, low, 1);

        return left == -1 ? BinarySearch(mountainArr, target, low, 0) : left;

    }

    public int BinarySearch(MountainArray mountainArray, int target, int dest, int left) {


        int low = (left == 1 ? 0 : dest + 1);
        int high = (left == 1 ? dest : mountainArray.length() - 1);
        while (low <= high) {
            int mid = (low + high) >> 1;
            int currTarget = mountainArray.get(mid);

            if (currTarget == target) return mid;

            if (currTarget > target) {
                if (left == 1) high = mid - 1;
                else low = mid + 1;
            } else {
                if (left == 1) low = mid + 1;
                else high = mid - 1;
            }
        }
        return -1;
    }
}

interface MountainArray {
    int get(int index);
    int length();
}
