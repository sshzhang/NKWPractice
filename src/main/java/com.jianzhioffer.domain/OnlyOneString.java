package com.jianzhioffer.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 只出现一次字符串
 */
public class OnlyOneString {


    public int FirstNotRepeatingChar(String str) {

        Map<String, Integer> params = new LinkedHashMap<>();

        for (int i = 0; i < str.length(); i++) {
            String sm = str.charAt(i) + "";
            if (params.containsKey(sm)) {
                params.put(sm, -1);
            } else {
                params.put(sm, i);
            }
        }

        for (String stm : params.keySet()) {

            if(params.get(stm)!=-1){
                return params.get(stm);
            }
        }

        return -1;
    }


    public static void main(String... args) {

        System.out.println(new OnlyOneString().FirstNotRepeatingChar("google"));
    }
}

