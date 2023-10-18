import java.util.*;
class Solution {
    public static int[] dx = {-1,1,0,0};
    public static int []dy = {0,0,-1,1};
    
    public static int m;
    public static int n;
    public static boolean[][] check;
    
    public int bfs ( int startX, int startY,int[][]arr) {
        int cnt = 1;
        int color = arr[startX][startY];
        Queue<int[]> q= new LinkedList<>();
        q.add(new int[]{startX, startY});
        check[startX][startY] = true;
        
        while (!q.isEmpty()) {
            int [] temp = q.poll();
            for (int i = 0 ; i< 4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];
                if (0> nextX || nextX >= m || 0> nextY ||  nextY >= n ) continue;
                if (check[nextX][nextY]) continue;
                if (arr[nextX][nextY] != color ) continue;
                q.add(new int[]{nextX,nextY});
                check[nextX][nextY]= true;
                cnt++;
            }
        }
        return cnt;
    }
    public int[] solution(int m, int n, int[][] picture) {
        this.m = m;
        this.n = n;
        check = new boolean[m+1][n+1];
        int maxSizeOfOneArea = 0;
        int numberOfArea = 0;

        for (int i = 0 ; i< m ; i++) {
            for (int j = 0 ; j< n ; j++) {
                if(picture[i][j] != 0 && !check[i][j] ) {
                    maxSizeOfOneArea= Math.max(bfs(i,j,picture),maxSizeOfOneArea);
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
        // 1 1 1 0
        // 1 2 2 0 
        // 1 0 0 1
        // 0 0 0 1
        // 0 0 0 3 
        // 0 0 0 3 
    }
}