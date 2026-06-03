import java.util.Arrays;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, 
                                  int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length, m = waterStartTime.length;
        int ans = Integer.MAX_VALUE;

        // Create indexed arrays and sort
        Integer[] landIdx = new Integer[n];
        Integer[] waterIdx = new Integer[m];
        for (int i = 0; i < n; i++) landIdx[i] = i;
        for (int i = 0; i < m; i++) waterIdx[i] = i;

        // Sort by start time
        Arrays.sort(landIdx, (a, b) -> landStartTime[a] - landStartTime[b]);
        Arrays.sort(waterIdx, (a, b) -> waterStartTime[a] - waterStartTime[b]);

        // Precompute suffix min of (waterStart[j] + waterDuration[j]) for water rides sorted by start
        int[] waterFinish = new int[m]; // waterStart[j] + waterDuration[j], sorted by waterStart
        int[] suffixMinWaterFinish = new int[m + 1];
        for (int i = 0; i < m; i++) {
            int j = waterIdx[i];
            waterFinish[i] = waterStartTime[j] + waterDuration[j];
        }
        suffixMinWaterFinish[m] = Integer.MAX_VALUE;
        for (int i = m - 1; i >= 0; i--) {
            suffixMinWaterFinish[i] = Math.min(suffixMinWaterFinish[i + 1], waterFinish[i]);
        }

        // Similarly for land
        int[] landFinishArr = new int[n];
        int[] suffixMinLandFinish = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int j = landIdx[i];
            landFinishArr[i] = landStartTime[j] + landDuration[j];
        }
        suffixMinLandFinish[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffixMinLandFinish[i] = Math.min(suffixMinLandFinish[i + 1], landFinishArr[i]);
        }

        // Case 1: Land ride first, then water ride
        // For each land ride i, finish time = landStart[i] + landDuration[i]
        // Then best water: min over all j of max(finishLand, waterStart[j]) + waterDuration[j]
        // Binary search: find first water with waterStart[j] >= finishLand
        //   - For those: cost = waterStart[j]+waterDuration[j], take suffix min
        //   - For those with waterStart[j] < finishLand: cost = finishLand + waterDuration[j]
        //     need prefix min of waterDuration

        int[] prefixMinWaterDur = new int[m + 1];
        prefixMinWaterDur[0] = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            prefixMinWaterDur[i + 1] = Math.min(prefixMinWaterDur[i], waterDuration[waterIdx[i]]);
        }

        for (int i = 0; i < n; i++) {
            int li = landIdx[i];
            int finishLand = landStartTime[li] + landDuration[li];

            // Binary search in sorted water: first index where waterStartTime[waterIdx[pos]] >= finishLand
            int lo = 0, hi = m;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (waterStartTime[waterIdx[mid]] >= finishLand) hi = mid;
                else lo = mid + 1;
            }
            int pos = lo; // first water ride that opens at or after finishLand

            // Water rides with index >= pos: they open after finishLand, cost = waterStart+waterDur
            if (pos < m) {
                ans = Math.min(ans, suffixMinWaterFinish[pos]);
            }
            // Water rides with index < pos: they open before finishLand, cost = finishLand + waterDur
            if (pos > 0 && prefixMinWaterDur[pos] != Integer.MAX_VALUE) {
                ans = Math.min(ans, finishLand + prefixMinWaterDur[pos]);
            }
        }

        // Case 2: Water ride first, then land ride
        int[] prefixMinLandDur = new int[n + 1];
        prefixMinLandDur[0] = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            prefixMinLandDur[i + 1] = Math.min(prefixMinLandDur[i], landDuration[landIdx[i]]);
        }

        for (int i = 0; i < m; i++) {
            int wi = waterIdx[i];
            int finishWater = waterStartTime[wi] + waterDuration[wi];

            int lo = 0, hi = n;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (landStartTime[landIdx[mid]] >= finishWater) hi = mid;
                else lo = mid + 1;
            }
            int pos = lo;

            if (pos < n) {
                ans = Math.min(ans, suffixMinLandFinish[pos]);
            }
            if (pos > 0 && prefixMinLandDur[pos] != Integer.MAX_VALUE) {
                ans = Math.min(ans, finishWater + prefixMinLandDur[pos]);
            }
        }

        return ans;
    }
}