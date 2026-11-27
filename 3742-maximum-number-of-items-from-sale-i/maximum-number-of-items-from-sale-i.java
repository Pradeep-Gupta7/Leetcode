class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] dp = new int[budget + 1]; // dp[b] = max copies with spend <= b

        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int factor = items[i][0], price = items[i][1];
            minPrice = Math.min(minPrice, price);

            // free copies this item generates
            int value = 1;
            for (int j = 0; j < n; j++) {
                if (j != i && items[j][0] % factor == 0) value++;
            }

            // 0/1 knapsack: first purchase of item i
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + value);
            }
        }

        // unbounded extras: each extra copy of the cheapest item adds 1
        for (int b = minPrice; b <= budget; b++) {
            dp[b] = Math.max(dp[b], dp[b - minPrice] + 1);
        }

        return dp[budget];
    }
}