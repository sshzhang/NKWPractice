package com.CollectionCodeAnalysisSeven;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] params = new int[n];
        for (int i = 0; i < params.length; i++) {
            params[i] = scanner.nextInt();
        }
        Arrays.sort(params);
        if (params.length % 2 == 0) {//偶数个数
            if ((params[params.length / 2] + params[params.length / 2 - 1]) % 2 == 0) {
                System.out.println((params[params.length / 2] + params[params.length / 2 - 1])/2);
            }else {
                System.out.println((params[params.length / 2] + params[params.length / 2 - 1]) / 2.0f);
            }
        } else {//奇数个数
            System.out.println(params[params.length / 2]);
        }

    }
}
