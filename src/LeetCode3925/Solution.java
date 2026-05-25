package LeetCode3925;

import java.util.Arrays;

class Solution {
    public int[] concatWithReverse(int[] nums) {
        // 提取变量 nums.length
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++){
            ans[i] = ans[2 * n - 1 - i] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.concatWithReverse(new int[]{1,2,3})));
        System.out.println(Arrays.toString(sol.concatWithReverse(new int[]{1})));
    }
}
