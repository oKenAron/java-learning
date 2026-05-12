package LeetCode1108;

public class Solution {
    public String defangIPaddr(String address) {
        StringBuilder result = new StringBuilder();
        for (int i=0;i<address.length();i++){
            if (address.charAt(i) == '.'){
                result.append("[.]");
            } else {
                result.append(address.charAt(i));
            }
        }
        return result.toString();
    }
    public static void main(String[] args){
        String exampleIP = "127.0.0.1";
        System.out.println(new Solution().defangIPaddr(exampleIP));
    }
}
