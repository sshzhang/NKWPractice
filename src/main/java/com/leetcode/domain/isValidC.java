package com.leetcode.domain;


import java.util.Stack;

public class isValidC {


    public static String[] sm = new String[]{"(", "[", "{"};
    public static String[] em = new String[]{")","]","}"};
    public boolean isValid(String s) {
        Stack<String> sk = new Stack<>();
        if(s==null||s.length()==0) return true;

        for (int i=0;i<s.length();i++) {
            int rIndex = -1;
            String tempt = s.charAt(i) + "";
            for (int index=0;index<sm.length;index++) {
                if (sm[index].equals(tempt)) {
                    rIndex = index;
                    break;

                }
            }

            if (rIndex == -1) {
                if (!sk.isEmpty()) {
                    String pop = sk.pop();
                    int mIndex = -1;
                    for (int index=0;index<sm.length;index++) {
                        if (sm[index].equals(pop)) {
                            mIndex = index;
                            break;
                        }
                    }
                    if(!em[mIndex].equals(tempt)) return false;
                }else
                    return false;

            }else{
                sk.push(tempt);
            }
        }

        if(sk.isEmpty()) return true;
        return false;
    }

}
