package com.leetcode.domain;

/**
 * 最长回文子串问题 leetcode 5
 */
public class TheLongestParindromicSubstr5 {


    /**
     * 暴力方法
     * 假设flage[i][j]表示以i开始，以j结束的回文串的长度。
     * 计算出所有以i开始以j结束回文串的长度大小，不是回文串的设置为0
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {

        // a[i][j] 表示以i开始， 以j结束的串， 如果是回文串保存长度，不是直接为0
        int max = 1, x = 0, y = 0;
        int len = s.length();
        int[][] flage = new int[len][len];
        for (int i = 0; i < len; i++) flage[i][i] = 1;
        for (int i = 0; i < len; i++) {

            for (int j = i + 1; j < len; j++) {
                flage[i][j] = TheLengthOfStr(s.substring(i, j + 1));

                if (max < flage[i][j]) {
                    max = flage[i][j];
                    x = i;
                    y = j;
                }
            }
        }
        return s.substring(x, y + 1);
    }

    public int TheLengthOfStr(String subStr) {

        int len = subStr.length();

        int i = 0, j = len - 1;

        while (i <= j) {

            if (subStr.charAt(i) == subStr.charAt(j)) {
                i++;
                j--;
            } else {
                return 0;
            }
        }

        return len % 2 == 0 ? i * 2 : i * 2 - 1;
    }


    /**
     * 动态规划,本质上是暴力方法的改进版本，它利用数组P[i][j]保存Si到Sj的字符串是否是回文串,
     * P[i][j]={P[i+1][j-1]&&Si==Sj}, 根据定义有P[i][i]=1, P[i][i+1]=Si==S(i+1)
     * 先从一个元素的回文串，再两个元素，接着三个元素......
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        // 先初始化 一位
        char[] cSubstr = s.toCharArray();
        int maxValue = 0;
        int low = 0, high = 0;
        int len = s.length();
        boolean[][] P = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            P[i][i] = true;

        }
        // 从3个元素到k个元素  P[j-k-1][j-1]
        boolean isFlage = false;
        for (int k = 1; k < len; k++) {
            isFlage = false;
            for (int j = k; j < len; j++) {
                P[j - k][j] = cSubstr[j - k] == cSubstr[j]&&(k==1||P[j-k+1][j-1]);
                if (P[j - k][j]&&!isFlage) {
                    maxValue = k;
                    low = j - k;
                    high = j;
                    isFlage = true;
                }
            }
        }
        return s.substring(low, high + 1);
    }


    /**
     * 中心扩展法
     * 由于回文串一定是关于中心对称的，因此我们只要确定回文串的中心位置，就能够判断回文串的长度大小。
     * 在一个长度为n的字符串中回文串的中心有2*n-1中可能，其中n-1中表示选取两个元素i,i+1为对称中心的情况数，
     * n表示选取一个元素为对称中心的情况数
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        int len = s.length();
        if(len<=1) return s;
        int start = 0, end = 0;
        for (int i = 0; i < len; i++) {
            int len1 = theLengthOfSubStr(s, i, i, len);
            int len2 = theLengthOfSubStr(s, i, i + 1, len);
            int le = Math.max(len1, len2);
            if (le > start - end) {
                start = i - (le-1 )/ 2;
                end = i + le / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int theLengthOfSubStr(String s, int left, int right, int len) {
        int L = left, R = right;
        while (L >= 0 && R < len && s.charAt(L) == s.charAt(R)) {
            R++;
            L--;
        }
        return R - L - 1;
    }


    /**
     * 利用马拉车方法Menacher
     *
     * @param s 目标字符串
     * @return
     */
    public String longestPalindrome4(String s) {

        int len = s.length();
        if(len<=1) return s;
        len = 2*len + 3;
        char cArray[] = new char[len];
        cArray[0] = '$';
        cArray[1] = '#';
        cArray[len-1] = '\n';
        for (int i = 2, j=0; i <len-1; i=i+2) {
            cArray[i]=s.charAt(j++);
            cArray[i + 1] = '#';
        }
        // p[i]保存以点cArray[i]为对称中心的最大半径长度
        int[] p = new int[len];
        // 表示当前最大半径的中心所在位置， mx当前最大半径所在的有边界, maxLen最大的回文串长度
        int id=0, mx = 0, maxLen = -1, medium=-1;
        for (int i = 1; i < len-1; i++) {
            if(i<mx){//可以借助对称性来求p[i]
                p[i] = Math.min(p[2 * id - i], mx - i);
            }else
                p[i] = 1; // 长度为1

            while (cArray[i - p[i]] == cArray[i + p[i]]) {
                p[i]++;
            }
            // 更新当前最远右边界
            if (i + p[i] > mx) {
                mx = i + p[i];
                id = i;
            }
            System.out.println("i = " + i + "  p[i] = " + p[i] + "");
             // p[i] - 1 当前以i为中心的回文串的长度(去掉#)
            if (p[i] - 1 > maxLen) {
                maxLen = p[i] - 1;
                medium = i - maxLen;
            }

        }

        medium = medium / 2;

        return s.substring(medium, medium + maxLen);
    }


    public static void main(String... args) {
        System.out.println('\0');
        System.out.println(new TheLongestParindromicSubstr5().longestPalindrome4("babad"));

    }
}
