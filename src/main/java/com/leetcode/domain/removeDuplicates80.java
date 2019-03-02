package com.leetcode.domain;

public class removeDuplicates80 {

    public int removeDuplicates(int[] nums) {

        //只有两个元素  返回2
        if(nums.length<=2) return nums.length;
        int count = 1;
        for (int k = 1,j=0; k < nums.length; k++) {
            //k 表示正常的便利元素序列位置， j表示新的元素序列位置
            if (nums[k] == nums[j]) {
                if(count<=1){//没满两次
                    count++;
                    nums[++j] = nums[k];
                }else{//多余两次
                    if (nums[k] == nums[j]) {
                        if(k==nums.length-1) count = j + 1;
                        continue;
                    }

                }
            }else{//不相等
                nums[++j] = nums[k];
                count = 1;
            }
            if(k==nums.length-1) count = j + 1;
        }
        return count;
    }

    public static void main(String... args) {
        removeDuplicates80 duplicates = new removeDuplicates80();
        int[] ints = {1,1,1};
        int i = duplicates.removeDuplicates(ints);
        System.out.println(i);
        System.out.println(ints);
    }


}
