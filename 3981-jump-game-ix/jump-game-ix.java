class Solution {

    public int[] maxValue(int[] nums) {

        int n = nums.length;

        int[] preMax = new int[n];
        int[] ans = new int[n];

        // prefix maximum
        preMax[0] = nums[0];

        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }

        int sufMin = Integer.MAX_VALUE;

        // process from right to left
        for (int i = n - 1; i >= 0; i--) {

            // if there exists a smaller value on right,
            // we can chain jumps and inherit answer
            if (preMax[i] > sufMin) {

                if (i == n - 1) {
                    ans[i] = preMax[i];
                } else {
                    ans[i] = ans[i + 1];
                }

            } else {

                ans[i] = preMax[i];
            }

            sufMin = Math.min(sufMin, nums[i]);
        }

        return ans;
    }
}