package com.jianzhioffer.domain;

import java.util.Scanner;

public class wangyi03 {

    public static void main(String... args) {


        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t>0) {

            int n = scanner.nextInt();

            int k = scanner.nextInt();

            if(n==k) System.out.println(n-1>=0?n-1:0);
            else if (k > n - k) {
                System.out.println(n-k>=0?n-k:0);
            } else if (k ==n - k) {
                System.out.println(n-k-1>=0?n-k:0);
            }else{
                System.out.println(k-1>=0?n-k:0);
            }
            t--;
        }
    }

}
