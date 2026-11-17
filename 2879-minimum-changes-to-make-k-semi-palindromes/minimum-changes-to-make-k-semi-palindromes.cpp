class Solution {
public:
    int minimumChanges(string s, int k) {
        int n = s.size();

        const int INF = 1e9;

        // cost[i][j] = minimum changes to make s[i...j]
        // a semi-palindrome.
        vector<vector<int>> cost(n, vector<int>(n, INF));

        // Precompute semi-palindrome costs.
        for (int i = 0; i < n; i++) {
            for (int len = 2; i + len <= n; len++) {

                int best = INF;

                // Find proper divisors of len.
                for (int d = 1; d < len; d++) {

                    if (len % d != 0)
                        continue;

                    int groupLen = len / d;
                    int changes = 0;

                    // Each group must be a palindrome.
                    for (int r = 0; r < d; r++) {
                        for (int t = 0; t < groupLen / 2; t++) {

                            int left = i + r + t * d;
                            int right = i + r + (groupLen - 1 - t) * d;

                            if (s[left] != s[right])
                                changes++;
                        }
                    }

                    best = min(best, changes);
                }

                cost[i][i + len - 1] = best;
            }
        }

        // dp[p][i]: minimum changes to partition
        // first i characters into p substrings.
        vector<vector<int>> dp(
            k + 1, vector<int>(n + 1, INF)
        );

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[p - 1][j] == INF)
                        continue;

                    dp[p][i] = min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
};