class Solution {
    public int totalNumbers(int[] d) {
        int ans = 0;
        boolean[] seen = new boolean[1000];

        for (int i = 0; i < d.length; i++)
            for (int j = 0; j < d.length; j++)
                for (int k = 0; k < d.length; k++)
                    if (i != j && i != k && j != k &&
                        d[i] != 0 && d[k] % 2 == 0) {
                        int n = d[i] * 100 + d[j] * 10 + d[k];
                        if (!seen[n]) {
                            seen[n] = true;
                            ans++;
                        }
                    }

        return ans;
    }
}