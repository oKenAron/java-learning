package LeetCode26;

import java.util.Arrays;

class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int left = 0;
        for (int right = 1; right < len; right++) {
            if (nums[left] != nums[right]) nums[++left] = nums[right];
        }
        return left + 1;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        int[] testCaseA = new int[]{1,1,2};
        int[] testCaseB = new int[]{0,0,1,1,1,2,2,3,3,4};

        System.out.println(sol.removeDuplicates(testCaseA));
        System.out.println(Arrays.toString(testCaseA));
        System.out.println(sol.removeDuplicates(testCaseB));
        System.out.println(Arrays.toString(testCaseB));

    }
}
