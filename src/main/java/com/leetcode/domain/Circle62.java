package com.leetcode.domain;

/**
 * 圆圈中最后一个剩下的数字  leetcode 62
 */
public class Circle62 {

    public int lastRemaining(int n, int m) {

        /*List<Integer> params = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            params.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            params.remove(idx);
            n--;
        }
        return params.get(0);*/

        //约瑟夫环的做法

        // 由于每次删除的都是第m个元素， 因此可以回推出现在第0个元素在原来数组中的位置信息，从而能够获取对应的数据u
        int idx=0;
        for (int i = 2; i <= n; i++) {
            idx=(idx+m)%i;
        }
        return idx;
    }
}
