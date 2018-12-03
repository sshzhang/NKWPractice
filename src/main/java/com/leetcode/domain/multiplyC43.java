package com.leetcode.domain;

public class multiplyC43 {

    public String multiply(String num1, String num2) {

        int len1 = num1.length();

        int len2 = num2.length();

        if(num1.equals("0")||num2.equals("0")) return "0";

        if(num1.equals("1")) return num2;
        if(num2.equals("1")) return num1;

        String result = "";//保存临时数据结果
        String before = "";//之前求得的数据和
        String current = "";//当前的数据和
        for (int i = len1 - 1; i >= 0; i--) {

            int l1 = num1.charAt(i) - '0';
            int position = 0;
            //补充零的个数
            for (int k = 1; k <= len1 - i - 1;k++) current += "0";

            for (int j = len2 - 1; j >= 0; j--) {

                int l2 = num2.charAt(j) - '0';
                int suml = l2 * l1 + position;
                //进位数据
                position = suml / 10;
                current = suml%10 +""+ current;
            }
             //最后一位进位数据
            if(position!=0) current = position + "" + current;

            if ("".equals(before)) {
                before = current;
                current = "";

            }else{//合并


                int curIndex = current.length()-1;

                int beIndex = before.length()-1;

                int positionx = 0;

                while (curIndex >= 0) {

                    int curr = current.charAt(curIndex) - '0';
                    int be = beIndex >= 0 ? before.charAt(beIndex) - '0' : 0;

                    int resum = curr + be + positionx;
                    positionx = resum / 10;
                    result = resum%10+""+result ;

                    curIndex--;
                    beIndex--;

                }

                if (positionx != 0) {

                    result = "1" + result;

                }

                before = result;
                result = "";
                current = "";
            }

        }

        return before;

    }


    public static void main(String... args) {

        System.out.println(new multiplyC43().multiply("9133", "0"));
    }
}
