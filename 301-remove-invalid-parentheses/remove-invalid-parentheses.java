import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.add(s);
        visited.add(s);

        boolean found = false;

        while (!q.isEmpty()) {
            String cur = q.poll();

            if (isValid(cur)) {
                ans.add(cur);
                found = true;
            }

            // Don't remove more characters once minimum removal is found
            if (found)
                continue;

            for (int i = 0; i < cur.length(); i++) {
                char ch = cur.charAt(i);

                // Remove only parentheses, not letters
                if (ch != '(' && ch != ')')
                    continue;

                String next = cur.substring(0, i)
                            + cur.substring(i + 1);

                if (!visited.contains(next)) {
                    visited.add(next);
                    q.add(next);
                }
            }
        }

        return ans;
    }

    private boolean isValid(String s) {
        int count = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count++;
            } else if (ch == ')') {
                count--;

                if (count < 0)
                    return false;
            }
        }

        return count == 0;
    }
}