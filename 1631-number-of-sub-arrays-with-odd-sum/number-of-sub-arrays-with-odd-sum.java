class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;
        long even = 1, odd = 0, ans = 0;
        int sum = 0;

        for (int num : arr) {
            sum = (sum + num) % 2;

            if (sum == 1) {
                ans = (ans + even) % mod;
                odd++;
            } else {
                ans = (ans + odd) % mod;
                even++;
            }
        }

        return (int) ans;
    }
}