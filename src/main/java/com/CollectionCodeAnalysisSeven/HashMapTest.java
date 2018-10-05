package com.CollectionCodeAnalysisSeven;

import java.util.HashMap;
import java.util.Stack;

public class HashMapTest {

    public static void main(String... args) {

        //　是否为空
        /*HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put(null, null);

        Hashtable<String, String> stringStringHashtable = new Hashtable<>();
        stringStringHashtable.put(null, "12");*/


        HashMap<String, String> firstMap = new HashMap<>();
        firstMap.put("12", "12");
        firstMap.put("13", "13");
        HashMap<String, String> secondMap = new HashMap<>(firstMap);
        System.out.println(secondMap.get("12"));

        System.out.println(3&1569);//1
        Stack<Integer>[] ss = new Stack[2];

    }
}
