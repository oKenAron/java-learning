package LeetCode2011;

public class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int X = 0;
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("X++") || operations[i].equals("++X")) {
                X++;
            } else if (operations[i].equals("X--") || operations[i].equals("--X")) {
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
