package LeetCode2894;

class Solution {
    public int differenceOfSums(int n, int m) {
        int num1 = 0, num2 = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m != 0) {
                num1 += i;
            } else {
                num2 += i;
            }
        }
        return num1 - num2;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.differenceOfSums(10,3));
        System.out.println(sol.differenceOfSums(5,6));
        System.out.println(sol.differenceOfSums(5,1));
    }
}
