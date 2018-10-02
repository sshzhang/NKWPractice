package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class GetMedianAndInsert {


    public static final ArrayList<Integer> params = new ArrayList<>();

    public void Insert(Integer num) {
        params.add(num);
    }

    public Double GetMedian() {

        Integer[] a = new Integer[params.size()];
        Integer[] objects = params.toArray(a);
        Arrays.sort(objects);

        if (objects.length % 2 == 0) {
            return (objects[objects.length / 2] + objects[objects.length / 2 - 1])/2d;
        } else {
            return objects[objects.length / 2]/1d;
        }
    }
}
