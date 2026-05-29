package LeetCode13;

class Solution {
    public int romanCharToInt(char c){
        return switch(c){
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }
    public int romanToInt(String s) {
        int len = s.length();
        int ans = 0;
        int nextValue = romanCharToInt(s.charAt(0));
        for (int i = 0; i < len - 1; i++) {
            int currentValue = nextValue;
            nextValue = romanCharToInt(s.charAt(i + 1));
            if (currentValue < nextValue){
                ans -= currentValue;
            } else {
                ans += currentValue;
            }
        }
        ans += nextValue;
        return ans;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.romanToInt("III"));
        System.out.println(sol.romanToInt("LVIII"));
        System.out.println(sol.romanToInt("MCMXCIV"));
    }
}
