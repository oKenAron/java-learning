package LeetCode1;

import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No Two Sum Solution");
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.twoSum(new int[]{2, 7, 11, 5}, 9)));
        System.out.println(Arrays.toString(sol.twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(sol.twoSum(new int[]{3, 3}, 6)));
    }
}
