package com.jianzhioffer.domain;

/**
 * 循环左移
 */
public class LeftRotateStringC {


    public String LeftRotateString(String str,int n) {

        int len = str.length();
        if(len==0||len==1) return str;
        int move = n % len;
        StringBuilder bf = new StringBuilder();
        for (int i = move; i < len; i++) {
            bf.append(str.charAt(i) + "");
        }
        for (int i = 0; i < move; i++) {
            bf.append(str.charAt(i) + "");
        }
        return bf.toString();
    }

}
