class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        
        int[] suffix = new int[n + 1];
        suffix[n] = m;
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suffix[i] = j + 1;
        }
        
        int[] result = new int[m];
        int idx = 0;
        boolean changed = false;
        
        for (int i = 0; i < n && idx < m; i++) {
            if (word1.charAt(i) == word2.charAt(idx)) {
                
                result[idx++] = i;
            } else if (!changed && suffix[i + 1] <= idx + 1) {
                
                result[idx++] = i;
                changed = true;
            }
        }
        
        return idx == m ? result : new int[0];
    }
}