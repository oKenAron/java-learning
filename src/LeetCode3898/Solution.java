package LeetCode3898;

import java.util.Arrays;

class Solution {
    public int[] findDegrees(int[][] matrix) {
        // 提取变量 matrix.length, 以达到视觉清爽 & 用代码声明业务逻辑的目的.
        int n = matrix.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++){
            int degreeCount = 0;
            for (int j = 0; j < n; j++){
                // 因为只有 0 和 1，直接累加取代 if 判断.
                // if(matrix[i][j] == 1) degreeCount += 1;
                degreeCount += matrix[i][j];
            }
            ans[i] = degreeCount;
        }
        return ans;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(Arrays.toString(sol.findDegrees(new int[][]{{0,1,1},{1,0,1},{1,1,0}})));
        System.out.println(Arrays.toString(sol.findDegrees(new int[][]{{0,1,0},{1,0,0},{0,0,0}})));
        System.out.println(Arrays.toString(sol.findDegrees(new int[][]{{0}})));
    }
}
