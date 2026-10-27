import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];
        
        // First row: all free cells '.'
        char[] firstRow = new char[n];
        Arrays.fill(firstRow, '.');
        ans[0] = new String(firstRow);
        
        // Remaining rows: all '#' except the last column which is '.'
        for (int i = 1; i < m; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '#');
            row[n - 1] = '.';
            ans[i] = new String(row);
        }
        
        return ans;
    }
}