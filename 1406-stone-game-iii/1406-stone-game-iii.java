class Solution {
    int n;
    int[] dp ;

    private int solve(int[] stone , int i){
        if( i >= n ){
            return 0;
        }

        if(dp[i] != -1){
            return dp[i];
        }
        
        int result = stone[i] - solve(stone ,i +1);

        if(i+1 < n){
            result = Math.max(result ,stone[i] + stone[i+1] - solve(stone ,i +2));
        }
        if(i+2 < n){
            result = Math.max(result ,stone[i] + stone[i+1]+stone[i+2] - solve(stone ,i +3));
        }   

        return dp[i] = result;
    }


    public String stoneGameIII(int[] stone) {
        n = stone.length;
        dp = new int[n];
        Arrays.fill(dp , -1);

        int diff = solve(stone , 0);    // This solve will give Alice - Bob4

        if(diff > 0){
            return "Alice";
        }
        else if(diff < 0) {
            return "Bob";
        }

        return "Tie";
            
        
    }
}