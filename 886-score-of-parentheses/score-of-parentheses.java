class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                int score = (v == 0) ? 1 : 2 * v;

                int parent = stack.pop();
                stack.push(parent + score);
            }
        }

        return stack.pop();
    }
}