package com.jianzhioffer.domain;

/**
 * 二进制1的个数
 */
public class BinaryJingzhi {



    public int NumberOf1(int n) {
        int[] flage = new int[32];
        for (int i = 0; i < flage.length; i++) flage[0] = 1;
        int k = n >= 0 ? n : -n;
        int count = 0, index = flage.length - 1;
        while (k != 0) {
            flage[index--] = k % 2;
            if (flage[index + 1] == 1) count++;
            k = k / 2;
        }
        if (n < 0) {
            for (int i = 0; i < flage.length; i++) {
                flage[i] = flage[i] == 0 ? 1 : 0;
            }
            StringBuilder stringBuilder = new StringBuilder();
            int tempt = 0;
            count = 0;
            for (int i = flage.length - 1, addition = 1; i >= 1; i--) {
                tempt = (flage[i] + addition) % 2;
                if (tempt == 1) count++;
                stringBuilder.append(tempt);
                addition = (flage[i] + addition) / 2;
            }
            count++;
        }
        return count;
    }

    public static void main(String... args) {

        System.out.println(new BinaryJingzhi().NumberOf1(-1));

    }

}
