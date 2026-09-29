class Solution {
    private int m, n;
    private char[][] g;
    private boolean[][][] visited; 

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        g = grid;

        int len = m + n - 1;
        if ((len & 1) == 1) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        visited = new boolean[m][n][len / 2 + 2];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int balance) {
        if (i >= m || j >= n) return false;

        balance += (g[i][j] == '(') ? 1 : -1;
        if (balance < 0) return false;

        
        int remaining = (m - 1 - i) + (n - 1 - j);
        if (balance > remaining) return false; 

        if (i == m - 1 && j == n - 1) return balance == 0;

        if (visited[i][j][balance]) return false; 
        visited[i][j][balance] = true;

        return dfs(i + 1, j, balance) || dfs(i, j + 1, balance);
    }
}