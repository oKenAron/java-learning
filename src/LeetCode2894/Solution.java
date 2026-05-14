package LeetCode2894;

class Solution {
    public int differenceOfSums(int n, int m) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m != 0) {
                result += i;
            } else {
                result -= i;
            }
        }
        return result;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.differenceOfSums(10,3));
        System.out.println(sol.differenceOfSums(5,6));
        System.out.println(sol.differenceOfSums(5,1));
    }
}
