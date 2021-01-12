package com.xiaoniu.fuse;

/**
 * Unit test for simple App.
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{5, 4, 5, 5}));

//        System.out.println(5 >> 0);
    }


//    public static int singleNumber(int[] A) {
//        int ones = 0;//记录只出现过1次的bits
//        int twos = 0;//记录只出现过2次的bits
//        int threes;
//        for (int i = 0; i < A.length; i++) {
//            int t = A[i];
//            twos |= ones & t;//要在更新ones前面更新twos
//            ones ^= t;
//            threes = ones & twos;//ones和twos中都为1即出现了3次
//            ones &= ~threes;//抹去出现了3次的bits
//            twos &= ~threes;
//        }
//        return ones;
//}

//    public static int singleNumber(int[] nums) {
//        int one = 0;
//        int two = 0;
//
//        for (int i = 0; i < nums.length; i++) {
//            one = (~two) & (one ^ nums[i]);
//            two = (~one) & (two ^ nums[i]);
//        }
//        return one;
//    }

    public static int singleNumber(int[] nums) {
        int x1 = 0, x2 = 0, mask = 0;

        for (int i : nums) {
            x2 ^= x1 & i;
            x1 ^= i;
            mask = ~(x1 & x2);
            x2 &= mask;
            x1 &= mask;
        }

        return x1;
    }


//    public static int singleNumber(int[] nums) {
//
//        int res = 0;
//        for (int i = 0; i < 32; i++) {
//            int sum = 0;
//
//            for (int j = 0; j < nums.length; j++) {
//                sum += (nums[j] >> i) & 1;
//            }
//            res |= (sum % 3) << i;
//
//        }
//        return res;
//    }


//    public static int singleNumber(int[] A) {
//        if (A == null || A.length == 0) {
//            return -1;
//        }
//        //得到出现一次的数字的值】
//        int result = 0;
//        //int为4个字节，那么一共有4*8=32位
//        for (int i = 0; i < 32; i++) {
//            //保存每一位求和值
//            int sum = 0;
//            for (int j = 0; j < A.length; j++) {
//                //累加所有数字上第i位上的数字
//                sum += (A[j] >> i) & 1;
//            }
//            //取余得到第i位上的数字，之后更新result
//            result |= (sum % 3) << i;
//        }
//        return result;
//    }
}
