import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(n) + 1;

        long[] mul = new long[n];
        Arrays.fill(mul, 1);

        // Step 1: handle large k directly
        Map<Integer, List<int[]>> smallK = new HashMap<>();

        for (int[] q : queries) {
            int k = q[2];
            if (k > B) {
                for (int i = q[0]; i <= q[1]; i += k) {
                    mul[i] = mul[i] * q[3] % MOD;
                }
            } else {
                smallK.computeIfAbsent(k, x -> new ArrayList<>()).add(q);
            }
        }

        // Step 2: handle small k using grouping
        for (int k : smallK.keySet()) {
            List<int[]> qs = smallK.get(k);

            for (int r = 0; r < k; r++) {

                List<Integer> g = new ArrayList<>();
                for (int i = r; i < n; i += k) g.add(i);

                int m = g.size();
                long[] diff = new long[m + 1];
                Arrays.fill(diff, 1);

                for (int[] q : qs) {
                    int l = q[0], rgt = q[1], v = q[3];

                    if (l % k != r) continue;

                    int start = (l - r) / k;
                    int end = (rgt - r) / k;

                    diff[start] = diff[start] * v % MOD;
                    if (end + 1 < diff.length)
                        diff[end + 1] = diff[end + 1] * inv(v) % MOD;
                }

                long cur = 1;
                for (int i = 0; i < m; i++) {
                    cur = cur * diff[i] % MOD;
                    mul[g.get(i)] = mul[g.get(i)] * cur % MOD;
                }
            }
        }

        // final XOR
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long val = nums[i] * mul[i] % MOD;
            ans ^= (int) val;
        }

        return ans;
    }

    long inv(long x) {
        return pow(x, MOD - 2);
    }

    long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}