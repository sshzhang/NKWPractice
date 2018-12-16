package com.leetcode.domain;

public class addBinary67 {

    public String addBinary(String a, String b) {

        int lena = a.length();
        int lenb = b.length();

        String dest = "";

        if(lena==0||lenb==0) return dest;
        int i = lena-1, j = lenb-1;


        int position = 0;
        while (i >= 0 || j >= 0) {

            int ca = i >= 0 ? a.charAt(i) - '0' : 0;
            int cb = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = ca + cb + position;
            position = sum/ 2;
            dest = sum % 2 + dest;
        }

        if(position==1) dest = 1 + dest;
        return dest;
    }

    public static void main(String... args) {


    }
}
