import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        // Step 1: map value -> indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> res = new ArrayList<>();

        // Step 2: process queries
        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);

            // only one occurrence
            if (list.size() == 1) {
                res.add(-1);
                continue;
            }

            int pos = Collections.binarySearch(list, q);

            int left = list.get((pos - 1 + list.size()) % list.size());
            int right = list.get((pos + 1) % list.size());

            int d1 = Math.abs(q - left);
            int d2 = Math.abs(q - right);

            // circular distance
            d1 = Math.min(d1, n - d1);
            d2 = Math.min(d2, n - d2);

            res.add(Math.min(d1, d2));
        }

        return res;
    }
}