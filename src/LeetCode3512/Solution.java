package LeetCode3512;

class Solution {
    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for (int num:nums) sum += num;
        return sum % k;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.minOperations(new int[]{3,9,7},5));
        System.out.println(sol.minOperations(new int[]{4,1,3},4));
        System.out.println(sol.minOperations(new int[]{3,2},6));
    }
}
