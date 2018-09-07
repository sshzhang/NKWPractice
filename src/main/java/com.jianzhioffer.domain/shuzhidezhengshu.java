package com.jianzhioffer.domain;

/**
 * 数值的整数次方问题
 *
 * 先判断指数的正负零
 * 再判断底数 是否为0  特殊 0的0次方为1
 */
public class shuzhidezhengshu {

    public double Power(double base, int exponent) {

        int n = 0;
        if (exponent > 0) {
            n = exponent;
        } else if (exponent < 0) {

            if (base == 0) {
                return 0;
            }
            n = -exponent;
        }else{
            return  1; //0的0次方等于1
        }

        double std = 1;
        while (n > 0) {
            std *= base;
            n--;
        }
        return exponent > 0 ? std : 1 / std;
    }

    public static void main(String... args) {

        System.out.println( new shuzhidezhengshu().Power(2,-1));

    }

}
