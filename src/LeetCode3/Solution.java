package LeetCode3;

import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;

        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int max = 0;
        int len = s.length();
        for (int right = 0; right < len; right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            set.add(s.charAt(right));
            // max = Math.max(max, right - left + 1);
            if (max < right - left + 1) max = right - left + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));
        System.out.println(sol.lengthOfLongestSubstring(""));
        System.out.println(sol.lengthOfLongestSubstring(null));
    }
}