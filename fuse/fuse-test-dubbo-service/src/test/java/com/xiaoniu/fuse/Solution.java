package com.xiaoniu.fuse;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Unit test for simple App.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{189, 11, 7, 19, 8, 45, 22, 11}, 2)));
    }

    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
    public static int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            //remove smaller numbers in k range as
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();

            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }
}
