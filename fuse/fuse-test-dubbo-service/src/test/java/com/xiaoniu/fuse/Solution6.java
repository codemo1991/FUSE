package com.xiaoniu.fuse;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/4 11:44
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Solution6 {

    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int value = Math.abs(nums[i]) - 1;
            int target = nums[value];
            if (target > 0)
                nums[value] = -target;
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] > 0) {
                res.add(j + 1);
            }
        }
        return res;
    }
}
