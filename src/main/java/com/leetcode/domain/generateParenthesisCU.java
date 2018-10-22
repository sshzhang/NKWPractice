package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class generateParenthesisCU {


    public List<String> generateParenthesis(int n) {

        List<String> strings = new ArrayList<>();

        Reclusive(strings,"",0, 0,n);
        return strings;
    }

    private void Reclusive(List<String> strings,String str,int close, int open, int n) {

        if (str.length() == 2 * n) {
            strings.add(str);
            return;
        }
        if (open < n)
            Reclusive(strings, str + "(", close, open + 1, n);

        if (close < open)
            Reclusive(strings, str + ")", close + 1,open, n);


    }

}
