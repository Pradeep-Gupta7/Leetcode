class Solution {
    public long minSumSquareDiff(int[] a, int[] b, int k1, int k2) {
        int n = a.length, max = 0;
        long k = k1 + k2;
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = Math.abs(a[i] - b[i]);
            max = Math.max(max, d[i]);
        }

        int lo = 0, hi = max;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            long need = 0;
            for (int x : d) need += Math.max(0, x - mid);
            if (need <= k) hi = mid;
            else lo = mid + 1;
        }

        for (int i = 0; i < n; i++) {
            if (d[i] > lo) {
                k -= d[i] - lo;
                d[i] = lo;
            }
        }

        Arrays.sort(d);

        int i = n - 1;
        while (k > 0 && i >= 0 && d[i] > 0) {
            d[i]--;
            k--;

            if (i > 0 && d[i - 1] > d[i]) {
                i--;
            }
        }

        long ans = 0;
        for (int x : d) ans += 1L * x * x;
        return ans;
    }
}