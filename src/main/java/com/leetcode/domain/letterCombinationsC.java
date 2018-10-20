package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Letter Combinations of a Phone Number
 */
public class letterCombinationsC {

    public List<String> letterCombinations(String digits) {

        String[] strs = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


        return Reclusive(strs, Arrays.asList(""), 0,digits);
    }


    public List<String> Reclusive(String[] strs, List<String> data, int index,String digits) {

        if(index>=digits.length()) return data;

        List<String> res= new ArrayList<>();
        int im = digits.charAt(index) - '0' - 2;
        char[] chars = strs[im].toCharArray();
        for (String da : data) {
            for (char ch : chars) {
                res.add(da + "" + ch);
            }
        }
        return Reclusive(strs, res, index + 1, digits);

    }



}
