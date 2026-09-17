class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int[] best = new int[n];
        int INF = n + 1;

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int ans = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum becomes greater than target
            while (sum > target) {
                sum -= arr[left++];
            }

            // Found a subarray with sum = target
            if (sum == target) {
                int len = right - left + 1;

                // Check if another valid subarray exists before this one
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, len + best[left - 1]);
                }

                // Store shortest valid subarray so far
                best[right] = Math.min(
                    right > 0 ? best[right - 1] : INF,
                    len
                );
            } 
            else {
                // No new subarray ending at right
                best[right] = right > 0 ? best[right - 1] : INF;
            }
        }

        return ans == INF ? -1 : ans;
    }
}