package com.leetcode.domain;

public class Happy202 {
    public boolean isHappy(int n) {
        int low = n, fast = getNextHappyNum(n);
        while (low != fast) {
            low = getNextHappyNum(low);
            fast = getNextHappyNum(getNextHappyNum(fast));
        }
        return low == 1;
    }

    /**
     * 获取 数字n的下一个快乐数
     *
     * @param n
     * @return
     */
    private int getNextHappyNum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    public static void main(String... args) {

        System.out.println(new Happy202().isHappy(19));
    }
}
