package LeetCode169;

import java.util.HashMap;

class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            int currentCount = map.getOrDefault(num, 0) + 1;
            map.put(num, currentCount);
            if (currentCount > nums.length / 2){
                return num;
            }
        }
        throw new IllegalArgumentException("No majority Element");
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.majorityElement(new int[]{3,2,3}));
        System.out.println(sol.majorityElement(new int[]{2,2,1,1,1,2,2}));
    }
}