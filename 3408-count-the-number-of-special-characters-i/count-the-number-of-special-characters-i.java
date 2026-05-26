class Solution {
    public int numberOfSpecialChars(String word) {
        // Handles: null/empty string
        if (word == null || word.isEmpty()) return 0;

        boolean[] lower = new boolean[26];
        boolean[] upper = new boolean[26];

        for (char c : word.toCharArray()) {
            if (c >= 'a' && c <= 'z') lower[c - 'a'] = true;
            else if (c >= 'A' && c <= 'Z') upper[c - 'A'] = true;
            // Handles: non-letter chars (ignored safely)
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            // Handles: same letter appearing multiple times (sets ignore dupes)
            // Handles: only lowercase or only uppercase present
            if (lower[i] && upper[i]) count++;
        }
        return count;
    }
}