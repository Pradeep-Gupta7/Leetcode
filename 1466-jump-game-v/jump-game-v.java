class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] memo = new int[n];
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dfs(arr, d, i, memo));
        }
        
        return result;
    }
    
    private int dfs(int[] arr, int d, int i, int[] memo) {
        if (memo[i] != 0) return memo[i];
        
        int n = arr.length;
        int best = 1;
        
        // Jump right
        for (int x = 1; x <= d && i + x < n; x++) {
            if (arr[i + x] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(arr, d, i + x, memo));
        }
        
        // Jump left
        for (int x = 1; x <= d && i - x >= 0; x++) {
            if (arr[i - x] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(arr, d, i - x, memo));
        }
        
        memo[i] = best;
        return best;
    }
}