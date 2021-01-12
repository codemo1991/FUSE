package com.xiaoniu.fuse;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/4 11:44
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Solution8 {

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{3, 4, 1, -1, -3, 5}));
    }


//    public static int firstMissingPositive(int[] A) {
//        int i = 0;
//        while (i < A.length) {
//            if (A[i] == i + 1 || A[i] <= 0 || A[i] > A.length) i++;
//            else if (A[A[i] - 1] != A[i]) swap(A, i, A[i] - 1);
//            else i++;
//        }
//        i = 0;
//        while (i < A.length && A[i] == i + 1) i++;
//        return i + 1;
//    }
//
//    private static void swap(int[] A, int i, int j) {
//        int temp = A[i];
//        A[i] = A[j];
//        A[j] = temp;
//    }


    public static int firstMissingPositive(int[] A) {
        int n = A.length;
        for (int i = 0; i < A.length; i++)
            while ((A[i] > 0 && A[i] <= n && A[A[i] - 1] != A[i]))
                swap(A, i, A[i] - 1);

        int i = 0;
        for (; i < A.length; i++)
            if (A[i] != i + 1) break;

        return i + 1;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}
