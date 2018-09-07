package com.jianzhioffer.domain;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindInTwoDimensionArray {


    public boolean Find(int target, int[][] arrays) {
        /**
         * 1.通过每一行的查找过去。
         */
        try {
            int rowmin , rowmax ;
            int rowmed=0;
            boolean flagerow = false;
            if(arrays==null||arrays.length==0) return false;
            for (int k=0;k<arrays.length;k++) {
                int row = k;
                if(target<arrays[row][0]&&target>arrays[row][arrays.length-1]) continue;
                rowmin = 0; rowmax = arrays[0].length - 1;
                while (rowmin <= rowmax) {
                    rowmed = (rowmax + rowmin) / 2;
                    if (target > arrays[row][rowmed]) {
                        rowmin = rowmed + 1;
                    }
                    if (target < arrays[row][rowmed]) {
                        rowmax = rowmed - 1;
                    }
                    if (target == arrays[row][rowmed]) {
                        flagerow = true;
                        break;
                    }
                }
                if (flagerow) return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
        /**
         * 2.通过 右上角(也可以左下角)的元素与目标元素比较  如果比目标元素小 那么行数+1  如果比目标元素大 列数-1
         *
         *  复杂度O(2n)
         */

      /*  int row = 0, column = arrays[0].length - 1;
        boolean flages = false;
        while (row <= arrays.length - 1 && column >= 0) {
            System.out.println(row + " " + column + " " + arrays[row][column]);
            if (target > arrays[row][column]) {
                row++;
            } else if (target < arrays[row][column]) {
                column--;
            } else {
                flages = true;
                break;
            }
        }
        return flages;*/

    }


    public static  void main(String ...args){

        FindInTwoDimensionArray findInTwoDimensionArray = new FindInTwoDimensionArray();
        Scanner scanner = new Scanner(System.in);
        String lines = scanner.next();
        int target = 0;
        String[] split = lines.split(",");
        target = Integer.parseInt(split[0]);
        int countrow = -1;
        for (int i=0;i<lines.length();i++) {
            char sm = lines.charAt(i);
            if (sm == '[') countrow++;
        }
        int[][] pa = new int[countrow][];
        Pattern compile = Pattern.compile("\\[(([0-9]+,?)+?)\\]");
        Matcher matcher =
                compile.matcher(lines);
        int count = 0;
        while (matcher.find()) {
            String[] split1 = matcher.group(1).split(",");
            pa[count++] = new int[split1.length];
            for (int k = 0; k < split1.length; k++) pa[count - 1][k] = Integer.parseInt(split1[k]);
        }
        System.out.println(findInTwoDimensionArray.Find(target, pa));

    }
}
