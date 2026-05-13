package LeetCode1920;

import java.util.Arrays;

class Solution {
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for(int i=0; i < nums.length; i++){
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

    public static void main(String[] args){
        int[] array = new int[]{5,0,1,2,3,4};

        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.buildArray(array)));
    }
}