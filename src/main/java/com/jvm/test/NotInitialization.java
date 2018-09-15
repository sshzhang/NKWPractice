package com.jvm.test;

public class NotInitialization {
    //方法区中分配内存
    public static String text = "2";
    //　堆中分配内存
    public  String  tm = new String("2");

    public String tmo = "2";

    private NotInitialization() {
        System.out.println("NotInitialization");
    }
    public static void main(String... args) {

        NotInitialization notInitialization = new NotInitialization();
        System.out.println(notInitialization.tm==text);
        System.out.println(notInitialization.tm == notInitialization.tmo);
    }
}
