package com.CollectionCodeAnalysisSeven;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String... args) {

        //　是否为空
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("test", "value");
        stringStringHashMap.put("test0", "value2");
        for (int i = 1; i <= 10; i++) {
            stringStringHashMap.put("test" + i, "value" + i);
        }
        System.out.println(stringStringHashMap.size());
    }
}
