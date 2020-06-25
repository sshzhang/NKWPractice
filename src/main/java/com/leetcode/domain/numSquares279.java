package com.leetcode.domain;

public class numSquares279 {

    /**
     * 通过动态规划算法　　dp[i]=min(dp[i-j*j], dp[i-(j-1)*(j-1),...]) i-j*J>=0
     * dp[0]=0  dp[1]=1  dp[2]=dp[1]+1=2
     *
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int j = 1;
            int min = n;
            while (i - j * j >= 0) {
                min = Math.min(min, dp[i - j * j] + 1);
                j++;
            }
            dp[i] = min;

        }

        return dp[n];
    }


    /**
     * 算法204 count primes
     * 统计小于某个数n 的素数个数
     */

    public int countPrimes(int n) {

        boolean[] isPrime = new boolean[n];
        //默认为｀１素数
        for (int i = 2; i < n; i++) isPrime[i] = true;
        for (int i = 2; i < n; i++) {

            //设置2,3,5,7所有数的倍数不为素数
            if(!isPrime[i]) continue;
            for (int j = i * i; j < n; j=j*i) {
                isPrime[j] = false;
            }
        }


        int count = 0;
        for (int k = 2; k < n; k++) {
            if(isPrime[k]) count++;
        }

        return count;
    }

}
