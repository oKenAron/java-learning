package LeetCode14;

import java.util.HashMap;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        // 代码最初缺乏防御性判断。虽然题目说 1 <= strs.length，但养成习惯总是好的
        if (strs == null || strs.length == 0) return "";

        HashMap<Integer, Character> map = new HashMap<>();
        for(int i = 0; i < strs[0].length(); i++){
            map.put(i, strs[0].charAt(i));
        }
        for (int i = 1; i < strs.length; i++){
            int prefixLength = Math.min(map.size(), strs[i].length());
            while (map.size() > prefixLength){
                map.remove(map.size() - 1);
            }
            int sign = prefixLength;
            for (int j = 0; j < prefixLength; j++){
                if (map.get(j) != strs[i].charAt(j)) {
                    sign = j;
                    break;
                }
            }
            while (prefixLength - sign > 0){
                map.remove(map.size() - 1);
                sign++;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < map.size(); i++){
            builder.append(map.get(i));
        }
        return builder.toString();
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(sol.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}