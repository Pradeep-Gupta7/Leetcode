import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        Integer[] a = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            a[i] = nums[i];
        }

        Arrays.sort(a, (x, y) -> Integer.compare(Math.abs(x), Math.abs(y)));

        int n = a.length;
        int positive = (n + 1) / 2;

        long ans = 0;

        for (int i = 0; i < n; i++) {
            long square = (long) a[i] * a[i];

            if (i < n - positive) {
                ans -= square;
            } else {
                ans += square;
            }
        }

        return ans;
    }
}