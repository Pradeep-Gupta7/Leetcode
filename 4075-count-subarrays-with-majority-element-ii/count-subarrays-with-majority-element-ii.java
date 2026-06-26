class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // prefix values range from -n to +n, shift by n to make 0-indexed
        int[] bit = new int[2 * n + 2];

        long ans = 0;
        int prefix = 0;
        // add prefix[0] = 0
        update(bit, prefix + n, 2 * n + 1);

        for (int num : nums) {
            prefix += (num == target) ? 1 : -1;
            // count of past prefixes strictly < prefix  =  query(prefix-1)
            if (prefix - 1 + n >= 0)
                ans += query(bit, prefix - 1 + n);
            update(bit, prefix + n, 2 * n + 1);
        }
        return ans;
    }

    // BIT: 1-indexed, range [1, size]
    private void update(int[] bit, int i, int size) {
        for (i++; i <= size; i += i & -i) bit[i]++;
    }

    private long query(int[] bit, int i) {
        long s = 0;
        for (i++; i > 0; i -= i & -i) s += bit[i];
        return s;
    }
}