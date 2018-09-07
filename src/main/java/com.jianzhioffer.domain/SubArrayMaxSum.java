package com.jianzhioffer.domain;

public class SubArrayMaxSum {

    public int FindGreatestSumOfSubArray(int[] array) {

        // 1.暴力破解法

        int maxValue = Integer.MIN_VALUE;
       /* for (int i=0;i<array.length;i++) {
            for (int j=i;j<array.length;j++) {
                int ans = 0;
                for (int k = i; k <= j; k++) {
                    ans += array[k];
                }
                if(ans>maxValue) maxValue = ans;
            }
        }*/


       //2. 通过维护一个数组sum[i] 表示 从第一个数加到第i个数
        for (int i=1;i<array.length;i++) {
            array[i] += array[i - 1];
        }
        for (int i=0;i<array.length;i++) {
            for (int j = i; j < array.length; j++) {
                int ans = array[j] - (i - 1 < 0 ? 0 : array[i - 1]);
                if(ans>maxValue) ans = maxValue;
            }
        }

     //3.分治法
        solve(0, array.length - 1, array);

        //4 动态规划  dp[n] 表示以第n个数结尾的最大连续子序列的和
        // dp[n]=max{0,dp[n-1]}+num[n]    整个数组的答案是 max(dp[n]|n从[1,m])
       /* int[] dp = new int[array.length];
        maxValue=dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            if(dp[i-1]<0){ dp[i] = array[i] + 0;}
            else{ dp[i] = dp[i - 1] + array[i];}
            if(maxValue<dp[i]) maxValue = dp[i];
        }*/
        return maxValue;
    }

    //分治法  分成三种情况
    //1 分割点左边   2 横跨分割点  3.分割点右边
    public int solve(int left, int right,int[] arrays) {

        if (left == right) {
            return arrays[left];
        }

        int mid = (left + right) >> 1;
        int lans = solve(left, mid, arrays);
        int rans = solve(mid + 1, right, arrays);

        //分割点的情况

        int sum = 0, lmax = arrays[mid], rmax = arrays[mid + 1];
        for (int i=mid;i>=left;i--) {
            sum += arrays[i];
            if(sum>lmax) lmax = sum;
        }
        sum = 0;

        for (int i=mid+1;i<=right;i++) {
            sum += arrays[i];
            if(sum>rmax) rmax = sum;
        }

        int ans = lmax + rmax;
        if(ans<lans) ans = lans;
        if(ans<rans) ans = rans;

        return ans;
    }

    public static void main(String... args) {

        new SubArrayMaxSum().FindGreatestSumOfSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5});

    }
}
