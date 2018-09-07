package com.jianzhioffer.domain;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 哔哩哔哩标签闭合问题
 * 每个属性声明以空格分隔 且属性值不能含有'
 *
 */
public class bibi {

    public static Stack<String> sk = new Stack<>();
    public static void main(String... args) {
        String input = "<a href='ssh' of='s'sm'><b></a></b><m/>";
        Pattern compile = Pattern.compile("<.*?>");
        Matcher matcher =
                compile.matcher(input);
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
            String substring = group.substring(1, group.length() - 1);
            //属性的定义是否满足要求
            int i = substring.indexOf("/");
            String[] split = substring.split("[[ ]+|=]");
            for (int j=1;j<split.length;j++) {
                if(j%2==0){
                    String substring1 = split[j].substring(1, split[j].length() - 1);
                    if(substring1.indexOf("'")!=-1){
                        System.out.println("error");
                        break;
                    }
                }
            }
            if (i == 0) {//</a>集合类型
                if(sk.isEmpty()) System.out.println("error");
                else{
                    String pop = sk.pop();
                    if(!pop.equals(split[0].substring(1,split[0].length()))) System.out.println("error");
                }
            } else if (i == -1) {
                sk.push(split[0]);
            } else {//<a/>
                //不需要处理
            }
        }
        if (sk.isEmpty()) {
            System.out.println("success");
        }else{
            System.out.println("error");
        }
    }
}
