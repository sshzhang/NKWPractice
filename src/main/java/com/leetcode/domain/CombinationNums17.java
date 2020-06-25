package com.leetcode.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CombinationNums17 {

    /**
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        // 记录相应的数字
        HashMap<Integer, List<String>> maps = new HashMap<>();
        char dest = 'a' - 1;
        for (int i = 2; i <=9; i++) {
            int len = (i == 8 || i == 9) ? 4 : 3;
            List<String> tempt = new ArrayList<>();
            for (int j = 1; j <= len; j++) {
                dest = (char) (dest + 1);
                tempt.add(dest + "");
            }
            maps.put(i, tempt);
        }


        List<List<String>> values = new ArrayList<>();

        int len = digits.length();
        for (int i = 0; i < len; i++) {
            int inValue = digits.charAt(i) - '0';
            if(inValue!=1) values.add(maps.get(inValue));
        }
        List<String> result = new ArrayList<>();
        if (values.size() != 0) {

            for (String str : values.get(0)) {
                dfsSearch(values, new StringBuilder().append(str),result , 1);
            }
        }
        return result;
    }

    /**
     *
     * @param values
     * @param result
     * @param x 表示是第几个List集合
     */
    public void dfsSearch(List<List<String>> values, StringBuilder tempt,List<String>result, int x) {
        if(x==values.size()) result.add(tempt.toString());
        else
            for (int rlen = values.get(x).size(), j = 0; j < rlen; j++) {
            tempt.append(values.get(x).get(j));
            dfsSearch(values, tempt, result, x + 1);
            tempt.deleteCharAt(tempt.length() - 1);
        }
    }


    public static void main(String... args) {

        System.out.println(new CombinationNums17().letterCombinations("23"));

    }
}
