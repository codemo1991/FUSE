package com.xiaoniu.fuse;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/21 14:20
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class HashFunctionTest {

    public static void main(String[] args) {
        HashFunction hf = Hashing.murmur3_128();
        System.out.println(hf.hashString("123", Charsets.UTF_8).asInt());


    }
}
