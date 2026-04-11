import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        if (n < 3) return -1;

        Map<Integer, Deque<Integer>> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new ArrayDeque<>());
            Deque<Integer> dq = map.get(nums[i]);

            dq.addLast(i);

            // Keep only last 3 indices
            if (dq.size() > 3) dq.pollFirst();

            // If we have 3 indices → calculate
            if (dq.size() == 3) {
                int first = dq.peekFirst();
                int last = dq.peekLast();
                ans = Math.min(ans, 2 * (last - first));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}