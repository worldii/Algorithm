import java.util.*;
class Solution {
    static class Node{
        int curAl;
        int curCop;
        int cnt;
        Node(int curAl, int curCop, int cnt) {
            this.curCop = curCop;
            this.curAl= curAl;
            this.cnt = cnt;
        }
    }
    public int[][] problems;
    public int bfs(int endAl, int endCop, int curAl, int curCop) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)-> a.cnt-b.cnt);
        boolean[][][] check = new boolean[1000][1000][200];
        pq.add(new Node(curAl, curCop, 0));
        check[curAl][curCop][0]= true;
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            if (temp.curCop >= endCop && temp.curAl >=endAl) return temp.cnt;
            // 코딩 늘리기 
            if (temp.curCop <150 && !check[temp.curAl][temp.curCop+1][temp.cnt+1]) {
                check[temp.curAl][temp.curCop+1][temp.cnt+1]= true;
                pq.add(new Node(temp.curAl, temp.curCop+1, temp.cnt+1));
            }
            // 알고 늘리기 
            if (temp.curAl < 150 && !check[temp.curAl+1][temp.curCop][temp.cnt+1]) {
                check[temp.curAl+1][temp.curCop][temp.cnt+1]= true;
                pq.add(new Node(temp.curAl+1, temp.curCop, temp.cnt+1));
            }
            for (int i = 0 ; i< this.problems.length ; i++) {
                if (this.problems[i][0] <= temp.curAl && this.problems[i][1] <= temp.curCop){
    if (check[temp.curAl+this.problems[i][2]][temp.curCop+ this.problems[i][3] ][temp.cnt+ this.problems[i][4]]) continue;
    check[temp.curAl+this.problems[i][2]][temp.curCop+ this.problems[i][3] ][temp.cnt+ this.problems[i][4]]= true;
             
                pq.add(new Node(temp.curAl+ this.problems[i][2],temp.curCop + this.problems[i][3],temp.cnt+ this.problems[i][4]));
                }
            }
        }
        return -1;
    }
    public int maxCop;
    public int maxAl;
    public int solution(int alp, int cop, int[][] problems) {
        this.problems = problems;
        for (int i = 0 ;i< problems.length ; i++) {
            maxAl= Math.max(maxAl, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        int answer = bfs(maxAl, maxCop, alp, cop);
        return answer;
    }
}