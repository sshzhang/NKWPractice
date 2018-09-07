package com.jianzhioffer.domain;

import java.util.Scanner;
import java.util.Stack;

public class kaluli_rpoblem3 {
    public static void main(String[] args) {

       /* Scanner scanner = new Scanner(System.in) ;
        int m = scanner.nextInt() ;
        int n = scanner.nextInt() ;
        int[][] map = new int[m][n] ;
        for(int i = 0 ; i < m ; ++ i){
            for(int j = 0 ; j < n ; ++ j){
                map[i][j] = scanner.nextInt() ;
            }
        }

        for(int i = 0 ; i < m ; ++ i){
            int sum = 0 ;
            for(int j = 0 ; j < n ; ++ j){
                if(map[i][j] == 0){
                    sum = 0 ;
                }
                sum += map[i][j] ;
                map[i][j] = sum ;
            }
        }

        int ans = 0 ;
        for(int i = 0 ; i < n ; ++ i){
            int left = -1 ;
            for(int j = 0 ; j < m ; ++ j){
                if(map[j][i] == 0){
                    left = -1 ;
                }
                else{
                    left = left == -1 ? j : left ;
                    int min = map[j][i] ;
                    for(int k = j ; k >= left ; -- k){
                        min = min > map[k][i] ? map[k][i] : min ;
                        int sum = min * (j - k + 1) ;
                        ans = ans < sum ? sum : ans ;
                    }
                }
            }
        }
        System.out.println(ans);*/

        kaluli_rpoblem3 kaluli3= new kaluli_rpoblem3();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        if(n==0||m==0) return;
        int[][] params = new int[n][m];
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                params[i][j] = scanner.nextInt();
            }
        }
        int subadd = 0;
        //从每一行开始统计相应的下面的1的个数 相当于每行是一个直方图， 问题转换为求直方图的面积问题
        for (int j = 0; j < m; j++) {
            subadd = 0;
            for (int i = n - 1; i >= 0; i--) {

                if(params[i][j]==0) subadd = 0;
                params[i][j] += subadd;
                if(params[i][j]!=0) subadd++;
            }
        }
        int max = 0;
        //调用单调栈
        for (int i=0;i<n;i++) {
            max = max> kaluli3.diandiaoStack(params[i])?max:kaluli3.diandiaoStack(params[i]);
        }
        System.out.println(max);
    }


    /**
     * 单调栈问题
     * 2 1 4 5 1  3 3
     *
     * 1.如果当前元素比栈顶元素大或者栈为空 ，则直接压栈(x,1)
     *
     * 2. 如果当前元素小于等于栈顶元素，则退栈，直到 当前元素大于栈顶元素或者栈为空时，压栈
     *
     * 3退栈过程中要进行最大面积和累积宽度的更新
     *
     */
    public int diandiaoStack(int [] params) {

        Stack<Mode> stk = new Stack<Mode>();
        int max = 0, m = 0;
        int temp;
        for (int i=0;i<params.length;i++) {
            temp = 0;
            if (stk.isEmpty()) {
                stk.push(new Mode(params[i], 1));
            } else if (params[i] <= stk.peek().value) {//当前元素不大于栈顶元素
                while (!stk.empty() && params[i] <= stk.peek().value) {//计算面积
                    stk.peek().len += temp;
                    m = stk.peek().value * stk.peek().len;
                    if(m>max) max = m;
                    temp = stk.peek().len;//保留长度
                    stk.pop();
                }
                stk.push(new Mode(params[i], 1 + temp));

            }else
                stk.push(new Mode(params[i], 1));
        }
        temp = 0;
        while (!stk.isEmpty()) {
            stk.peek().len += temp;
            m = stk.peek().value * stk.peek().len;
            if(m>max) max = m;
            temp = stk.peek().len;
            stk.pop();
        }
        return max;
    }
    static  class   Mode{
        //取值
        int value;
        //相应的长度计数
        int len;
        Mode(int value, int len) {
            this.value = value;
            this.len = len;
        }
    }
}

