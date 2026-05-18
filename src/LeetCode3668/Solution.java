package LeetCode3668;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        ArrayList<Integer> ansTmp = new ArrayList<>();
        for (int ord : order) {
            for (int friend : friends) {
                if (ord == friend) {
                    ansTmp.add(ord);
                    break;
                }
            }
        }
        int[] ans = new int[ansTmp.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = ansTmp.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.recoverOrder(new int[]{3, 1, 2, 5, 4}, new int[]{1, 3, 4})));
        System.out.println(Arrays.toString(sol.recoverOrder(new int[]{1, 4, 5, 3, 2}, new int[]{2, 5})));
    }
}