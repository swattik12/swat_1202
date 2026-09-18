class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        List<String> ans = new ArrayList<>();

        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try to create a valid interval starting from
        // the first occurrence of each character
        for (int c = 0; c < 26; c++) {
            if (last[c] == -1) continue;

            int l = first[c];
            int r = last[c];
            boolean valid = true;

            for (int i = l; i <= r; i++) {
                int x = s.charAt(i) - 'a';

                // Character occurs before l,
                // so this substring cannot be valid
                if (first[x] < l) {
                    valid = false;
                    break;
                }

                // Expand interval if needed
                r = Math.max(r, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        // Greedily select non-overlapping intervals
        int end = -1;

        for (int[] interval : intervals) {
            if (interval[0] > end) {
                ans.add(s.substring(interval[0], interval[1] + 1));
                end = interval[1];
            }
        }

        return ans;
    }
}