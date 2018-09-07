package com.jianzhioffer.domain;

public class replaceSpace {

    public String replaceSpace(StringBuffer str) {
        String dest = "";
        for (int i=0;i<str.length();i++) {

            if (str.charAt(i) == ' ') {
                dest += "%20";
            } else {
                dest += str.charAt(i);
            }
        }

        return dest;
    }






}
