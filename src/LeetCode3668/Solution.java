package LeetCode3668;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        int[] ans = new int[friends.length];
        int index = 0;
        for (int id : order) {
            for (int friend : friends) {
                if (id == friend) {
                    ans[index] = friend;
                    index++;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.recoverOrder(new int[]{3, 1, 2, 5, 4}, new int[]{1, 3, 4})));
        System.out.println(Arrays.toString(sol.recoverOrder(new int[]{1, 4, 5, 3, 2}, new int[]{2, 5})));
    }
}