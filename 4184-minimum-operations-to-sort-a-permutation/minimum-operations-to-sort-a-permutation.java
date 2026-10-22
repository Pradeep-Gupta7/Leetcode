import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int zeroIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        int reverseZeroIndex = n - 1 - zeroIndex;

        boolean normal = true;
        boolean reversed = true;

        for (int i = 0; i < n; i++) {
            if (nums[(zeroIndex + i) % n] != i) {
                normal = false;
            }

            int idx = (reverseZeroIndex + i) % n;
            if (nums[n - 1 - idx] != i) {
                reversed = false;
            }
        }

        if (!normal && !reversed) {
            return -1;
        }

        int[][] dist = new int[2][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int type = state[0];
            int k = state[1];
            int d = dist[type][k];

            if (type == 0 && normal && k == zeroIndex) {
                return d;
            }

            if (type == 1 && reversed && k == reverseZeroIndex) {
                return d;
            }

            int nextK = (k + 1) % n;
            if (dist[type][nextK] == -1) {
                dist[type][nextK] = d + 1;
                queue.offer(new int[]{type, nextK});
            }

            int reverseK = (n - k) % n;
            int nextType = 1 - type;

            if (dist[nextType][reverseK] == -1) {
                dist[nextType][reverseK] = d + 1;
                queue.offer(new int[]{nextType, reverseK});
            }
        }

        return -1;
    }
}