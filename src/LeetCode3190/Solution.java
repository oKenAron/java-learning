package LeetCode3190;

class Solution {
    public int minimumOperations(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 3 != 0) ans += 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.minimumOperations(new int[]{1, 2, 3, 4}));
        System.out.println(sol.minimumOperations(new int[]{3, 6, 9}));
    }
}