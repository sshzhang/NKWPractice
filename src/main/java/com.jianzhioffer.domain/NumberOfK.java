package com.jianzhioffer.domain;

public class NumberOfK {

    public int GetNumberOfK(int[] array, int k) {

        if (array==null||array.length==0) return 0;
        int min = BinarySearchTree(array, k);
       if(min==-1) return 0;
         //左右试探
        int count = 1;
        for (int i=min+1;i<array.length;i++){
            if(array[i]==k) count++;
            else break;
        }

        for (int i = min - 1; i >= 0; i--) {
            if(array[i]==k) count++;
            else  break;
        }

        return count;
    }

    private int BinarySearchTree(int[] array, int k) {
        int max = array.length - 1;
        int min = 0;
        while (min < max) {
            int mid = (max + min) >> 1;
            if(array[mid]>k) max = mid - 1;
            else if(array[mid]<k) min = mid + 1;
            else return mid;
        }
        return array[min] == k ? min : -1;
    }

    public static void main(String... args) {
        System.out.println(new NumberOfK().GetNumberOfK(new int[]{2, 5, 6, 7, 9, 9}, 9));
    }
}
