class Solution {

    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // Suffix sum
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // Initially Alice starts at index 0 with M = 1
        return solve(0, 1);
    }

    private int solve(int i, int M) {

        // All remaining piles can be taken
        if (i >= n) {
            return 0;
        }

        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M; X++) {

            int newM = Math.max(M, X);

            // Current player gets:
            // remaining stones - opponent's best
            int current = suffix[i]
                    - solve(i + X, newM);

            best = Math.max(best, current);
        }

        dp[i][M] = best;

        return best;
    }
}