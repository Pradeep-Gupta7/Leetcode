import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        if (digits == null || digits.length() == 0)
            return result;

        String[] map = {
            "", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"
        };

        backtrack(result, digits, map, 0, new StringBuilder());
        return result;
    }

    private void backtrack(List<String> result, String digits, String[] map,
                           int index, StringBuilder current) {

        // Base case
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {
            current.append(ch);              // choose
            backtrack(result, digits, map, index + 1, current); // explore
            current.deleteCharAt(current.length() - 1); // undo
        }
    }
}