class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int maxDist = 0;

        while (i < nums1.length && j < nums2.length) {
            
            // Valid pair condition
            if (nums1[i] <= nums2[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++; // try to increase distance
            } 
            else {
                i++; // move i to find smaller nums1[i]
            }
            
            // Ensure i <= j
            if (i > j) {
                j = i;
            }
        }

        return maxDist;
    }
}