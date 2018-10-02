package com.jianzhioffer.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中第一个不重复出现的字符
 */
public class StringFirstNotRepeatChar {


    StringBuilder bf = new StringBuilder();
    Map<String, Integer> maps = new HashMap<>();

    //Insert one char from stringstream
    public void Insert(char ch) {

        bf.append(ch);
        if (maps.containsKey(ch + "")) {
            maps.put(ch + "", 2);
        } else {
            maps.put(ch + "", 1);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {

        char[] chars = bf.toString().toCharArray();
        for (char sk : chars) {

            if (maps.get(sk+"") == 1) {
                return sk;
            }
        }
        return '#';
    }


    public static void main(String... args) {

    }

}
