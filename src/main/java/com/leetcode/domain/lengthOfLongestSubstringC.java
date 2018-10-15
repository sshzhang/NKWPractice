package com.leetcode.domain;

import java.util.ArrayList;
import java.util.ListIterator;

public class lengthOfLongestSubstringC {

    public int lengthOfLongestSubstring(String s) {

        //下一个位置采取的点
        int startOrder=1;
        //此序列的开始点为
        int startPoint=0;
        int maxValue=1;
        int len = s.length();
        int index = 0;
        ArrayList<String> strings = new ArrayList<String>();
        ArrayList<Integer> integers = new ArrayList<Integer>();
        strings.add(s.charAt(startPoint) + "");
        integers.add(0);
        while(startPoint<len-1){
            for(int i=startOrder;i<len;i++){
                if (strings.contains(s.charAt(i) + "")) {
                    index = integers.get(strings.indexOf(s.charAt(i) + ""));
                    maxValue = maxValue > strings.size() ? maxValue : strings.size();
                    ListIterator<Integer> integerListIterator =
                            integers.listIterator();
                    ListIterator<String> stringListIterator = strings.listIterator();
                    for (int j=startPoint;j<=index;j++) {
                        integerListIterator.next();
                        integerListIterator.remove();
                        stringListIterator.next();
                        stringListIterator.remove();
                    }
                    //下一个序列的开始点为
                    startPoint = index + 1;
                    //下一个序列的开始采集点为
                    startOrder = i;
                    break;
                }else{
                    strings.add(s.charAt(i) + "");
                    integers.add(i);
                }
            }
        }
        return maxValue;
    }


    public static void main(String... args) {

        int abcabcbb = new lengthOfLongestSubstringC().lengthOfLongestSubstring("abb");
        System.out.println(abcabcbb);

    }
}
