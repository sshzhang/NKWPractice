package com.jianzhioffer.domain;

public class TheTimes1Occures {

    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            String sm = String.valueOf(n);
            if (sm.contains("1")) {

                for (int j = 0; j < sm.length(); j++) {
                    if (sm.charAt(j) == '1') {
                        count++;
                    }
                }
            }
        }

        return count;
    }

}
