package com.xiaoniu.fuse;

/**
 * Unit test for simple App.
 */
public class Solution4 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 1};
        System.out.println(findDuplicate(nums));
    }

    /**
     * @return: The maximum number inside the window at each moving.
     */

//    public static int findDuplicate(int[] nums) {
//        BitSet set = new BitSet(nums.length);
//        for (int i : nums) {
//            if (set.get(i))
//                return i;
//            set.set(i, true);
//        }
//        return -1;
//    }
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return -1;
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
