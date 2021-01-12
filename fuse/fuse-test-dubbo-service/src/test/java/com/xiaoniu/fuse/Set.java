package com.xiaoniu.fuse;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2018/3/7 14:12
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Set {

    public static void main(String[] args) {
        RangeSet<Integer> set = TreeRangeSet.create();
        set.add(Range.open(1, 10));
        System.out.println();
    }
}
