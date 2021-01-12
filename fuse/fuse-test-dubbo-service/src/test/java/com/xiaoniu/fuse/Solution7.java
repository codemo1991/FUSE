package com.xiaoniu.fuse;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/4 11:44
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Solution7 {

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{1, 0, 5, 4, 3}));
    }

    public static int missingNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (nums.length * (nums.length + 1)) / 2 - sum;
    }

}
