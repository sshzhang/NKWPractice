package com.jianzhioffer.domain;

/**
 * 表示数字的字符串
 * 通过自动机来构造规则　判断
 */
public class isNumeriC {

    public boolean isNumeric(char[] string) {


        int i = 0;
        if (string[i] == '+' || string[i] == '-' || IsNum(string[i])) {

                while ((++i) <string.length && IsNum(string[i])) ;
                  if(i==string.length) return true;
                 else  if (string[i] == '.') {
                    if(++i==string.length) return false;
                    else if (IsNum(string[i])) {
                        while (++i < string.length && IsNum(string[i])) ;
                         if (i==string.length) {
                            return true;
                        }
                        else if (string[i] == 'e' || string[i] == 'E') {

                             if(++i==string.length) return false;

                             else if (string[i] == '+' || string[i] == '-' ) {


                                 if(++i<string.length&&IsNum(string[i])){

                                     while (++i<string.length&&IsNum(string[i]));
                                     if(i==string.length) return true;
                                     else return false;

                                 } else return false;

                             }else if(IsNum(string[i])){
                                 while (++i<string.length&&IsNum(string[i]));
                                 if(i==string.length) return true;
                                 else return false;
                             }

                        }  else {
                            return false;
                        }
                    }else
                        return false;
                } else if (string[i] == 'e' || string[i] == 'E') {

                    if(++i==string.length) return false;

                    else if (string[i] == '+' || string[i] == '-' ) {


                       if(++i<string.length&&IsNum(string[i])){

                           while (++i<string.length&&IsNum(string[i]));
                           if(i==string.length) return true;
                           else return false;

                       } else return false;

                    }else if(IsNum(string[i])){
                        while (++i<string.length&&IsNum(string[i]));
                        if(i==string.length) return true;
                        else return false;
                    }

                 } else  return false;


        }
            return false;
    }

    public boolean IsNum(char ch) {
        if (ch <= '9' && ch >= '0') {
            return true;
        }
        return false;
    }

    public static void main(String... args) {
        System.out.println(new isNumeriC().isNumeric(new char[]{'1', 'a','3','.','1','4'}));
    }
}
