package com.leetcode.domain;

import java.util.*;

public class groupAnagramsC49 {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> params = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String tempt = String.valueOf(chars);
            boolean index = params.containsKey(tempt);


            if (!index) {
                List<String> objects = new ArrayList<>();
                objects.add(strs[i]);
                params.put(tempt, objects);

            } else {
                params.get(tempt).add(strs[i]);
            }
        }


        return new ArrayList(params.values());
    }


    public static void main(String... args) {

        new groupAnagramsC49().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});


    }
}
