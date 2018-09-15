package com.jianzhioffer.domain;

/**
 * 剑指offer
 * 把数组排成最小的数
 */
public class arrayminvalue {


    public String PrintMinNumber(int[] numbers) {

        if (numbers == null || numbers.length == 0) return "";

        String params[] = new String[numbers.length];
        for (int i = 0; i < params.length; i++) {
            params[i] = String.valueOf(numbers[i]);
        }

        for (int i = 1; i < params.length; i++) {

            String tempt = params[i];

            int j = i - 1;
            for (; j >= 0 && Compare(tempt, params[j]); j--) {
                params[j+1] = params[j];
            }
            params[j+1] = tempt;
        }


        String result = "";
        for (int i = 0; i < params.length; i++) {
            result += params[i];
        }
        return result;

    }


    public boolean Compare(String o1, String o2) {

        int max = o1.length() > o2.length() ? o1.length() : o2.length();

        for (int i = 0; i < max; i++) {
            char os1 = o1.charAt(i % o1.length());
            char os2 = o2.charAt(i % o2.length());

            if(os1>os2) return false;
            else if(os1<os2) return true;
        }

        return false;
    }


    public static void main(String... args) {

        System.out.println(new arrayminvalue().PrintMinNumber(new int[]{3,5,1,4,2}));
    }

}
