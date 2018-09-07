package com.jianzhioffer.domain;

/**
 * 跳台阶
 */
public class tiaotaijie {

    public int JumpFloor(int target) {

        /**
         * 简单 跳台阶
         */
        /*if(target==1||target==2) return target;
        return JumpFloor(target - 1) + JumpFloor(target - 2);*/


        /**
         * 变态跳台阶
         */

        if(target==1) return 1;
        return JumpFloor(target-1)*2;

    }




}
