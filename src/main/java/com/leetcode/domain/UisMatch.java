package com.leetcode.domain;

public class UisMatch {


    public boolean isMatch(String s, String p) {


        if(s==null||p==null) return false;
        char[] strs = s.toCharArray();
        char[] pattrs = p.toCharArray();


        return MatchReclusive(strs, 0, pattrs, 0);

    }

    private boolean MatchReclusive(char[] strs, int strIndex, char[] pattrs, int patIndex) {

        if (strIndex == strs.length && patIndex == pattrs.length) {
            return true;
        }

        if (strIndex <= strs.length && patIndex == pattrs.length) {
            return false;
        }


        if ( patIndex + 1 < pattrs.length && pattrs[patIndex + 1] == '*') {
            //如果匹配
            if ( strIndex < strs.length && (pattrs[patIndex] == strs[strIndex]||pattrs[patIndex]=='.')) {
                return MatchReclusive(strs, strIndex, pattrs, patIndex + 2) || MatchReclusive(strs, strIndex + 1, pattrs, patIndex) || MatchReclusive(strs, strIndex + 1, pattrs, patIndex + 2);
            }else{
                return MatchReclusive(strs, strIndex, pattrs, patIndex + 2);
            }
        }

        if ((strIndex != strs.length && strs[strIndex] == pattrs[patIndex]) || (strIndex != strs.length && pattrs[patIndex] == '.')) {

            return MatchReclusive(strs, strIndex + 1, pattrs, patIndex + 1);

        }

        return false;
    }


    public boolean isMatchU(String s, String p) {

        if (s == null || p == null) {
            return false;
        }

        //dp[i][j] 表示 s[0,...i) 是否匹配 p[0,...j)
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true; //初始情况下 不包含任何数据肯定匹配
        for (int i = 0; i < p.length(); i++) {
            //在不包含任何字符串的情况下
            if (p.charAt(i) == '*' && dp[0][i-1]) {//dp[0][2] 其实就是 p[0...1] 是否能匹配空串
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {//匹配零个字符串
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }



    public boolean isMatchUDP(String s, String p){
        if(s==null||p==null) return false;

        boolean matchs[][] = new boolean[s.length() + 1][p.length() + 1];

        matchs[0][0] = true;
        for (int j = 1; j <= p.length(); j++)
            matchs[0][j]=j>1&&p.charAt(j-1)=='*'&&matchs[0][j-2];

        for (int i = 1; i <= s.length(); i++) {
            matchs[i][0] = false;
        }

        for (int i = 1; i <= s.length(); i++) {

            for (int j = 1; j <= p.length(); j++) {


                if (p.charAt(j - 1) == '*') {

                    matchs[i][j] = matchs[i][j - 2] || (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && matchs[i - 1][j];
                }else{

                    matchs[i][j] = (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) && matchs[i - 1][j - 1];
                }

            }

        }


        return matchs[s.length()][p.length()];



       /* int m = s.length(),n = p.length();
        boolean dp[][]=new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++)
            dp[i][0] = false;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j > 1 && '*' == p.charAt(j-1) && dp[0][j - 2];

        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (p.charAt(j - 1) == '*')
                {
                    dp[i][j] = dp[i][j - 2] || (s.charAt(i - 1) ==p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j];

                }
                else
                {
                    dp[i][j] = (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1)) && dp[i - 1][j - 1];

                }
            }
        }
        return dp[m][n];*/

    }


    public static void main(String... args) {

        UisMatch uisMatch = new UisMatch();
        System.out.println(uisMatch.isMatchUDP("mississippi", "mis*is*p*."));

    }
}
