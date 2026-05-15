package LeetCode3110;

class Solution {
    public int scoreOfString(String s) {
        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int diff = s.charAt(i) - s.charAt(i + 1);
            if (diff < 0) diff = -diff;
            ans += diff;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.scoreOfString("hello"));
        System.out.println(sol.scoreOfString("zaz"));
    }
}