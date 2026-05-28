package LeetCode13;

class Solution {
    public int romanToInt(String s) {
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            switch(s.charAt(i)){
                case 'I':
                    ans += 1;
                    break;
                case 'V':
                    if (i != 0 && s.charAt(i-1) == 'I'){
                        ans += 3;
                    } else {
                        ans += 5;
                    }
                    break;
                case 'X':
                    if (i != 0 && s.charAt(i-1) == 'I'){
                        ans += 8;
                    } else {
                        ans += 10;
                    }
                    break;
                case 'L':
                    if (i != 0 && s.charAt(i-1) == 'X'){
                        ans += 30;
                    } else {
                        ans += 50;
                    }
                    break;
                case 'C':
                    if (i != 0 && s.charAt(i-1) == 'X'){
                        ans += 80;
                    } else {
                        ans += 100;
                    }
                    break;
                case 'D':
                    if (i != 0 && s.charAt(i-1) == 'C'){
                        ans += 300;
                    } else {
                        ans += 500;
                    }
                    break;
                case 'M':
                    if (i != 0 && s.charAt(i-1) == 'C'){
                        ans += 800;
                    } else {
                        ans += 1000;
                    }
                    break;
                default:
                    break;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        Solution sol = new Solution();

        System.out.println(sol.romanToInt("III"));
        System.out.println(sol.romanToInt("LVIII"));
        System.out.println(sol.romanToInt("MCMXCIV"));
    }
}
