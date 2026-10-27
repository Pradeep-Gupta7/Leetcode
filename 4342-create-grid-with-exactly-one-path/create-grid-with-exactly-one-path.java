class Solution {
public String[] createGrid(int m, int n) {
char[][] a = new char[m][n];
String[] ans = new String[m];
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            a[i][j] = '.';

    if (m == 1 || n == 1) {
        for (int i = 0; i < m; i++)
            ans[i] = new String(a[i]);
        return ans;
    }

    for (int j = 2; j < n; j++)
        a[0][j] = '#';

    a[1][0] = '#';

    for (int i = 2; i < m; i++)
        for (int j = 0; j < n - 1; j++)
            a[i][j] = '#';

    for (int i = 0; i < m; i++)
        ans[i] = new String(a[i]);

    return ans;
}
}
