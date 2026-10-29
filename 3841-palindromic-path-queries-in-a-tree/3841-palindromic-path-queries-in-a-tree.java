import java.util.*;

class Solution {
    private int timer = 0;
    private int[] tin, tout, depth;
    private int[][] up;
    private List<Integer>[] adj;
    private int[] fenwick;
    private int n;

    // Fenwick Tree (XOR BIT)
    private void bitXor(int i, int val) {
        for (; i <= n; i += i & -i) {
            fenwick[i] ^= val;
        }
    }

    private int bitQuery(int i) {
        int res = 0;
        for (; i > 0; i -= i & -i) {
            res ^= fenwick[i];
        }
        return res;
    }

    // Range XOR update: [l, r] ^= val
    private void rangeUpdate(int l, int r, int val) {
        bitXor(l, val);
        bitXor(r + 1, val);
    }

    private void dfs(int u, int p, int d) {
        tin[u] = ++timer;
        up[u][0] = p;
        depth[u] = d;

        for (int i = 1; i < 18; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        tout[u] = timer;
    }

    private int getLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int tmp = u; u = v; v = tmp;
        }

        // Lift u to the same depth as v
        for (int i = 17; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }

        if (u == v) return u;

        for (int i = 17; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][18];
        fenwick = new int[n + 2];

        // DFS to compute Euler tour intervals and binary lifting table
        dfs(0, 0, 0);

        char[] chars = s.toCharArray();

        // Initialize Fenwick tree with initial node values
        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            rangeUpdate(tin[i], tout[i], mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] != c) {
                    int oldMask = 1 << (chars[u] - 'a');
                    int newMask = 1 << (c - 'a');
                    int delta = oldMask ^ newMask;

                    // Update all descendants in the Euler tour range
                    rangeUpdate(tin[u], tout[u], delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLca(u, v);
                int maskU = bitQuery(tin[u]);
                int maskV = bitQuery(tin[v]);
                int lcaCharMask = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaCharMask;

                // At most one bit set check
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }
}