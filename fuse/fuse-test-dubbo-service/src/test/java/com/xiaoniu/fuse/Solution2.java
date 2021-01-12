package com.xiaoniu.fuse;

/**
 * Unit test for simple App.
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16));

    }

    public static boolean isPerfectSquare(int num) {
        int count = 1;
        while (num > 0) {
            num -= count;
            count += 2;
        }
        return num == 0;
    }
}
