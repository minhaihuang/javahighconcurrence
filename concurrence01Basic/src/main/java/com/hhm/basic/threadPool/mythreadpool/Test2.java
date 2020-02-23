package com.hhm.basic.threadPool.mythreadpool;

import java.util.*;

/**
 * create by huanghaimin
 */
public class Test2 {
    public static void main(String[] args) {
        Map<String,Object> map1 = new HashMap<>();
        map1.put("1",1);
        map1.remove("1");

        Map<String,Object> hashTable = new Hashtable<>();
        hashTable.put("1",1);
        hashTable.remove("1");

        Map<String,Object> linkHashMap = new LinkedHashMap<>();
        linkHashMap.put("1","1");
        linkHashMap.remove("1");

        Map<String,Object> treeMap = new TreeMap<>();


        Set set1 = new HashSet();
        set1.add(1);
    }
}
