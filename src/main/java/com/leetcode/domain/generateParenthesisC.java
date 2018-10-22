package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class generateParenthesisC {

    public List<String> generateParenthesis(int n) {

        List<String> strings = new ArrayList<>();
        char[] chars = new char[2 * n];
        ReclusiveVisted(0, strings, chars);
        return strings;
    }

    //列出所有可能的情况
    public void ReclusiveVisted(int position, List<String> strings, char[] chars) {

        if (position == chars.length) {
            //选取其中满足条件的情况
            if (isVilidate(chars))
                strings.add(new String(chars));
        } else {
            chars[position] = '(';
            ReclusiveVisted(position + 1, strings, chars);
            chars[position] = ')';
            ReclusiveVisted(position + 1, strings, chars);
        }
    }

    private boolean isVilidate(char[] chars) {

        int balance = 0;
        for (char ch : chars) {
            if(ch=='(') balance++;
            else balance--;
            if(balance<0) return false;
        }
        return balance == 0;
    }

}
