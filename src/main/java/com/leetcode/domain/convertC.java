package com.leetcode.domain;

/**
 * leetcode 之字型转换
 *
 */
public class convertC {

    public String convert(String s, int numRows) {

        char[] chars = s.toCharArray();
        StringBuffer sbs[] = new StringBuffer[numRows];
        for (int i = 0; i < sbs.length; i++) {
            sbs[i] = new StringBuffer();
        }
        int i = 0;
        int len = chars.length;
        while (i < len) {
            for (int idx = 0; idx < numRows && i < len; idx++) {
                sbs[idx].append(chars[i++] + "");
            }
            for (int idx = numRows - 2; idx > 0 && i < len; idx--) {
                sbs[idx].append(chars[i++] + "");
            }
        }
        for (int j = 1; j < numRows; j++) {
            sbs[0].append(sbs[j].toString());
        }
        return sbs[0].toString();
    }

    public static void main(String... args) {

        String paypalishiring = new convertC().convert("PAYPALISHIRING", 3);
        System.out.println(paypalishiring);

    }

}
