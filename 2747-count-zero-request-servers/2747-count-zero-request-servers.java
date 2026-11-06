import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];

        // Pair each query with its original index: [queryTime, originalIndex]
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort logs by timestamp
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        // Sort queries by timestamp
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));

        int[] count = new int[n + 1]; // Server IDs are 1-indexed up to n
        int activeServers = 0;
        int left = 0;
        int right = 0;
        int totalLogs = logs.length;

        for (int[] query : sortedQueries) {
            int qTime = query[0];
            int qIdx = query[1];
            int windowStart = qTime - x;

            // Expand right boundary: include all logs with time <= qTime
            while (right < totalLogs && logs[right][1] <= qTime) {
                int serverId = logs[right][0];
                if (count[serverId] == 0) {
                    activeServers++;
                }
                count[serverId]++;
                right++;
            }

            // Shrink left boundary: exclude all logs with time < windowStart
            while (left < totalLogs && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                count[serverId]--;
                if (count[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }

            // Zero-request servers = total servers - active servers
            ans[qIdx] = n - activeServers;
        }

        return ans;
    }
}