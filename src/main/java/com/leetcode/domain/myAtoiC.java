package com.leetcode.domain;

public class myAtoiC {


    public int myAtoi(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
            }else{
                str = str.substring(i);
                break;
            }
        }
        String[] s1 = str.split(" ");
        if(s1.length==0) return 0;
        String s = s1[0];
        if (s == null || s.length() == 0) return 0;
        int flage = 1;
        int  nx = 0;

        char c1 = s.charAt(0);

        if (c1 == '-') {
            flage = -1;
        } else if (c1 == '+') {
        } else if (c1 <= '9' && c1 >= '0') {
            flage = 0;
            nx = c1 - '0';
        } else {
            return 0;
        }

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= '9' && c >= '0') {
                if(flage!=-1&&((nx>Integer.MAX_VALUE/10)||(nx==Integer.MAX_VALUE/10&&(c-'0')>=Integer.MAX_VALUE%10))) return Integer.MAX_VALUE;
                if(flage==-1&&(nx>(Integer.MIN_VALUE/10*(-1))||(nx==(Integer.MIN_VALUE/10*(-1))&&(c-'0')>=Integer.MIN_VALUE%10*(-1)))) return Integer.MIN_VALUE;
                nx = nx * 10 + c - '0';
            } else {

                return  nx;
            }
        }
        return  nx*flage;
    }

    public static void main(String... args) {
        int i = new myAtoiC().myAtoi("2147483646");
        System.out.println(i);
    }
}
