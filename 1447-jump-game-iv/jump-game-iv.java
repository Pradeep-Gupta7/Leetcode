class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;

        // Edge case: already at the last index
        if (n == 1) return 0;

        // Build value -> list of indices map
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        // BFS
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            jumps++;

            for (int k = 0; k < size; k++) {
                int i = queue.poll();

                // Jump to i-1
                if (i - 1 >= 0 && !visited[i - 1]) {
                    if (i - 1 == n - 1) return jumps;
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }

                // Jump to i+1
                if (i + 1 < n && !visited[i + 1]) {
                    if (i + 1 == n - 1) return jumps;
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }

                // Jump to all same-value indices
                if (graph.containsKey(arr[i])) {
                    for (int j : graph.get(arr[i])) {
                        if (!visited[j]) {
                            if (j == n - 1) return jumps;
                            visited[j] = true;
                            queue.offer(j);
                        }
                    }
                    // ✅ CRITICAL: remove to avoid reprocessing this group
                    graph.remove(arr[i]);
                }
            }
        }

        return jumps; // unreachable if input is valid
    }
}