package com.leetcode.domain;

public class StrTranformerToInteger8 {


    public int myAtoi(String str) {

        int position = 1; // 1表示hi为正数, -1表示为负数
        int value = 0; // 表示数据
        boolean hashNum = false; // 是否设置过数据
        if (str == null || "".equals(str)) return 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (!hashNum && ch == ' ') continue;   // ch==' ' 设置过数据，    ch!=''()
            else if (ch == ' ') break;  // ch==' '  设置过数据
            /*else if (hashNum) {
                if (ch <= '9' && ch >= '0') {
                    if (isNotFlowerValue(value, ch, position))
                        return position == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    value = value * 10 + ch - '0';
                } else {
                    break;
                }
            }*/ else { // 没设置过数据
                if (!hashNum&&ch == '+') {
                    position = 1;
                    hashNum = true;
                } else if (!hashNum&&ch == '-') {
                    position = -1;
                    hashNum = true;
                } else if (ch <= '9' && ch >= '0') {
                    if (isNotFlowerValue(value, ch, position))
                        return position == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    value = value * 10 + ch - '0';
                    hashNum = true;
                } else
                    break;
            }
        }
        return position == 1 ? value : -value;


    }

    private boolean isNotFlowerValue(long value, char ch, int position) {

        long tempt = value * 10 + ch - '0';
        if (position == 1) {
            return tempt >= Integer.MAX_VALUE;
        } else
            return tempt * -1 <= Integer.MIN_VALUE;
    }

    public static void main(String... args) {
        System.out.println(new StrTranformerToInteger8().myAtoi("0-1"));

    }
}
