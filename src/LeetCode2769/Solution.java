package LeetCode2769;

class Solution {
    public int theMaximumAchievableX(int num, int t) {
        return num + 2 * t;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.theMaximumAchievableX(4,1));
        System.out.println(sol.theMaximumAchievableX(3,2));
    }
}
