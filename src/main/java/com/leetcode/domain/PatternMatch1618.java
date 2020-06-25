package com.leetcode.domain;

public class PatternMatch1618 {

    public boolean patternMatching(String pattern, String value) {
        // 模式串是否为空标志
        boolean isPatternEmpty = pattern == null || "".equals(pattern);
        // 值串是否为空标志
        boolean isValueEmpty = value == null || "".equals(value);
        int pLen = pattern == null ? 0 : pattern.length();
        int vLen = value == null ? 0 : value.length();
        // aNum 表示模式串中a出现的次数, bNum表示b出现的次数
        int aNum = 0, bNum = 0, num = 0, cLen = 0;
        for (int i = 0; i < pLen; i++) {
            char curr = pattern.charAt(i);
            if ('a' == curr) aNum++;
            else bNum++;
        }
        if (isPatternEmpty) {// 模式串为空, 那么直接返回对应值串是否为空
            return isValueEmpty;
        } else if (isValueEmpty) { // 模式串不为空 且值串为空
            return aNum == 0 || bNum == 0;
        } else { // 两者都不为空
            // 一个元素组成
            if (aNum == 0 || bNum == 0) {
                num = aNum == 0 ? bNum : aNum;
                if (vLen % num != 0) return false;
                cLen = vLen / num;
                StringBuilder stringBuilder = new StringBuilder();
                String substring = value.substring(0, cLen);
                for (int k = 1; k <= num; k++) {
                    stringBuilder.append(substring);
                }
                return stringBuilder.toString().equals(value);
            } else {// 两个元素组成
                // b每个对应的字符串长度从0开始
                int maxbNum = vLen / bNum;
                String strA, strB;
                for (int x = 0; x <= maxbNum; x++) {
                    strA = null;
                    strB = null;
                    // distanceANum 表示所有的a占的总长度
                    int distanceANum = vLen - x * bNum;
                    // 记录位置信息
                    int currIndexPosi = 0;
                    boolean isPatternSucess = true;
                    // 能被整除
                    if (distanceANum % aNum == 0) {
                        for (int k = 0, eachANum=distanceANum/aNum; k < pLen; k++) {
//                            if (pattern.charAt(k) == 'a') {
//                                String currStr = value.substring(currIndexPosi, currIndexPosi + x);
//                                if("".equals(strA)){
//                                    strA=currStr;
//                                }else if(!currStr.equals(strA)) {isPatternSucess=false;break;}
//                                currIndexPosi += x;
//                            } else {
//                                String currStr = value.substring(currIndexPosi, eachANum);
//                                if("".equals(strB)){
//                                    strB=currStr;
//                                }else if(!currStr.equals(strB))  {isPatternSucess=false;break;}
//                                        currIndexPosi += eachANum;
//                            }
                            boolean isAFlage = pattern.charAt(k) == 'a';
                            int eachIncrementNum=isAFlage?eachANum:x;
                            String currStr = value.substring(currIndexPosi,currIndexPosi+eachIncrementNum);
                            if(isAFlage&&strA==null){
                                strA = currStr;
                            }else if(strB==null&&!isAFlage){
                                strB = currStr;
                            }
                            // 需要判断的字符串
                            String destReallyStr = isAFlage ? strA : strB;
                            if(!currStr.equals(destReallyStr)){isPatternSucess=false;break;}
                            currIndexPosi += eachIncrementNum;
                            if(strA!=null&&strA.equals(strB)){isPatternSucess=false;break;}
                        }
                        if(isPatternSucess) return true;
                    }
                }
                return false;
            }
        }
    }

    public  static  void main(String...args){

        System.out.println(new PatternMatch1618().patternMatching("aaaaab", "xahnxdxyaahnxdxyaahnxdxyaahnxdxyaauxuhuo"));
    }
}
