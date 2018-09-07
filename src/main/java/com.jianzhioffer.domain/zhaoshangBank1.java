package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 招商银行第一题
 *
 * 求最少的交换次数 使的最后 仅有一对鸡鸭相邻
 * CCDCC->CCCDC->CCCCD
 * 2
 * 其实就是指定C 向左移动 还是向右移动问题
 *
 */
public class zhaoshangBank1 {

    public static void main(String... args) {

        List<Integer> paramC = new ArrayList<Integer>();
        List<Integer> paramD = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        for (int i=0;i<line.length();i++) {
            if(line.charAt(i)=='C') paramC.add(i);
            else paramD.add(i);
        }
        int min = Integer.MAX_VALUE;
        //现求C 的移动距离  其实对于D的移动与C对称  C 向左移动 就是D向右移动  C 向右移动  就是D向左移动
        int Ccountl = 0, Ccountr = 0;
        for (int i=0;i<paramC.size();i++) {
            Ccountl += paramC.get(i) - i;
            Ccountr += line.length() - (paramC.size() - i - 1) - paramC.get(i) - 1;
        }
        min = Ccountl > Ccountr ? Ccountr : Ccountl;
       /* //现求C 的移动距离
        int Dcountl = 0, Dcountr = 0;
        for (int i=0;i<paramD.size();i++) {
            Dcountl+= paramD.get(i) - i;
            Dcountr += line.length() - (paramD.size() - i - 1) - paramD.get(i) - 1;
        }
        min = (Dcountl > Dcountr) ? (min > Dcountr ? Dcountr : min) : (min > Dcountl ? Dcountl : min);*/
        System.out.println(min);
    }

}
