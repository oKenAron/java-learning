package LeetCode14;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        // 养成习惯日常生活 str 输入要防御
        if (strs == null || strs.length == 0) return "";
        int len = strs.length;

        String prefixString = strs[0];
        for (int i = 1; i < len; i++){
            while (!strs[i].startsWith(prefixString)){
                prefixString = prefixString.substring(0, prefixString.length() - 1);
                // 我没加这个代码跑测试还是正常过了 为啥
                // 答案: 不加语法上是没问题的, startsWith 一个空字符串永远是 true, 但既然已经空了, 后面的判断根本没必要跑了.
                if (prefixString.isEmpty()) return "";
            }
        }
        return prefixString;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(sol.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}