class Solution {

    public int minJumps(int[] nums) {
        int n = nums.length;

        if (n == 1) return 0;

        int MAX = 0;
        for (int x : nums) MAX = Math.max(MAX, x);

        // Smallest Prime Factor
        int[] spf = new int[MAX + 1];

        for (int i = 2; i <= MAX; i++) {
            if (spf[i] == 0) {
                spf[i] = i;

                if ((long) i * i <= MAX) {
                    for (int j = i * i; j <= MAX; j += i) {
                        if (spf[j] == 0) {
                            spf[j] = i;
                        }
                    }
                }
            }
        }

        // prime -> all indices divisible by prime
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            while (x > 1) {
                int p = spf[x];

                map.computeIfAbsent(p, k -> new ArrayList<>()).add(i);

                while (x % p == 0) {
                    x /= p;
                }
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int idx = q.poll();

                if (idx == n - 1) {
                    return steps;
                }

                // left
                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                    q.offer(idx - 1);
                }

                // right
                if (idx + 1 < n && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    q.offer(idx + 1);
                }

                int val = nums[idx];

                // teleport only if current value itself is prime
                if (val > 1 && spf[val] == val) {

                    ArrayList<Integer> nexts = map.get(val);

                    if (nexts != null) {

                        for (int next : nexts) {

                            if (!visited[next]) {
                                visited[next] = true;
                                q.offer(next);
                            }
                        }

                        // critical optimization
                        map.remove(val);
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}