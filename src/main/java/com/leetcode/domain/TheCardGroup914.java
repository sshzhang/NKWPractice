package com.leetcode.domain;

import java.util.HashMap;

/**
 * 卡牌分组
 */
public class TheCardGroup914 {

    /**
     * 求一组数的最大公约数
     *
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {

        if (deck.length < 2) return false;
        // 利用hashMap来保存每个记录
        HashMap<Integer, Integer> maps = new HashMap<Integer, Integer>();
        for (int i = 0; i < deck.length; i++) {
            maps.put(deck[i], maps.getOrDefault(deck[i], 0) + 1);
        }
        Integer[] values = new Integer[maps.size()];
        maps.values().toArray(values);
        int tempt = -1;
        for (int i = 0; i < values.length; i++) {
            if (tempt == 1) return false;
            int a = i == 0 ? values[i] : tempt;
            int b = values[i];
            if (a < b) {
                tempt = b;
                b = a;
                a = tempt;
            }

            while ((tempt = a % b) != 0) {
                a = b;
                b = tempt;
            }
            tempt = b;
        }

        return tempt >= 2;
    }




}
