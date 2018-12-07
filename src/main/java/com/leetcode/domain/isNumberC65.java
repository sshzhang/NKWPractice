package com.leetcode.domain;

public class isNumberC65 {


    public boolean isNumber(String s) {


        s = s.trim();
        if(s==null||s.length()==0) return false;

        String[] es = s.split("e");

        if(es.length>2||es.length==0) return false;
        int  flages = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='e') flages++;
            if (!isTheRightSymbol(c)) {
                return false;
            }
        }
        if(flages>1) return false;


        if (es.length == 2) {

            String left = es[0];
            String right = es[1];

            if(left==""||right=="") return false;

            //判断数据
            int position = -1,positionNum=0;
            int negative = -1,negativeNum=0;
            int point = -1, pointNum = 0;
            for (int i = 0; i < left.length(); i++) {

                char c = left.charAt(i);
                if(c=='-'){
                    negative = i;
                    negativeNum++;
                }

                if(c=='+'){
                    position = i;
                    positionNum++;
                }

                if (c == '.') {
                    point = i;
                    pointNum++;
                }
            }


            if(positionNum>=1&&negativeNum>=1||pointNum>1||pointNum+negativeNum+positionNum==left.length()) return false;

            if(position!=-1&&position!=0) return false;

            if(negative!=-1&&negative!=0) return false;


             position = -1;positionNum=0;
             negative = -1;negativeNum=0;
             point = -1; pointNum = 0;


            for (int i = 0; i < right.length(); i++) {

                char c = right.charAt(i);
                if(c=='-'){
                    negative = i;
                    negativeNum++;
                }

                if(c=='+'){
                    position = i;
                    positionNum++;
                }

                if (c == '.') {
                    point = i;
                    pointNum++;
                }
            }


            if(positionNum>=1&&negativeNum>=1||pointNum>=1||pointNum+negativeNum+positionNum==right.length()) return false;

            if(position!=-1&&position!=0) return false;

            if(negative!=-1&&negative!=0) return false;



        }else {

            if(flages==1) return false;
            String left = es[0].trim();

            if(left=="") return false;

            //判断数据
            int position = -1,positionNum=0;
            int negative = -1,negativeNum=0;
            int point = -1, pointNum = 0;
            for (int i = 0; i < left.length(); i++) {

                char c = left.charAt(i);
                if(c=='-'){
                    negative = i;
                    negativeNum++;
                }

                if(c=='+'){
                    position = i;
                    positionNum++;
                }

                if (c == '.') {
                    point = i;
                    pointNum++;
                }
            }


            if((positionNum>=1&&negativeNum>=1)||pointNum>1||pointNum+negativeNum+positionNum==left.length()) return false;

            if(position!=-1&&position!=0) return false;

            if(negative!=-1&&negative!=0) return false;

        }


        return true;

    }

    public boolean isTheRightSymbol(char c) {

        if((c<='9'&&c>='0')||c=='e'||c=='-'||c=='+'||c=='.') return true;
        return false;

    }

    public static void main(String... args) {

        System.out.println(new isNumberC65().isNumber("0e"));
    }
}
