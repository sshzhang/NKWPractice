package com.leetcode.domain;

public class isUgly263 {


    public boolean isUgly(int num) {

        int[] params = new int[]{2, 3, 5};
        if (num == 0) return false;
        for (int i = 0; i < params.length; i++) {
            while (num % params[i] == 0) {
                num = num / params[i];
            }
        }

        return num == 1;
    }


    // t2表示2出现的次数, t3表示3出现的次数  t5表示5出现的次数
    // k[0]=1  k[1]=min(2*k[t2], 3*k[t3], 5*k[t5])
    public int nthUglyNumber(int n) {

        if(n<=0) return 0;
        int t2 = 0, t3 = 0, t5 = 0;
        int[] k = new int[n];
        k[0] = 1;

        for (int i = 1; i < n; i++) {
            k[i] = Math.min(k[t2] * 2, Math.min(k[t3] * 3, k[t5] * 5));

            if(k[i]==k[t2]*2) t2++;
            if(k[i]==k[t3]*3) t3++;
            if(k[i]==k[t5]*5) t5++;
        }

        return k[n - 1];
    }

}
