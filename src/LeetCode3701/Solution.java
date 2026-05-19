package LeetCode3701;

class Solution {
    public int alternatingSum(int[] nums) {
        int ans = 0, sign = 1;
        for (int num: nums) {
            ans += num * sign;
            sign = -sign;
        }
        return ans;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.alternatingSum(new int[]{1,3,5,7}));
        System.out.println(sol.alternatingSum(new int[]{100}));
    }
}