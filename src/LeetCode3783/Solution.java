package LeetCode3783;

import java.util.ArrayList;

class Solution {
    public int mirrorDistance(int n) {
        ArrayList<Integer> listN = new ArrayList<>();
        int ans = 0;
        while(n != 0){
           listN.add(n % 10);
           n /= 10;
        }
        for (int i = 0; i < listN.size(); i++){
            ans += listN.get(i) * ((int)Math.pow(10, i) - (int)Math.pow(10, listN.size() - 1 - i));
        }
        return Math.abs(ans);
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.mirrorDistance(25));
        System.out.println(sol.mirrorDistance(10));
        System.out.println(sol.mirrorDistance(7));
    }
}
