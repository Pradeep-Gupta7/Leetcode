import java.util.*;

class Solution {
    static final long M1 = 1_000_000_007L, M2 = 998_244_353L;
    long[] h1, h2, p1, p2;
    int[] nums;
    int n;

    public int smallestUniqueSubarray(int[] nums) {
        this.nums = nums;
        n = nums.length;

        Random rnd = new Random();
        long b1 = 100_003 + rnd.nextInt(1_000_000);
        long b2 = 100_003 + rnd.nextInt(1_000_000);

        h1 = new long[n + 1]; h2 = new long[n + 1];
        p1 = new long[n + 1]; p2 = new long[n + 1];
        p1[0] = 1; p2[0] = 1;
        for (int i = 0; i < n; i++) {
            h1[i + 1] = (h1[i] * b1 + nums[i]) % M1;
            h2[i + 1] = (h2[i] * b2 + nums[i]) % M2;
            p1[i + 1] = p1[i] * b1 % M1;
            p2[i + 1] = p2[i] * b2 % M2;
        }

        int lo = 1, hi = n;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (hasUnique(mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private boolean hasUnique(int L) {
        int cnt = n - L + 1;
        long[] arr = new long[cnt];
        for (int i = 0; i < cnt; i++) {
            long a = ((h1[i + L] - h1[i] * p1[L]) % M1 + M1) % M1;
            long b = ((h2[i + L] - h2[i] * p2[L]) % M2 + M2) % M2;
            arr[i] = a * M2 + b;
        }
        Arrays.sort(arr);
        for (int i = 0; i < cnt; i++) {
            boolean sameLeft = i > 0 && arr[i] == arr[i - 1];
            boolean sameRight = i < cnt - 1 && arr[i] == arr[i + 1];
            if (!sameLeft && !sameRight) return true;
        }
        return false;
    }
}