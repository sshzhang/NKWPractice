package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class findWords212 {


    public List<String> findWords(char[][] board, String[] words) {

        List<String> lists = new ArrayList<>();
        if(words==null||words.length==0) return lists;
        //构建字典树
        TreeNode treeNode = BuildTreeNode(words);
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                DFSU(lists, board, treeNode, i, j, visited);
                }
            }
        Collections.sort(lists);
        return lists;
        }


    private void DFSU(List<String> lists, char[][] board, TreeNode treeNode, int i, int j, boolean[][] visited) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length || visited[i][j] || treeNode.next[board[i][j] - 'a'] == null) {
            return ;
        }

        treeNode = treeNode.next[board[i][j] - 'a'];
        if (treeNode.word != null) {
            lists.add(treeNode.word);
            treeNode.word = null;
        }
        visited[i][j] = true;
        DFSU(lists, board, treeNode, i + 1, j, visited);
        DFSU(lists, board, treeNode, i - 1, j, visited);
        DFSU(lists, board, treeNode, i, j + 1, visited);
        DFSU(lists, board, treeNode, i, j - 1, visited);
        visited[i][j] = false;
        return;
    }

    private TreeNode  BuildTreeNode(String[] words) {
        TreeNode nhead = new TreeNode();
        TreeNode head = nhead;
        for (String word : words) {
            nhead = head;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if(nhead.next[c-'a']==null) nhead.next[c - 'a'] = new TreeNode();
                nhead = nhead.next[c - 'a'];
            }
            nhead.word = word;
        }
        return head;
    }


    class  TreeNode{
        //总共有26个字母, 26总方式
        TreeNode next[] = new TreeNode[26];
        //如果是单词的结尾  保存对应的单词， 否则 为null
        String word;

        public void setWord(String word) {
            this.word = word;
        }
    }
    public static void main(String... args) {


        findWords212 ob = new findWords212();
        char[][] chars = new char[1][2];
        chars[0] = new char[]{'a','b'};
        String[] strings = new String[1];
        strings[0] = "ba";
        List<String> words = ob.findWords(chars, strings);
        System.out.println(words);
//        chars[1] = new char[]{'A', 'A','A','A'};
//        chars[2] = new char[]{'A', 'A','A','A'};
    }




}