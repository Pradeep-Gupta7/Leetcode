import java.util.*;

class Solution {
    int[] seg;
    int N;

    public List<Boolean> getResults(int[][] queries) {
        int maxX = 0;
        for (int[] q : queries) maxX = Math.max(maxX, q[1]);
        N = maxX + 1;
        seg = new int[4 * N];

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        List<Boolean> results = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1];
                int left = obstacles.floor(x);
                Integer right = obstacles.higher(x);
                if (right != null) update(1, 0, N - 1, right, right - x);
                update(1, 0, N - 1, x, x - left);
                obstacles.add(x);
            } else {
                int x = q[1], sz = q[2];
                int bestInternal = query(1, 0, N - 1, 0, x);
                int trailing = x - obstacles.floor(x);
                results.add(Math.max(bestInternal, trailing) >= sz);
            }
        }
        return results;
    }

    void update(int node, int lo, int hi, int pos, int val) {
        if (lo == hi) { seg[node] = val; return; }
        int mid = (lo + hi) >>> 1;
        if (pos <= mid) update(2 * node, lo, mid, pos, val);
        else update(2 * node + 1, mid + 1, hi, pos, val);
        seg[node] = Math.max(seg[2 * node], seg[2 * node + 1]);
    }

    int query(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return 0;
        if (l <= lo && hi <= r) return seg[node];
        int mid = (lo + hi) >>> 1;
        return Math.max(query(2 * node, lo, mid, l, r),
                        query(2 * node + 1, mid + 1, hi, l, r));
    }
}