class Solution {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        
        // 1. Skip spaces
        while (i < n && s.charAt(i) == ' ') i++;
        
        // 2. Check sign
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') sign = -1;
            i++;
        }
        
        // 3. Convert digits
        int res = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            
            // 4. Handle overflow BEFORE multiplying
            if (res > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            res = res * 10 + digit;
            i++;
        }
        
        return res * sign;
    }
}