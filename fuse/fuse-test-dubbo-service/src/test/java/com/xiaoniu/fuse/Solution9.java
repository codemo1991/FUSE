package com.xiaoniu.fuse;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/4 11:44
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Solution9 {

    public static void main(String[] args) {
        System.out.println(removeKdigits2("9676573", 6));
    }

//    public static String removeKdigits(String num, int k) {
//        char[] c = num.toCharArray();
//        Deque<Character> deque = new LinkedList<>();
//        for (int i = 0; i < c.length; i++) {
//            while (k > 0 && !deque.isEmpty() && deque.peekLast() > c[i]) {
//                deque.pollLast();
//                k--;
//            }
//            deque.addLast(c[i]);
//        }
//
//        while (k > 0) {
//            deque.pollLast();
//            k--;
//        }
//        String res = "";
//        boolean startWith0 = true;
//        while (deque.size() > 0) {
//            if (deque.peekFirst() == '0' && startWith0)
//                deque.pollFirst();
//            else {
//                res += deque.pollFirst();
//                startWith0 = false;
//            }
//
//        }
//
//        return res == "" ? "0" : res;
//    }


    public static String removeKdigits2(String num, int k) {
        if (k == 0) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }
        char[] content = num.toCharArray();
        char[] newContent = new char[num.length()];
        int len = num.length();
        newContent[0] = content[0];
        int newIndex = 1;
        for (int i = 1; i <= len - 1; i++) {
            // current number to point and compare with newIndex
            char ch = content[i];
            // begin to compare with the value with the current newIndex point at
            while (k > 0 && newIndex >= 1 && ch < newContent[newIndex - 1]) {
                k--;
                newIndex--;
            }
            newContent[newIndex] = ch;
            newIndex++;
            if (k == 0) {
                i++;
                System.arraycopy(content, i, newContent, newIndex, len - i);
                newIndex += (len - i);
                break;

            }
        }
        int startIndex = 0;
        while (newContent[startIndex] == '0') {
            startIndex++;
        }
        if (newIndex - startIndex - k == 0) {
            return "0";
        }
        return new String(newContent, startIndex, newIndex - startIndex - k);


    }
}