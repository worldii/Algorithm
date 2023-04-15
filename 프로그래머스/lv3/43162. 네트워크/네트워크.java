import java.util.*;
class Solution {
      static void dfs(int start, boolean[] check, int [][] computers)  {
        check[start] = true;
          
          Queue<Integer> q= new LinkedList<>();
          q.add(start);
          System.out.println(start);
          while (!q.isEmpty()) {
              int temp = q.peek();
              q.poll();
              for (int i = 0 ; i< computers[temp].length ; i++ ) {
                  if (check[i]) continue;
                if (computers[temp][i] == 1) {check[i] = true;
                                             q.add(i); 
                                             }
                  
            }
          }
            
        }
    public int solution(int n, int[][] computers) {
       
        int answer = 0;
        
        boolean [] check = new boolean[computers.length];
        for (int i = 0 ; i< computers.length ; i++) {

            if (!check[i] ) {answer++; dfs(i, check, computers);}
        }
        
        return answer;
    }
}