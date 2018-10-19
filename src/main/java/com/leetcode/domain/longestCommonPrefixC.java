package com.leetcode.domain;


/**
 * 最长的公共前缀
 */
public class longestCommonPrefixC {

    public String longestCommonPrefix(String[] strs) {

        if(strs==null||strs.length==0) return "";

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            char[] chars1 = prefix.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < chars.length && j < chars1.length; j++) {

                if (chars[j] == chars1[j]) {
                    builder.append(chars[j]);
                }else{
                    break;
                }
            }
            prefix = builder.toString();
            if(prefix==""||"".equals(prefix)) return "";
        }

        return prefix;

    }

    public static void main(String... args) {

        longestCommonPrefixC prefixC = new longestCommonPrefixC();

        String[] strings = {"flower", "flow", "flight"};
        String s = prefixC.longestCommonPrefix(strings);
        System.out.println(s);
    }
}
