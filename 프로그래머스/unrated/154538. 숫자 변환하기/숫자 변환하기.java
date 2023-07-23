import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        boolean [] check = new boolean[1000001];
        check[x] = true;
        Queue<int[]> q= new LinkedList<>();
        q.add(new int[]{x,0});
        while (!q.isEmpty()) {
            int [] temp = q.poll();
            if (temp[0] == y) {
                return temp[1];
            }
            int next = temp[0] + n;
           
            if (next<=y && !check[next]) {
                check[next] = true;
                q.add(new int[]{next, temp[1]+1});
            }
            next = temp[0] * 2;
            if (next<=y && !check[next]) {
                check[next] = true;
                q.add(new int[]{next, temp[1]+1});
            }
            next = temp[0] * 3;
            if (next<=y && !check[next]) {
                check[next] = true;
                q.add(new int[]{next, temp[1]+1});
            }
        }
        return -1;
    }
}