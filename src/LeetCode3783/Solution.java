package LeetCode3783;

class Solution {
    public int mirrorDistance(int n) {
        int tmp = n;
        int rev = 0;
        while (tmp != 0) {
            rev = rev * 10 + tmp % 10;
            tmp /= 10;
        }
        return Math.abs(n - rev);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.mirrorDistance(25));
        System.out.println(sol.mirrorDistance(10));
        System.out.println(sol.mirrorDistance(7));
    }
}
