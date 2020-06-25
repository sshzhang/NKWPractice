package com.leetcode.domain;

import java.util.*;

public class MergeArea56 {


    public int[][] merge(int[][] intervals) {

        List<int[]> tempt = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            tempt.add(intervals[i]);
        }
        Collections.sort(tempt, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0] != 0 ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });
        List<int[]> result = new ArrayList<>();
        boolean isFlage = false;
        for (int i = 0; i < tempt.size(); ) {
            int[] currs = tempt.get(i);
            for (int j = i + 1; j < tempt.size();i=j, j++) {
                int[] after = tempt.get(j);
                if (currs[0] <= after[0] && after[0] <= currs[1]) {
                    currs[1] = currs[1] > after[1] ? currs[1] : after[1];
                    isFlage = true;
                } else {
                    isFlage = false;
                    result.add(currs);
                    i = j;
                    break;
                }
            }
            if (i == tempt.size() - 1) {
                result.add(isFlage ? currs : tempt.get(i));
                i++;
            }
        }
        int[][] nArrays = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            nArrays[i] = result.get(i);
        }
        return nArrays;
    }

    public static void main(String... args) {

        new MergeArea56().merge(new int[][]{{1, 4}, {4, 5}});

    }

}
