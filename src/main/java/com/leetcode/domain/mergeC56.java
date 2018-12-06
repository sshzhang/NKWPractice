package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class mergeC56 {


    public List<Interval> merge(List<Interval> intervals) {


        List<Interval> results = new ArrayList<Interval>();
        if(intervals==null||intervals.size()==0) return results;
        //先排序
        intervals.sort(new MyComporator());
        results.add(intervals.get(0));
        //一个一个元素添加
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            Interval ointerval = results.get(results.size() - 1);
            if(interval.start<=ointerval.end){
                ointerval.end = Math.max(interval.end, ointerval.end);

            }else{
                results.add(interval);
            }
        }
        return results;
    }


    private class MyComporator implements Comparator<Interval> {

        @Override
        public int compare(Interval o1, Interval o2) {

            if (o1.start == o2.start) return o1.end - o2.end;
            else return o1.start - o2.start;

        }
    }


    class Interval {


        int start;
        int end;

        Interval() {
            this.start = 0;
            this.end = 0;
        }

        Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

}
