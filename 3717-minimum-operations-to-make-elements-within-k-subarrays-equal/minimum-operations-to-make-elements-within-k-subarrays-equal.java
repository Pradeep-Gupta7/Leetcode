class Solution {
    int n;
    int[] bitCount;
    long[] bitSum;
    int[] sorted;

    void update(int[] bit, int idx, int val) {
        while (idx < bit.length) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    void update(long[] bit, int idx, long val) {
        while (idx < bit.length) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    long query(long[] bit, int idx) {
        long sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    int query(int[] bit, int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    int kth(int k) {
        int idx = 0;
        int mask = Integer.highestOneBit(n);

        for (; mask != 0; mask >>= 1) {
            int next = idx + mask;
            if (next <= n && bitCount[next] < k) {
                idx = next;
                k -= bitCount[next];
            }
        }
        return idx + 1;
    }

    public long minOperations(int[] nums, int x, int k) {
        n = nums.length;

        sorted = nums.clone();
        Arrays.sort(sorted);

        int[] unique = Arrays.stream(sorted).distinct().toArray();
        n = unique.length;

        bitCount = new int[n + 1];
        bitSum = new long[n + 1];

        int[] rank = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            rank[i] = Arrays.binarySearch(unique, nums[i]) + 1;
        }

        long[] prefix = new long[nums.length + 1];

        // Sliding window costs
        long[] cost = new long[nums.length - x + 1];

        for (int i = 0; i < nums.length; i++) {
            update(bitCount, rank[i], 1);
            update(bitSum, rank[i], nums[i]);

            if (i >= x) {
                update(bitCount, rank[i - x], -1);
                update(bitSum, rank[i - x], -nums[i - x]);
            }

            if (i >= x - 1) {
                int medianRank = kth((x + 1) / 2);
                long median = unique[medianRank - 1];

                long leftCount = query(bitCount, medianRank);
                long leftSum = query(bitSum, medianRank);

                long totalSum = query(bitSum, n);
                long totalCount = x;

                long rightCount = totalCount - leftCount;
                long rightSum = totalSum - leftSum;

                cost[i - x + 1] =
                    median * leftCount - leftSum +
                    rightSum - median * rightCount;
            }
        }

        // DP: choose k non-overlapping windows
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][nums.length + 1];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= nums.length; i++) {
            dp[0][i] = 0;
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= nums.length; i++) {

                dp[j][i] = dp[j][i - 1];

                if (i >= x && dp[j - 1][i - x] != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }

        return dp[k][nums.length];
    }
}