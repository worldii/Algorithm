import java.util.*;
class Solution {
    static class Node {
        int x;
        int y;
        int flag;
        Node (int x, int y, int flag) {
            this.x= x;
            this.flag = flag;
            this.y= y;
        }
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy= {0,0,-1,1};
    
    public int solution(String[] maps) {
        
        int answer = 0;
        Node start =null;
        Node end = null;
        for (int i = 0 ; i < maps.length ; i++) {
            answer = maps[i].length();
            for (int j = 0 ; j< maps[i].length() ; j++) {
                if (maps[i].charAt(j) == 'S') {
                    start = new Node(i,j, 0);
                }
                else if (maps[i].charAt(j) == 'E') {
                    end = new Node(i,j, 0);
                }  
            }
        }
        
        Queue<Node> q= new LinkedList<>();
        q.add(start);
        boolean [][][] check = new boolean[101][101][2];
        int [][][] dist = new int[101][101][2];
        check[start.x][start.y][0] = true;
        
        while (!q.isEmpty()) {
            Node temp = q.poll();
            if (temp.x == end.x && temp.y == end.y && temp.flag == 1) {
                return dist[temp.x][temp.y][1];
            }
            for (int i = 0 ; i< 4; i++) {
                int nextX = temp.x + dx[i];
                int nextY= temp.y +dy[i];
                if (0<= nextX && nextX < maps.length && 0<= nextY && nextY < answer) {
                    if (maps[nextX].charAt(nextY) == 'X') continue;
                    if (maps[nextX].charAt(nextY)  == 'L') {
                        if (check[nextX][nextY][1]) continue;
                        check[nextX][nextY][1] = true;
                        dist [nextX][nextY][1] = dist[temp.x][temp.y][temp.flag] +1;
                        q.add(new Node(nextX, nextY, 1));   
                    }
                    else {
                        if (check[nextX][nextY][temp.flag]) continue;
                                                check[nextX][nextY][temp.flag] = true;

                        dist [nextX][nextY][temp.flag] = dist[temp.x][temp.y][temp.flag] +1;
                        q.add(new Node(nextX, nextY, temp.flag)); 
                        
                    }
                    
                }  
            }
            
        }
        return -1;
    }
}