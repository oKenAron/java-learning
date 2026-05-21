package LeetCode3925;

import java.util.Arrays;

class Solution {
    public int[] concatWithReverse(int[] nums) {
        int[] ans = new int[2 * nums.length];
        for (int i = 0; i < nums.length; i++){
            ans[i] = ans[2 * nums.length - 1 - i] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.concatWithReverse(new int[]{1,2,3})));
        System.out.println(Arrays.toString(sol.concatWithReverse(new int[]{1})));
    }
}
