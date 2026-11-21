class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // children adjacency list
        int[] head = new int[n], nxt = new int[n];
        java.util.Arrays.fill(head, -1);
        for (int i = 1; i < n; i++) {
            nxt[i] = head[parent[i]];
            head[parent[i]] = i;
        }

        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);

        int[] newPar = new int[n];
        int[] prev = new int[n];
        int[] order = new int[n];
        int cnt = 0;

        int[] it = head.clone();
        int[] stack = new int[n];
        int sp = 0;

        // enter root
        int c0 = s.charAt(0) - 'a';
        prev[0] = last[c0];
        last[c0] = 0;
        newPar[0] = -1;
        order[cnt++] = 0;
        stack[sp++] = 0;

        while (sp > 0) {
            int u = stack[sp - 1];
            int v = it[u];
            if (v != -1) {
                it[u] = nxt[v];
                int c = s.charAt(v) - 'a';
                prev[v] = last[c];
                newPar[v] = (prev[v] != -1) ? prev[v] : parent[v];
                last[c] = v;
                order[cnt++] = v;
                stack[sp++] = v;
            } else {
                last[s.charAt(u) - 'a'] = prev[u]; // restore on exit
                sp--;
            }
        }

        int[] ans = new int[n];
        java.util.Arrays.fill(ans, 1);
        for (int i = n - 1; i >= 1; i--) {
            int u = order[i];
            ans[newPar[u]] += ans[u];
        }
        return ans;
    }
}