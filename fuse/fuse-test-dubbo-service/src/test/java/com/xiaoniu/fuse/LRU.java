package com.xiaoniu.fuse;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/12/6 11:54
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class LRU {

    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);
        c.put(1, 1);
        c.put(2, 2);
        c.get(1);
        c.put(3, 3);
        System.out.println(c.get(2));
        System.out.println(c.get(1));
    }


}

class LRUCache {

    final LinkedHashMap<Integer, Integer> lruMap;

    public LRUCache(final int capacity) {
        lruMap = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (lruMap.containsKey(key))
            return lruMap.get(key);
        return -1;
    }

    public void put(int key, int value) {
        lruMap.remove(key);
        lruMap.put(key, value);
    }
}