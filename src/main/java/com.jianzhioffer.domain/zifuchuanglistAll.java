package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.Comparator;

public class zifuchuanglistAll {

    public static final ArrayList<String> params = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        params.clear();
        if(str==null||str.length()==0) return params;
        char[] chars = str.toCharArray();
        AllRange(chars, 0, chars.length);

        params.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return params;
    }

    private void AllRange(char[] chars, int start, int length) {

        if (start == length - 1) {
            String str = "";
            for (int i = 0; i < chars.length; i++) {
                str += chars[i];
            }
            params.add(str);
        } else {
            for (int i = start; i < length; i++) {
                if (ISSwap(chars, start, i)) {//是否需要交换  如果之前存在相同元素就不需要交换
                    //交换两个元素
                    char tempt = chars[start];
                    chars[start] = chars[i];
                    chars[i] = tempt;

                    AllRange(chars, start + 1, length);

                    tempt = chars[start];
                    chars[start] = chars[i];
                    chars[i] = tempt;
                }
            }
        }
    }

    public static boolean ISSwap(char[] chars, int start, int length) {

        for (;start<length;start++) {//是否需要交换 chars[start] 和chars[length]
            // 之前的元素中存在相等数据  不交换
            if (chars[start] == chars[length]) {
                return false;
            }
        }
        //交换
        return true;
    }

    public static void main(String... args) {
        new zifuchuanglistAll().Permutation("abb");
        System.out.println(params);
    }
}
