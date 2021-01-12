package com.xiaoniu.fuse;

import java.util.HashSet;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/22 17:20
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class AgeTest {


    public static int lengthOfLongestSubstring(String s) {
        int[] store = new int[256];
        int maxLength = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char currentChar = s.charAt(j);
            ++store[currentChar];//记录下字符个数
            /**
             * 作用是清除重复的字符，例如 a,b,c,c,d,e,q
             * 遍历到j = 3的时候，会把a,b,c清空，然而这个时候maxLength还是记录着3，
             * 就像从第二个c重新开始统计最长字符一样，
             * 如果第二个c之后不大于3，则3就是不重复最长字符
             */
            while (store[currentChar] > 1) {
                --store[s.charAt(i++)];
            }
            maxLength = Math.max(maxLength, j - i + 1);
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s) {
        HashSet<Character> store = new HashSet<>();
        int maxLength = 0;
        for (int i = 0, j = 0; i < s.length(); ) {
            Character c = s.charAt(i);
            if (store.add(c)) {
                i++;
                maxLength = Math.max(maxLength, store.size());
            } else store.remove(s.charAt(j++));//作用是清除重复的字符
        }
        return maxLength;
    }

//    public static int lengthOfLongestSubstring(String s) {
//        int i = 0, j = 0, max = 0;
//        Set<Character> set = new HashSet<>();
//
//        while (j < s.length()) {
//            if (!set.contains(s.charAt(j))) {
//                set.add(s.charAt(j++));
//                max = Math.max(max, set.size());
//            } else {
//                set.remove(s.charAt(i++));
//            }
//        }
//
//        return max;
//    }

    public static void maxSubArray(int[] nums) {
        int maxForNow = nums[0];
        int maxPostfix = nums[0];
        int beginIndex = 0, endIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            int preMaxPostfix = maxPostfix + nums[i];
            maxPostfix = Math.max(preMaxPostfix, nums[i]);

            if (maxPostfix == nums[i]) {
                beginIndex = i;
                endIndex = i;
            } else if (maxPostfix > maxForNow) {
                endIndex = i;
            }
            maxForNow = Math.max(maxForNow, maxPostfix);
        }
        System.out.println(maxForNow);
        System.out.println(beginIndex + " " + endIndex);
    }

    public static void main(String[] args) {

        maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4, 19});
    }

}
