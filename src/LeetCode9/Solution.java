package LeetCode9;

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int tmp = x;
        int rev = 0;
        while (tmp != 0){
            rev = 10 * rev + tmp % 10;
            tmp /= 10;
        }
        return rev == x;

    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.isPalindrome(121));
        System.out.println(sol.isPalindrome(-121));
        System.out.println(sol.isPalindrome(10));
    }
}
