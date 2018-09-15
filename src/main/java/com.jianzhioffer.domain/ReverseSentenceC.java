package com.jianzhioffer.domain;

/**
 * 翻转单词
 */
public class ReverseSentenceC {


    public String ReverseSentence(String str) {

        if(str==null||str.equals("")) return str;
        String[] s = str.split(" ");
        //一定要加这个判断　否则不能通过
        if(s.length==0) return str;
        StringBuilder bf = new StringBuilder();
        for (int i = s.length - 1; i >= 0; i--) {
            bf.append(s[i]);
            if(i!=0) bf.append(" ");
        }

        return bf.toString();
    }

    public static void main(String... args) {

        System.out.println(new ReverseSentenceC().ReverseSentence("12   "));
        System.out.println(" ".equals(" "));
    }
}
