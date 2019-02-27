package com.leetcode.domain;

import java.util.Stack;

public class simplifyPath71 {

    public String simplifyPath2(String path) {

        Stack<String> elements = new Stack<String>();
        String[] splits = path.split("/");
        for (String sp : splits) {

            if (sp.equals("..")) {
                if(!elements.isEmpty())
                elements.pop();
            } else if (sp.equals(".")) {
            } else if(!sp.equals("")){
                elements.push(sp);
            }
        }

        String dest = "/";
        for (int i = 0; i < elements.size(); i++) {
            String s = elements.get(i);
            if(i!=0) s = "/"+s ;
            dest += s;
        }

        System.out.println(dest);
        return "";
    }


    public String simplifyPath(String path) {

        Stack<String> elements = new Stack<String>();
        path = path + "/";
        int len = path.length();
        String str = "";

        for (int i = 0; i < len; i++) {
            String element = path.charAt(i) + "";
            if (elements.isEmpty()) { //第一个元素
                elements.push(element);
            } else {
                if ((!element.equals(".")) && (!element.equals("/"))) {
                    str += element;
                }else{//当前
                    if(!str.equals(""))
                    elements.push(str);
                   String  peek = elements.peek();
                    str = "";
                    if (element.equals(".")) {
                        if (peek.equals("/")) {
                            elements.pop();
                            if(elements.isEmpty()) elements.push("/");
                        } else{
                            if (elements.size() <= 2) {
                                elements.clear();
                                elements.push("/");
                            } else {
                                elements.pop();
                                elements.pop();
                            }
                        }
                    }else if (element.equals("/")){
                         if (peek.equals("/")) {
                         } else if (i != len - 1) {
                             elements.push("/");
                         }
                    }
                }
            }

        }


        String dest = "";
        for (int i = 0; i < elements.size(); i++) {
            dest += elements.get(i);
        }

        if("/".equals(dest.charAt(dest.length()-1)+"")&&dest.length()>1) dest = dest.substring(0, dest.length() - 1);
        return dest;
    }

    public static void main(String... args) {

        String s = new simplifyPath71().simplifyPath2("/a//b////c/d//././/..");
    }
}
