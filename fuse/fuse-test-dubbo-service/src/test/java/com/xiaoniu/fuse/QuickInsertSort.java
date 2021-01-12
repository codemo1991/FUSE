package com.xiaoniu.fuse;

import java.util.Arrays;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/30 11:21
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class QuickInsertSort {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort(new int[]{2, 3, 4, 21, 12, 3, 5, 78, 1})));
    }

    public static int[] sort(int[] source) {

        for (int i = 1; i < source.length; i++) {
            if (source[i] < source[i - 1]) {
                int temp = source[i];
                source[i] = source[i - 1];
                int j = i - 1;
                while (j > 0 && temp < source[j - 1]) {
                    source[j] = source[j - 1];
                    j--;
                }
                source[j] = temp;
            }
        }
        return source;
    }

}
