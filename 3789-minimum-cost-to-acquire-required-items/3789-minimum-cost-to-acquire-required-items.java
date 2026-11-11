class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long common = Math.min(need1, need2);
        long rem1 = need1 - common;
        long rem2 = need2 - common;

        long costForBoth = Math.min((long) costBoth, (long) cost1 + cost2);
        long costForRem1 = Math.min((long) costBoth, (long) cost1);
        long costForRem2 = Math.min((long) costBoth, (long) cost2);

        return (common * costForBoth) + (rem1 * costForRem1) + (rem2 * costForRem2);
    }
}