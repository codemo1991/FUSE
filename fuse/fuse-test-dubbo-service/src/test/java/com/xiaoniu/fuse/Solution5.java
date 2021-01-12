package com.xiaoniu.fuse;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/4 11:44
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Solution5 {

    public static void main(String[] args) {

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public ListNode detectCycle(ListNode head) {

            ListNode slow = head;
            ListNode fast = head;
            while (slow != null && fast.next != null && slow.val != fast.val) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow.val == fast.val) {
                    fast = head;
                    while (fast.val != slow.val) {
                        fast = fast.next;
                        slow = slow.next;
                    }
                    return slow;
                }
            }
            return null;
        }
    }
}
