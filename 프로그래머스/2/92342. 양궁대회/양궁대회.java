class Solution {
    /*
    n : the number of arrows
    info[][] : 10~0 count that apeach shoot
    max diff Score 
    1. if, lion couldnt win, then -1 returns
    2. if both shoot k score, then compare score between them and if someone shoot many times, then get k score. if equals, then apeach get k scores.
    3. if (a=b=0) nobody get score
    4. calculate total score of each player.
    5. decide winner that player does have more total score if same scores, then apeach player wins.
    */
    public int check (int []result , int [] info) {
        int aCount = 0;
        int bCount =0;
        for (int i = 0 ; i< result.length; i++ ) {
            if (info[i] == 0 && result[i] == 0 ) continue;
            if (info[i] >= result[i]) aCount += (10- i);
            else bCount+= (10-i);
        }
        if (bCount <= aCount) return -1;
        else return bCount - aCount;
    }
    public int [] answer = new int [] {-1};
    public int maxDiff =0;
    public int maxKey = -1;
    public int makeKey(int [] result) {
        for (int i = result.length -1; i>= 0 ; i--) {
            if (result[i]!=0) return i;
        }
        return -1;
    }
    public void dfs(int start ,int end, int []result, int[] info, int leftCount) {
        if (leftCount == 0) {
            int number = check(result, info); 
            if (maxDiff < number) {
                maxDiff = number;
                maxKey = makeKey(result);
                answer = new int [result.length];

                for (int i = 0 ; i< result.length ; i++) {
                    answer[i] = result[i];
                }
            }
           else if (maxDiff == number) {
             int resultKey =  makeKey(result);
             if (maxKey <resultKey) {
                 maxKey = resultKey;
                 answer = new int [result.length];

                for (int i = 0 ; i< result.length ; i++) {
                    answer[i] = result[i];
                }
               }
           }
            return ;
        }
        if (start == end) return ;
        for (int j= 0 ; j <= leftCount ; j++) 
        {
            if (leftCount <  j) continue;
            result[start] = j;
            dfs(start+1, end, result, info, leftCount - j);
            result[start]= 0;
        }        
    }
    public int[] solution(int n, int[] info) {
        int [] result = new int [11];
        dfs(0, result.length, result, info,  n);
        return answer;
    }
}