class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long) k1 + k2;
        
        // Count frequencies of each absolute difference
        int maxDiff = 0;
        int[] count = new int[100001];
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }
        
        // Greedily reduce the largest differences
        for (int d = maxDiff; d > 0 && totalOps > 0; d--) {
            if (count[d] == 0) continue;
            
            // Number of operations needed to reduce all elements with difference `d` to `d - 1`
            long opsToReduce = Math.min(totalOps, (long) count[d]);
            
            count[d] -= opsToReduce;
            count[d - 1] += opsToReduce;
            totalOps -= opsToReduce;
        }
        
        // Calculate the final minimum sum of squared differences
        long minSum = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                minSum += (long) count[d] * d * d;
            }
        }
        
        return minSum;
    }
}