package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * 网易前端 字符串间隔最长的连续字符串
 *
 * wwb 有三种  wbw
 *
 */
public class wangyi2 {

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();
            List<PartArrayNode> lists = new ArrayList<PartArrayNode>();
            int max = 0, count = 1, indexleft = 0, indexright = 0;
            int order = 0;
            char before=' ' ;
            for (int i=0; i < input.length(); i++) {
                if (i==0||before != input.charAt(i)) {
                    indexright++;
                    count++;
                } else {

                    lists.add(new PartArrayNode(indexleft, indexright));
                    max = max > count ? max : count;
                    count = 1;
                    indexright = indexleft = i;
                }
                before = input.charAt(i);
            }

            if(indexleft!=indexright) lists.add(new PartArrayNode(indexleft, indexright));

            // 0  length

            int temtmax = 0;
            if(lists.size()==1) {
                System.out.println(input.length());
                continue;
            }
            PartArrayNode partArrayNode1 = lists.get(0);
            PartArrayNode partArrayNode = lists.get(lists.size() - 1);
            if (input.charAt(partArrayNode.leftindex) != input.charAt(partArrayNode1.rightindex)) {
                temtmax=partArrayNode.rightindex - partArrayNode.leftindex + 1 + partArrayNode1.rightindex - partArrayNode1.leftindex + 1;
            }
            max = max > temtmax ? max : temtmax;
            System.out.println(max);
        }

    }


    static  class  PartArrayNode{
        private int leftindex;
        private int rightindex;

        public PartArrayNode(int leftindex, int rightindex) {
            this.leftindex = leftindex;
            this.rightindex = rightindex;
        }
    }

}
