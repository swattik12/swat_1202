class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[51];

        // Count total frequency
        for (int num : nums) {
            freq[num]++;
        }

        // If k == n, there is only one subarray
        if (k == n) {
            int ans = 0;

            for (int num : nums) {
                ans = Math.max(ans, num);
            }

            return ans;
        }

        // If k == 1, each element is its own subarray
        if (k == 1) {
            for (int i = 50; i >= 0; i--) {
                if (freq[i] == 1) {
                    return i;
                }
            }

            return -1;
        }

        // For 1 < k < n, only first and last elements
        // can belong to exactly one k-sized subarray.
        int ans = -1;

        if (freq[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }

        if (freq[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}