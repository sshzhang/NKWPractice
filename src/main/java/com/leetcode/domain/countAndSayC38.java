package com.leetcode.domain;

/**
 * 思路就是数某个元素的个数 个数 元素
 */
public class countAndSayC38 {

    public String countAndSay(int n) {

        String str = "1";
        String tempt = "";
        for (int i = 2; i <= n; i++) {

            int len = str.length();

            char index = '.';
            int count = 0;
            for (int j = 0; j < len; j++) {

                if (index == '.') {//初始值
                    index = str.charAt(j);
                    count++;
                    continue;
                }
                if (index == str.charAt(j)) count++;

                if (index != str.charAt(j)){//不相同　直接添加
                    tempt += count + "" + index;
                    count = 1;
                    index = str.charAt(j);
                }
            }
            //添加最后未比较的元素信息
            tempt += count + "" + index;
            str = tempt;
            tempt = "";
        }
        return str;

    }

    public static void main(String... args) {
        System.out.println(new countAndSayC38().countAndSay(5));
    }

}
