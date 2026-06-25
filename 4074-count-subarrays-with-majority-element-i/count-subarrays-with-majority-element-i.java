class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            int balance = 0; // +1 for target, -1 for others
            for (int j = i; j < n; j++) {
                balance += (nums[j] == target) ? 1 : -1;
                if (balance > 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}