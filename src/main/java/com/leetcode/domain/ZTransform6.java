package com.leetcode.domain;

public class ZTransform6 {

    public String convert1(String s, int numRows) {


        StringBuilder builderStr = new StringBuilder();
        int len = s.length();
        if (numRows == 1) return s;
        else if (numRows == 2) {
            int colNum = len / numRows + (len % numRows == 0 ? 0 : 1);
            String[][] value = new String[numRows][colNum];

            for (int i = 0, j = 0; i < len; i++) {
                value[j % numRows][j / numRows] = s.charAt(i) + "";
                j++;
            }
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < colNum; j++) {
                    builderStr.append(value[i][j]!=null?value[i][j]:"");
                }
            }

            return builderStr.toString();
        } else {
            // 每一条篇完整记录的个数
            int esumSize = 2 * numRows - 2;
            int colNum = (len / esumSize + (len % esumSize == 0 ? 0 : 1) )* (numRows-1);
            String[][] value = new String[numRows][colNum];
            for (int i = 0, j = 0, jc = 0; i < len; ) {

                while (j < numRows && i < len) {
                    value[j++][jc] = s.charAt(i++) + "";
                }
                j = j % numRows;

                int t = 0; // 遍历另外数据
                while (i < len && t < numRows - 2) {
                    value[numRows - 2 - t][jc + 1 + t] = s.charAt(i++) + "";
                    t++;
                }
                jc = jc + numRows - 1;
            }

            for (int i = 0; i < numRows; i++) {

                for (int j = 0; j < colNum; j++) {

                    if (value[i][j] != null) {
                        builderStr.append(value[i][j]);
                    }
                }
            }

            return builderStr.toString();
        }

    }

    public static void main(String... args) {

        System.out.println(new ZTransform6().convert2("LEETCODEISHIRING", 3));

    }

    /**
     * 找规律
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        StringBuilder builderStr = new StringBuilder();

        int len=s.length();
        if(numRows==1) return s;
        /*
        *  0,   6,     12    每个一次增加 2RowNums-2==6个元素

         1,    5,    7,   11,    13       假设当前位置为i, 此处表示i=1,  (RowNums-1-i)*2,   i*2

         2,    4,    8,     10,   14

          3,       9,    15      每个一次增加 2RowNums-2==6个元素

        *
        */

        for(int k=0;k<len;k+=2*numRows-2){
            builderStr.append(s.charAt(k)+"");
        }

        for(int i=1, j;i<numRows-1;i++){

            j=i;
            int c=0;
            while(j<len){
                builderStr.append(s.charAt(j));
                if(c%2==0) j+=(numRows-1-i)*2;
                else j+=i*2;
                c++;
            }
        }

        for(int k=numRows-1;k<len;k+=2*numRows-2){
            builderStr.append(s.charAt(k));
        }

        return builderStr.toString();
    }
}
