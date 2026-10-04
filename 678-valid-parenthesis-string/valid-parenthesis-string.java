class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                low++;
                high++;
            } 
            else if (c == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;
                high++;
            }

            // Too many closing parentheses
            if (high < 0) {
                return false;
            }

            // We can never have fewer than 0 unmatched '('
            low = Math.max(low, 0);
        }

        return low == 0;
    }
}