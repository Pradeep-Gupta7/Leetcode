import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] pos = new long[n];
        long P = 4L * side;

        for (int i = 0; i < n; i++) {
            long x = points[i][0], y = points[i][1];
            if      (y == 0)    pos[i] = x;
            else if (x == side) pos[i] = (long) side + y;
            else if (y == side) pos[i] = 2L * side + (side - x);
            else                pos[i] = 3L * side + (side - y);
        }

        Arrays.sort(pos);

        // Double the array for circular binary search
        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i]     = pos[i];
            ext[i + n] = pos[i] + P;
        }

        long lo = 0, hi = 2L * side, ans = 0;
        while (lo <= hi) {
            long mid = (lo + hi) >>> 1;
            if (check(pos, ext, n, k, mid, P)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return (int) ans;
    }

    private boolean check(long[] pos, long[] ext, int n, int k, long d, long P) {
        if (d == 0) return true;

        for (int start = 0; start < n; start++) {
            int cnt = 1;
            int cur = start;          // index into ext[]
            long last = ext[start];   // current position value

            while (cnt < k) {
                // Need next point with Manhattan dist >= d from last
                // i.e., forward arc >= d  →  ext value >= last + d
                // AND  forward arc <= P-d →  ext value <= last + (P - d)
                // Search in ext[cur+1 .. start+n]

                long target = last + d;
                long limit  = last + (P - d); // must not exceed (backward arc would be < d)

                // Binary search for first index in ext >= target
                int lo = cur + 1, hi = start + n, next = -1;
                while (lo <= hi) {
                    int m = (lo + hi) >>> 1;
                    if (ext[m] >= target) { next = m; hi = m - 1; }
                    else lo = m + 1;
                }

                // Valid only if within upper bound
                if (next == -1 || ext[next] > limit) break;

                last = ext[next];
                cur  = next;
                cnt++;
            }

            if (cnt == k) {
                // Check closing arc: from last back to pos[start]
                long closingFwd = ext[start] + P - last; // arc from last → start going forward
                long closingDist = Math.min(closingFwd, P - closingFwd);
                if (closingDist >= d) return true;
            }
        }
        return false;
    }
}