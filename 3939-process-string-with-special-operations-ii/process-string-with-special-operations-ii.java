class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];

        long cur = 0;

        // Compute length after each operation
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                cur++;
            } else if (ch == '*') {
                if (cur > 0) cur--;
            } else if (ch == '#') {
                cur *= 2;
            } else if (ch == '%') {
                // reverse -> length unchanged
            }

            len[i] = cur;
        }

        // k is out of bounds
        if (k >= cur) return '.';

        // Work backwards
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long currLen = len[i];

            if (ch >= 'a' && ch <= 'z') {
                long prevLen = currLen - 1;

                // This letter created index prevLen
                if (k == prevLen) {
                    return ch;
                }
                // Otherwise k stays the same
            } else if (ch == '*') {
                // Previous length = current + 1
                // Removed last character, so surviving indices unchanged.
            } else if (ch == '#') {
                long prevLen = currLen / 2;

                // Second copy maps to first copy
                if (k >= prevLen) {
                    k -= prevLen;
                }
            } else if (ch == '%') {
                // Reverse mapping
                k = currLen - 1 - k;
            }
        }

        return '.';
    }
}