class Solution {
    public int minMoves(int[] nums) {
        int max = 0, sum = 0;
        for (int n : nums) {
            max = Math.max(max, n);
            sum += n;
        }
        return max * nums.length - sum;
    }
}