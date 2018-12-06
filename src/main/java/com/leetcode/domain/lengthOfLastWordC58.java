package com.leetcode.domain;

public class lengthOfLastWordC58 {

    public int lengthOfLastWord(String s) {

        int n = s.length();

        int count = 0;
        int flage = -1;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (flage == -1 && c == ' ') {
                continue;
            }
            flage = 1;
            if (c != ' ') {
                count++;

            } else {
                break;

            }
        }

        return count;

    }

}
