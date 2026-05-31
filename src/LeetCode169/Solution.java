package LeetCode169;

import java.util.HashMap;

class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, currentBoss = 0;
        for (int num : nums) {
            if (count == 0) currentBoss = num;
            if (num == currentBoss) count++;
            else count--;
        }
        return currentBoss;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.majorityElement(new int[]{3, 2, 3}));
        System.out.println(sol.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }
}