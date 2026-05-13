package LeetCode1863;

// 算法有优化空间
class Solution {
    public int subsetXORSum(int[] nums) {
        int result = 0;
        int subsetCount = 1 << nums.length;
        for (int i = 0; i < subsetCount; i++){
            int resultSubset = 0;
            for (int j = 0; j < nums.length; j++){
                if ((i & (1 << j)) != 0){
                    resultSubset ^= nums[j];
                }
            }
            result += resultSubset;
        }
        return result;
    }

    public static void main(String[] args){
        int[] exampleA = new int[]{1,3};
        int[] exampleB = new int[]{5,1,6};
        int[] exampleC = new int[]{3,4,5,6,7,8};

        Solution sol = new Solution();

        System.out.println(sol.subsetXORSum(exampleA));
        System.out.println(sol.subsetXORSum(exampleB));
        System.out.println(sol.subsetXORSum(exampleC));
    }
}
