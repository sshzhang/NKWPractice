package com.leetcode.domain;

public class CSubArray1248 {

    /**
     * 暴力+滑动窗口+剪枝
     * 剪枝条件， 如果移除的第i个元素是偶数，那么总的满足条件情况数和未移除第i个元素相同。
     * ​ 比如数据 2，2，2，1，2，2，1，2，2，2
     * ​ 第一个for循环​ dp中数据为 0，0，0，1，0，0，1，0，0，0
     * ​ 第二个for循环 计算以nums[0]开头，以nums[i]结尾的子数组中奇数的个数
     * ​ dp中数据为 0，0，0，1，1，1，2，2，2，2
     * ​ count=4
     * ​ 当我们计算nums[1]开头 nums[i]结尾的子数组中奇数个数时，分两种情况
     * ​ a）如果nums[0]移除的元素为偶数，那么此种情况下count并未改变依然为4，直接返回
     * ​ b) 如果nums[0]移除的元素为奇数，那么相当于target变为原来的target+1,查找满足条件的奇数个数。
     * 滑动窗口 优化
     * 查找过程中，我们利用滑动窗口来保存当前满足条件的记录区间。
     * ​ posiMin 每次保存当前记录第一次满足奇数出现k次的位置
     * ​ posiMax 每次保存第一次满足奇数出现k+1次的位置。从而减少查找时间
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        // dp[i][j] 计算
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        int count = 0, sum = 0;
        // 初始化距离为l情况下奇数的个数
        for (int i = 0; i < len; i++) {
            dp[i] = nums[i] % 2;
        }
        // 数据
        count = 0;
        int posiMin = -1;
        int posiMax = -1;
        for (int i = 0; i < len; i++) {
            dp[i] = (i > 0 ? dp[i - 1] : 0) + nums[i] % 2;
            count += (dp[i] == k ? 1 : 0);
            posiMin = posiMin == -1 && dp[i] == k ? i : posiMin;
            posiMax = posiMax == -1 && dp[i] == k + 1 ? i : posiMax;
        }
        sum += count;
        int m = 1, cm = 0;
        for (; m < len; m++) {
            if (nums[m - 1] % 2 == 0) {
                sum += count;
            } else {
                cm++;
                // 没有比它更大的数据
                if (posiMax == -1 || posiMax == len) return sum;
                posiMin = posiMax;
                while (posiMax < len && dp[posiMax] == cm + k) posiMax++;
                count = posiMax - posiMin;
                sum += count;
            }
        }
        return sum;
    }


    /**
     * 利用前缀和 prefixSum数组， 索引表示前缀和， 值表示前缀和的个数
     * 本质上相当于把prefix[sum]出现次数分割为对应prefix[sum-k] 中sum-k前缀和出现次数.
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarraysU(int[] nums, int k) {
        int len = nums.length;
        // 前缀和最大为len, 所以数组长度为len+1
        int[] prefixSum = new int[len + 1];
        // 初始值设置前缀和为0时，前缀和个数为1。
        // 因为如果全部元素都为1的话，那么计算的时候存在一个初始出现次数1
        prefixSum[0] = 1;
        int sum = 0, res = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i] & 1;
            prefixSum[sum]++;
            if (sum >= k) res += prefixSum[sum - k];
        }
        return res;
    }


    /**
     * 利用滑动窗口， （第一次出现奇数的位置对应左边偶数个数+1）*（结束位置对应奇数后面的偶数个数+1）
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarraysUU(int[] nums, int k) {
        int res = 0, left = 0, right = 0, count = 0;
        int len = nums.length;
        while (right < len) {

            if ((nums[right++] & 1) == 1) {
                count++;
            }
            if (count == k) {
                int rightChrage = 0;
                while (right < len && (nums[right] & 1) == 0) {
                    rightChrage++;
                    right++;
                }
                // 左边界出现次数
                int leftCharge = 0;
                while (left < len && (nums[left] & 1) == 0) {
                    leftCharge++;
                    left++;
                }
                res += (leftCharge + 1) * (rightChrage + 1);
                left++;
                count--;
            }
        }
        return res;
    }


    public static void main(String... args) {
        System.out.println(new CSubArray1248().numberOfSubarrays(new int[]{1, 1, 1, 1, 1}, 1));
    }
}

