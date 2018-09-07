package com.jianzhioffer.domain;

/**
 * 二叉搜索树的后序遍历序列
 * <p>
 * 二叉搜索树 也叫二叉排序树  当存在左节点时 所有的左节点值小于根节点值 ，
 * 当存在右节点时 所有右节点值大于根节点的值
 *
 * 注意点
 *  //找不到元素大于  2 4 6 7 9 89 递增序列  是二叉搜索树的后序遍历序列
 */
public class BinarySearchTreeSequence {


    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence==null||sequence.length==0) return false;
        return SplitTreeDepth(0, sequence.length, sequence);
    }

    public boolean SplitTreeDepth(int min, int max, int[] sequence) {

        //找出最后一个元素，在这些元素的位置  其实元素的位置 在 [min,max-1]
        // 剩下最后一个元素  直接返回true
        if (min >= max - 1) return true;
        int resultNum = sequence[max - 1];
        int resultPosition = -1;
        for (int i = min; i < max - 1; i++) {
            if (resultNum < sequence[i]) {
                resultPosition = i;
                break;
            }
        }
        //找不到元素大于  2 4 6 7 9 89 递增序列  是二叉搜索树的后序遍历序列
        if(resultPosition==-1) return true;
        boolean flages = true;
        for (int i = resultPosition + 1; i < max - 1; i++) {
            if (sequence[i] <= resultNum) {
                flages = false;
                break;
            }
        }
        if (!flages) {
            return false;
        }
        return SplitTreeDepth(min, resultPosition, sequence) && SplitTreeDepth(resultPosition, max - 1, sequence);
    }


    public static void main(String... args) {

        System.out.println(new BinarySearchTreeSequence().VerifySquenceOfBST(new int[]{7,4,6,5}));
    }

}
