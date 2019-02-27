package com.leetcode.domain;

public class searchMatrix74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;

        int low = 0, high = m * n-1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (matrix[mid / n][mid % n] == target) {
                return true;
            } else if (matrix[mid / n]  [mid % n] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String... args) {
        int[][] ints = new int[1][1];
        ints[0][0] = 1;
        System.out.println( new searchMatrix74().searchMatrix(ints, 2));
    }

}
