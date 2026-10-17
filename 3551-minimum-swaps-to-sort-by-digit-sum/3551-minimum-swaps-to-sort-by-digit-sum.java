class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        int[][] elements = new int[n][3];
        for (int i = 0; i < n; i++) {
            elements[i][0] = nums[i];
            elements[i][1] = getDigitSum(nums[i]);
            elements[i][2] = i;
        }

        Arrays.sort(elements, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        
        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
             
            if (visited[i] || elements[i][2] == i) {
                if (elements[i][2] == i) {
                    visited[i] = true;
                    cycles++;
                }
                continue;
            }

           
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = elements[curr][2];
            }
            cycles++;
        }

        return n - cycles;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}