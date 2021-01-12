package com.xiaoniu.fuse;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2018/1/31 14:11
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 * <p>
 * /**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Solution10 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;

            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode n1 = new ListNode(6);
        ListNode n2 = new ListNode(5);
        l1.next = n1;
        n1.next = n2;
        ListNode l2 = new ListNode(5);
        ListNode x1 = new ListNode(4);
        ListNode x2 = new ListNode(3);
        l2.next = x1;
        x1.next = x2;

        ListNode rlt = addTwoNumbers(l1, l2);

        System.out.println(rlt.val);
        System.out.println(rlt.next.val);
        System.out.println(rlt.next.next.val);


    }
}
