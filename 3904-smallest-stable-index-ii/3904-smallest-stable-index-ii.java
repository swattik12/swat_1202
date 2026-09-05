class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // right[i] = minimum value from i to n-1
        int[] right = new int[n];

        right[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }

        // left = maximum value from 0 to i
        int left = 0;

        for (int i = 0; i < n; i++) {
            left = Math.max(left, nums[i]);

            int instability = left - right[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}