package LeetCode1929;

import java.util.Arrays;
/*
class Solution {
    public int[] getConcatenation(int[] nums) {
        int numsLength = nums.length;
        int[] ans = new int[2 * numsLength];
        for (int i = 0; i < numsLength; i++) {
            ans[i] = ans[i + numsLength] = nums[i];
        }
        return ans;
    }
*/
class Solution {
    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[2 * nums.length];
        System.arraycopy(nums,0,ans,0,nums.length);
        System.arraycopy(nums,0,ans,nums.length,nums.length);
        return ans;
    }

    public static void main(String[] args) {
        int[] expNums1 = new int[]{1, 2, 1};
        int[] expNums2 = new int[]{1, 3, 2, 1};

        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.getConcatenation(expNums1)));
        System.out.println(Arrays.toString(sol.getConcatenation(expNums2)));
    }
}
