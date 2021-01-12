package com.xiaoniu.fuse.test;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/10/12 19:36
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class TestMain {

    public void rotate(int[] a, int k) {
        k %= a.length;
        reverse(a, 0, a.length - 1);
        reverse(a, 0, k - 1);
        reverse(a, k, a.length - 1);
    }

    private void reverse(int[] a, int lo, int hi) {
        while (lo < hi) {
            int temp = a[lo];
            a[lo] = a[hi];
            a[hi] = temp;
            lo++;
            hi--;
        }
    }

    public static int findPeakElement(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (mid > 0 && mid < a.length - 1 && a[mid] >= a[mid - 1] && a[mid] >= a[mid + 1]) return mid;

            if (mid < a.length - 1 && a[mid] < a[mid + 1])
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 20, 3, 4, 10, 6};
//        TestMain main = new TestMain();
//        main.rotate(a, 2);
        System.out.println(findPeakElement(a));
    }
}
