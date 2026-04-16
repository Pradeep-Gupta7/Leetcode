import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, String curr, int open, int close, int n) {
        // Base case
        if (curr.length() == 2 * n) {
            res.add(curr);
            return;
        }

        // Add '('
        if (open < n) {
            backtrack(res, curr + "(", open + 1, close, n);
        }

        // Add ')'
        if (close < open) {
            backtrack(res, curr + ")", open, close + 1, n);
        }
    }
}