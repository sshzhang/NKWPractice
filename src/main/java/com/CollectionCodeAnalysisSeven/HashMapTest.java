package com.CollectionCodeAnalysisSeven;

import java.util.HashMap;
import java.util.Hashtable;

public class HashMapTest {

    public static void main(String... args) {

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put(null, null);

        Hashtable<String, String> stringStringHashtable = new Hashtable<>();
        stringStringHashtable.put(null, "12");

    }
}
