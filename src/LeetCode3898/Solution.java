package LeetCode3898;

import java.util.Arrays;

class Solution {
    public int[] findDegrees(int[][] matrix) {
        int[] ans = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++){
            int degreeCount = 0;
            // 这里 matrix[i].length 在整道题里都是固定值, 甚至和 matrix.length 一致,
            // 是不是说这里换成一个特定变量效率更高, 类似疑问很早我就有了, 但在这道题我感觉这个问题最突出.
            for (int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 1) degreeCount += 1;
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
