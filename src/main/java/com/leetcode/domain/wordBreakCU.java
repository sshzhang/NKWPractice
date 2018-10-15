package com.leetcode.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode 动态规划Word Break II
 */
public class wordBreakCU {


    public List<String> wordBreak(String s, List<String> wordDict) {

        return DFS(s, wordDict, new HashMap<>());
    }


    public List<String> DFS(String s, List<String> wordDict, HashMap<String, List<String>> map) {

        if (map.containsKey(s)) return map.get(s);

        List<String> results = new ArrayList<>();

        if (s.length() == 0) {
            results.add("");
            return results;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> datas = DFS(s.substring(word.length()), wordDict, map);
                for (String data : datas) {
                    results.add(word + (data.isEmpty() ? "" : " ") + data);
                }
            }

        }
        map.put(s, results);

        return results;
    }


    TriNode root = new TriNode();

    public List<String> wordBreakU(String s, List<String> wordDict) {

        //构造字典树

        TriNode currNode;
        for (String word : wordDict) {
            currNode = root;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                currNode.add(chars[i]);
                currNode = currNode.next[chars[i] - 'a'];
            }
            currNode.isWord = true;
        }


        boolean[] flages = new boolean[s.length() + 1];
        flages[0] = true;
        char[] chars = s.toCharArray();
        //优化
        for (int i = 0; i < chars.length; i++) {
            // 如果此节点开始的单词 不可达
            if (!flages[i]) continue;
            currNode = root;
            for (int j = i; j < chars.length && currNode != null; j++) {
                currNode = currNode.next[chars[j] - 'a'];
                // 如果此节点正好是一个单词
                if (currNode != null && currNode.isWord) flages[j + 1] = true;
            }
        }


        List<String> results = new ArrayList<>();
        if(!flages[flages.length]) return results;
        find(chars, 0, new int[chars.length], 0, results);
        return results;
    }

    private void find(char[] datas, int form, int[] split, int splits, List<String> results) {

        if (form == datas.length) {
            int splitPosi = 0, wordPosi = 0;
            char[] words = new char[datas.length + splits - 1];
            for (int i = 0; i < datas.length; i++) {
                if (i == split[splitPosi]) {
                    words[wordPosi++] = ' ';
                    splitPosi++;
                }
                words[wordPosi++] = datas[i];
            }
            results.add(new String(words));
        } else {

            TriNode currNode = root;
            for (int i = form; i < datas.length; i++) {
                currNode = currNode.next[datas[i] - 'a'];
                // 没有单词 不要继续搜索
                if (currNode == null) break;
                if (currNode.isWord) {
                    split[splits] = i + 1;
                    find(datas, i + 1, split, splits + 1, results);
                }
            }
        }


    }


    //字典树的结构
    class TriNode {
        boolean isWord;
        TriNode[] next = new TriNode[26];

        TriNode add(char c) {
            int index = c - 'a';
            if (next[index] != null) return next[index];
            next[index] = new TriNode();
            return next[index];
        }


    }


}
