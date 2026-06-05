class Solution {

    String s;
    Long[][][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        s = String.valueOf(n);

        memo = new Long[s.length()][11][11][2][2][16];

        return dp(0, 10, 10, 1, 0, 0);
    }

    private long dp(int pos,
                    int prev2,
                    int prev1,
                    int tight,
                    int started,
                    int waviness) {

        if (pos == s.length()) {
            return waviness;
        }

        if (memo[pos][prev2][prev1][tight][started][waviness] != null) {
            return memo[pos][prev2][prev1][tight][started][waviness];
        }

        int limit = tight == 1 ? s.charAt(pos) - '0' : 9;

        long res = 0;

        for (int d = 0; d <= limit; d++) {

            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {
                res += dp(
                        pos + 1,
                        10,
                        10,
                        ntight,
                        0,
                        waviness
                );
            } else {

                int add = 0;

                if (prev2 != 10 && prev1 != 10) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                int np2, np1;

                if (prev1 == 10) {
                    np2 = 10;
                    np1 = d;
                } else if (prev2 == 10) {
                    np2 = prev1;
                    np1 = d;
                } else {
                    np2 = prev1;
                    np1 = d;
                }

                res += dp(
                        pos + 1,
                        np2,
                        np1,
                        ntight,
                        1,
                        waviness + add
                );
            }
        }

        return memo[pos][prev2][prev1][tight][started][waviness] = res;
    }
}