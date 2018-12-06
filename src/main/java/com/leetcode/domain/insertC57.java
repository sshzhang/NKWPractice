package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class insertC57 {


    /**
     * 此问题没有考虑 区间相等的情况
     * 取出所有可能的情况
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {


        List<Interval> results = new ArrayList<Interval>();


        int start[] = getTheNearPosition(0, intervals, newInterval);
        int end[] = getTheNearPosition(1, intervals, newInterval);


        if (start[0] == -1) {//此时start[1]=0

            if(end[1]==intervals.size()){//此时end[0]=intervals.size()-1
                results.add(newInterval);
            }else if(end[1]==end[0]){

                results.add(new Interval(newInterval.start, intervals.get(end[1]).end));

                for (int j = end[1] + 1; j < intervals.size(); j++) {
                    results.add(intervals.get(j));
                }
            }else {

                results.add(newInterval);
                for (int i = end[1] > end[0] ? end[1] : end[0]; i < intervals.size(); i++) {
                    results.add(intervals.get(i));
                }
            }

        }else{


            if (start[0] == start[1]) {//某个集合的里面

                if (end[1] == intervals.size()) {
                    for (int i = 0; i < start[0]; i++) {
                        results.add(intervals.get(i));
                    }
                    results.add(new Interval(intervals.get(start[0]).start, newInterval.end));
                } else if (end[1] == end[0]) {

                    for (int j = 0; j < start[0]; j++) {
                        results.add(intervals.get(j));
                    }
                    results.add(new Interval(intervals.get(start[0]).start, intervals.get(end[1]).end));
                    for (int j = end[1] + 1; j < intervals.size(); j++) {
                        results.add(intervals.get(j));
                    }
                } else{

                    for (int i = 0; i < start[0]; i++) {
                        results.add(intervals.get(i));
                    }

                    results.add(new Interval(intervals.get(start[0]).start, newInterval.end));
                    for (int i = end[1] > end[0] ? end[1] : end[0]; i < intervals.size(); i++) {
                        results.add(intervals.get(i));
                    }

                }



            }else{ //start[0]<start[1] 或者start[0]>start[1]

                if (end[1] == intervals.size()) {

                    int dest = start[0] > start[1] ? start[0] : start[1];

                    for (int i = 0; i < dest; i++) {

                        results.add(intervals.get(i));
                    }
                    results.add(newInterval);
                } else if (end[1] == end[0]) {

                    int dest = start[0] > start[1] ? start[0] : start[1];

                    for (int i = 0; i < dest; i++) {

                        results.add(intervals.get(i));
                    }

                    results.add(new Interval(newInterval.start, intervals.get(end[1]).end));

                    for (int j = end[1] + 1; j < intervals.size(); j++) {
                        results.add(intervals.get(j));
                    }

                }else{

                    int dest = start[0] > start[1] ? start[0] : start[1];

                    int other = end[0] > end[1] ? end[0] : end[1];

                    for (int i = 0; i < dest; i++) {

                        results.add(intervals.get(i));
                    }

                    results.add(newInterval);
                    for (int j = other; j < intervals.size(); j++) {
                        results.add(intervals.get(j));
                    }
                }

            }

        }

        return results;

    }


    public int[] getTheNearPosition(int index, List<Interval> intervals, Interval newInterval) {

        int n = intervals.size();

        int low = 0;
        int high = n - 1;
        int position1 = -1;
        int position2 = -1;


        if (index == 0) {

            while (low <= high) {

                int mid = (low + high) / 2;
                Interval interval = intervals.get(mid);

                if (interval.start == newInterval.start) {
                    position1 = mid;
                    break;
                }
                if (interval.start > newInterval.start) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (position1 == -1) {//没有找到
                position1 = high;

            }

            low = 0;
            high = n - 1;


            while (low <= high) {

                int mid = (low + high) / 2;
                Interval interval = intervals.get(mid);

                if (interval.end == newInterval.start) {
                    position2 = mid;
                    break;
                }
                if (interval.end > newInterval.start) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (position2 == -1) {//没有找到
                position2 =low;

            }


            return new int[]{position1, position2};

        }else{


            while (low <= high) {

                int mid = (low + high) / 2;
                Interval interval = intervals.get(mid);

                if (interval.start == newInterval.end) {
                    position1 = mid;
                    break;
                }
                if (interval.start > newInterval.end) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (position1 == -1) {//没有找到
                position1 = high;

            }

            low = 0;
            high = n - 1;


            while (low <= high) {

                int mid = (low + high) / 2;
                Interval interval = intervals.get(mid);

                if (interval.end == newInterval.end) {
                    position2 = mid;
                    break;
                }
                if (interval.end > newInterval.end) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (position2 == -1) {//没有找到
                position2 =low;
            }

            return new int[]{position1, position2};

        }



    }


    public static void main(String... args) {


        List<Interval> intervals = new ArrayList<Interval>();

        Interval interval = new Interval(4, 8);
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(12, 16));
        System.out.println(new insertC57().insert(intervals, interval));

    }


    static  class Interval {


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
