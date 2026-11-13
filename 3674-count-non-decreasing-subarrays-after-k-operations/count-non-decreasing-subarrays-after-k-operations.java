import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];

        Deque<long[]> dq = new ArrayDeque<>();
        long sumMax = 0;
        long ans = 0;
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            long cnt = 1;
            while (!dq.isEmpty() && dq.peekFirst()[0] <= nums[l]) {
                long[] t = dq.pollFirst();
                sumMax -= t[0] * t[1];
                cnt += t[1];
            }
            dq.offerFirst(new long[]{nums[l], cnt});
            sumMax += (long) nums[l] * cnt;

            while (sumMax - (pre[r + 1] - pre[l]) > k) {
                long[] last = dq.peekLast();
                sumMax -= last[0];
                last[1]--;
                if (last[1] == 0) dq.pollLast();
                r--;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}