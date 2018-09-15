package com.jvm.test;

public class ConstClass {

    public static  int HELLOWORLD =1;

    static{
        System.out.println("ConstClass init!");

    }

    public static final StringBuilder bu = new StringBuilder("hello world");

}
