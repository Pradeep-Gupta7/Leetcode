class Solution {
    public int maxSum(java.util.List<Integer> nums, int k) {
        int[] count = new int[30];
        long ans = 0, mod = 1_000_000_007;

        for (int x : nums)
            for (int b = 0; b < 30; b++)
                count[b] += (x >> b) & 1;

        while (k-- > 0) {
            long x = 0;
            for (int b = 0; b < 30; b++)
                if (count[b] > 0) {
                    x |= 1L << b;
                    count[b]--;
                }
            ans = (ans + x * x) % mod;
        }
        return (int) ans;
    }
}