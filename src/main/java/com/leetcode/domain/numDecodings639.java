package com.leetcode.domain;

public class numDecodings639 {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        for (int k = 1; k < dp.length; k++) {
            char currV = s.charAt(k - 1);
            char befoV = ' ';
            dp[k] = currV == '0' ? 0 : (currV == '*' ? 9 * dp[k - 1] : dp[k - 1]);
            if (k > 1) {
                befoV = s.charAt(k - 2);
                if (befoV == '*' && currV == '*') {
                    dp[k] += dp[k - 2] * 15;
                } else if (befoV != '*'&&currV=='*') {
                    if (befoV == '1') dp[k] += dp[k - 2] * 9;
                    else if (befoV == '2') dp[k] += dp[k - 2] * 6;
                } else if (currV != '*'&&befoV=='*') {
                    if (currV <= '6' && currV >= '0') dp[k] += dp[k - 2] * 2;
                    else dp[k] += dp[k - 2];
                } else {
                    if (befoV == '1' || (befoV == '2' && currV <= '6'))
                        dp[k] += dp[k - 2];

                }
                //四种情况  1. i-1 i-2全为*  2. 其中一个为*   3.都不为*
            }
            dp[k] %= 1000000007;
        }
        return (int)dp[s.length()];
    }

    public static void main(String... args) {
        numDecodings639 numDecod = new numDecodings639();
        int i = numDecod.numDecodings("********************"); //1111111111
        System.out.println(i);

    }

}
