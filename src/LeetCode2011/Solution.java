package LeetCode2011;

public class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int X = 0;
        for (String op: operations) {
            if (op.equals("X++") || op.equals("++X")) {
                X++;
            } else if (op.equals("X--") || op.equals("--X")) {
                X--;
            }
        }
        return X;
    }

    public static void main(String[] args) {
        String[] expX = new String[]{"--X", "X++", "X++"};
        String[] expX2 = new String[]{"++X", "++X", "X++"};
        String[] expX3 = new String[]{"X++", "++X", "--X", "X--"};

        Solution sol = new Solution();

        System.out.println(sol.finalValueAfterOperations(expX));
        System.out.println(sol.finalValueAfterOperations(expX2));
        System.out.println(sol.finalValueAfterOperations(expX3));
    }
}
