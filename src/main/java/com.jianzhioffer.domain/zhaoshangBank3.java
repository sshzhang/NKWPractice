package com.jianzhioffer.domain;

import java.util.Scanner;

/**
 * 招商银行第三题
 * <p>
 * 小猫吃猫粮问题
 *
 * 其实就是最小值一定不小于 数据的总和/时间
 *
 */
public class zhaoshangBank3 {

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int H = scanner.nextInt();
        scanner.close();
        String[] split = line.split(" ");
        int[] nums = new int[split.length];
        int sum = 0, maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.valueOf(split[i]);
            sum += nums[i];
            maxValue = maxValue > nums[i] ? maxValue : nums[i];
        }

        int min = sum / H + (sum % H != 0 ? 1 : 0);
        int k = min;
        for (; k <= maxValue; k++) {
            //统计相应的时间是否小于等于 H
            int countH = 0;
            for (int i = 0; i < nums.length; i++) {
                countH += nums[i] / k + nums[i] % k != 0 ? 1 : 0;
            }
            if (countH <= H) break;
        }
        System.out.println(k);
    }
}
