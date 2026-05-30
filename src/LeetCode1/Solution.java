package LeetCode1;

import java.util.Arrays;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
           for (int j = i + 1; j < len; j++){
               if (nums[i] + nums[j] == target){
                   return new int[]{i, j};
               }
           }
        }
        throw new IllegalArgumentException("No Two Sum Solution");
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.twoSum(new int[]{2,7,11,5}, 9)));
        System.out.println(Arrays.toString(sol.twoSum(new int[]{3,2,4}, 6)));
        System.out.println(Arrays.toString(sol.twoSum(new int[]{3,3}, 6)));
    }
}
