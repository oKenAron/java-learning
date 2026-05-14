package LeetCode2894;

class Solution {
    public int differenceOfSums(int n, int m) {
        return (1 + n) * n / 2 - (1 + (n / m)) * (n / m) / 2 * m * 2;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.differenceOfSums(10,3));
        System.out.println(sol.differenceOfSums(5,6));
        System.out.println(sol.differenceOfSums(5,1));
    }
}
