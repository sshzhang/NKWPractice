package com.leetcode.domain;

/**
 * Integer to Roman
 */
public class intToRomanC {

    public static String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }


    public String intToRomanU(int num) {
        StringBuilder sb = new StringBuilder();
        String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        if (letters.length != values.length) throw new RuntimeException("mismatch");

        int ptr = 0;
        while (ptr < letters.length) {
            for (int i = 0; i < num / values[ptr]; i++) {
                sb.append(letters[ptr]);
            }
            num %= values[ptr];
            ptr++;
        }
        return sb.toString();
    }

    public int romanToInt(String s) {


        char last = ' ';

        int total = 0;

        for (char sm : s.toCharArray()) {

            if(sm=='M') total += 1000;
            else if(sm=='D') total += 500;
            else if(sm=='C') total += 100;
            else  if(sm=='L') total+=50;
            else if(sm=='X') total+=10;
            else  if(sm=='V') total+=5;
            else  if(sm=='I') total+=1;

            if(last=='I'&&(sm=='V'||sm=='X')) total-=2;

            if(last=='X'&&(sm=='L'||sm=='C')) total -= 20;

            if(last=='C'&&(sm=='D'||sm=='M')) total -= 200;
            last = sm;
        }

        return total;
    }

}
