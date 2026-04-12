class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        int[][] dist = new int[26][26];

        // Precompute distances
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dist[i][j] = Math.abs(i / 6 - j / 6) + Math.abs(i % 6 - j % 6);
            }
        }

        int[] dp = new int[26];
        int res = 0, maxSave = 0;

        for (int i = 0; i < n - 1; i++) {
            int a = word.charAt(i) - 'A';
            int b = word.charAt(i + 1) - 'A';

            for (int j = 0; j < 26; j++) {
                dp[a] = Math.max(dp[a], dp[j] + dist[a][b] - dist[j][b]);
            }

            maxSave = Math.max(maxSave, dp[a]);
            res += dist[a][b];
        }

        return res - maxSave;
    }
}