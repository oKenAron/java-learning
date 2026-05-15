package LeetCode2942;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i=0; i < words.length; i++){
            for (int j=0; j < words[i].length(); j++){
                if (words[i].charAt(j) == x){
                    ans.add(i);
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.findWordsContaining(new String[]{"leet","code"},'e'));
        System.out.println(sol.findWordsContaining(new String[]{"abc","bcd","aaaa","cbc"},'a'));
        System.out.println(sol.findWordsContaining(new String[]{"abc","bcd","aaaa","cbc"},'z'));
    }
}