package com.leetcode.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 给定字符串和单词集合  找出字符串中所有可能的单词集合
 *
 */
public class wordBreakC {

    public List<String> wordBreak(String s, List<String> wordDict) {
        return helper(s,wordDict,new HashMap<>());
    }


    /**
     * 通过DFS 深度优先遍历
     * @param s 表示当前需要匹配的字符串
     * @param wordDict  单词集合
     * @param m  保存  键表示相应的字符串  值表示其对应的所有单词集合  主要是防止重复计算
     * @return
     */
    List<String> helper(String s, List<String> wordDict, HashMap<String, List<String>> m) {
        if(m.containsKey(s)) return m.get(s);
        List<String> strings = new ArrayList<>();
        if (s == "" || "".equals(s)) {
            strings.add("");
            return strings;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> helpers = helper(s.substring(word.length()), wordDict, m);
                for (String he : helpers) {
                    strings.add(word + (he.isEmpty() ? "" : " ") + he);
                }
            }
        }
        m.put(s, strings);
        return strings;
    }


    public static void main(String... args) {
        List<String> strings = new ArrayList<>();
        strings.add("apple");
        strings.add("pen");
        strings.add("applepen");
        strings.add("pine");
        strings.add("pineapple");
        List<String> list = new wordBreakC().wordBreakU("pineapplepenapple", strings);
        System.out.println(list);
    }




    private TrieNode root;
    private void find(char[] sa, int from, int[] split, int splits, List<String> words) {
        if (from == sa.length) {
            char[] word = new char[sa.length+splits-1];
            int wordPos = 0, splitPos = 0;
            for(int i=0; i<sa.length; i++) {
                if (i==split[splitPos]) { word[wordPos++] = ' '; splitPos++; }
                word[wordPos++] = sa[i];
            }
            words.add(new String(word));
            return;
        }
        TrieNode current = root;
        for(int i=from;i<sa.length;i++) {
            current = current.nexts[sa[i]-'a'];
            if (current == null) break;
            if (current.isWord) {
                split[splits] = i+1;
                find(sa, i+1, split, splits+1, words);
            }
        }
    }
    public List<String> wordBreakU(String s, List<String> wordDict) {
        List<String> results = new ArrayList<>();
        if (s==null || wordDict==null) return results;
        root = new TrieNode();
        for(String word: wordDict) {
            char[] wa = word.toCharArray();
            TrieNode current = root;
            for(int i=0; i<wa.length; i++) current = current.add(wa[i]);
            current.isWord = true;
        }
        char[] sa = s.toCharArray();
        boolean[] reachable = new boolean[sa.length+1];
        reachable[0] = true;
        for(int i=0; i<sa.length; i++) {
            if (!reachable[i]) continue;
            TrieNode current = root;
            for(int j=i;j<sa.length && current != null;j++) {
                current = current.nexts[sa[j]-'a'];
                if (current != null && current.isWord) reachable[j+1] = true;
            }
        }
        if (!reachable[sa.length]) return results;
        find(sa, 0, new int[sa.length], 0, results);
        return results;
    }
}
class TrieNode {
    boolean isWord;
    TrieNode[] nexts = new TrieNode[26];
    TrieNode add(char ch) {
        int i = ch - 'a';
        if (nexts[i] != null) return nexts[i];
        nexts[i] = new TrieNode();
        return nexts[i];
    }
}
