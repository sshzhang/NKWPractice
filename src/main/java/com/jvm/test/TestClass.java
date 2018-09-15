package com.jvm.test;

public class TestClass {

    public int m;
    final static int s = 12;

    final static StringBuilder sb = new StringBuilder("sm");

    public int inc() {
        return m + 1;
    }


    class  MYInnerClass{


    }

    public static void main(String... args) {

        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }


}
