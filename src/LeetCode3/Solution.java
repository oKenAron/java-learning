package LeetCode3;

import java.util.ArrayList;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 防御性编程(此为提交前AI审查发现的疏漏)
        if (s == null || s.isEmpty()) return 0;

        // 语法糖错误用法
        // int max = int sign = 1;
        // 正确用法如下
        int max = 1, sign = 1;
        ArrayList<Character> tmp = new ArrayList<>();
        tmp.add(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < tmp.size(); j++) {
                if (tmp.get(j) == s.charAt(i)) {
                    for (int k = 0; k <= j; k++) {
                        sign -= 1;
                        // 这个会报警
                        tmp.remove(0);
                    }
                    break;
                }
            }
            sign += 1;
            tmp.add(s.charAt(i));
            if (sign > max) max = sign;
        }
        return max;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));
    }
}