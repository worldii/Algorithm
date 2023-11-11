import java.util.*;
class Solution {
    class Node {
        int x;
        int y;
        int cnt ;
        String str;
        
        Node (int x, int y, int cnt, String str ) {
            this.x= x;
            this.y= y;
            this.cnt= cnt;
            this.str= str;
        }
    }
    
    public int[] dx = {1,0,0,-1};
    public int[] dy = {0,-1,1,0};
    
    public Map<Integer,String> map = new HashMap<>();
    public ArrayList<Node> list = new ArrayList<>();
    
    public String bfs(int x, int y, int r, int c, int n, int m, int k) {
        
        PriorityQueue<Node> q= new PriorityQueue<>((a,b)-> {
            if (!a.str.equals(b.str)) {
                return a.str.compareTo(b.str);
            }
            return a.cnt - b.cnt;
        });
        
        q.add(new Node(x-1, y-1, 0, ""));
        
        boolean[][][] check = new boolean[n][m][k+1];
        check[x-1][y-1][0]= true;
        
        while (!q.isEmpty()){
            Node temp = q.poll();
            
            if (temp.x == r-1 && temp.y == c-1 && temp.cnt == k )  {
                return temp.str;
            }
            if (temp.cnt==k) continue;
            
            for (int i = 0 ; i< 4; i++) {
                int nextX = dx[i] +temp.x;
                int nextY = dy[i] + temp.y;
                
                if (0 >nextX || nextX>=n || 0 >nextY || nextY>= m ) continue;
                
                if (check[nextX][nextY][temp.cnt + 1]) {
                    continue;
                }
                check[nextX][nextY][temp.cnt + 1] = true;
                
                q.add(new Node(nextX, nextY, temp.cnt+1, temp.str+map.get(i)));
            }
        }
        return "impossible";
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        map.put(0,"d");
        map.put(1,"l");
        map.put(2,"r");
        map.put(3,"u");
        
        int dist = Math.abs(x-r) + Math.abs(y-c);
        if (dist > k || Math.abs(dist - k) %2!=0)  return "impossible";
        boolean[][][] check = new boolean[n][m][k+1];
        dfs(x-1,y-1,0,r,c,n,m,k, "", check);
        return answer;
    
    }
    private String answer;
    private void dfs(int curX, int curY, int curK, int r, int c, int n, int m, int k, String dir, boolean[][][]check){
        if (answer!= null) return;
        if (check[curX][curY][curK]) return;
        check[curX][curY][curK] = true;
        
        if (curX == r-1 && curY == c-1 && curK == k) {
            answer = dir;
            return ;
        }
        if (curK>=k) return;
        for (int i = 0 ; i < 4 ;i++){
            int nextX = dx[i] + curX;
            int nextY = dy[i] + curY;
            if (0 >nextX || nextX>=n || 0 >nextY || nextY>= m ) continue;
            dfs(nextX, nextY, curK+1, r, c, n, m, k, dir+ map.get(i), check);
        }
    }
}