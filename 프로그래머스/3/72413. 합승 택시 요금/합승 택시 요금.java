import java.util.*;

class Solution {
    // fares 배열의 크기 1... n-1
    // 각 지점은 1~n , 서로 다른 값
    // 존재하는 경우만 입력으로 주어진다. 
    // 합승할 때 가장 작은 비용을 구해라. 
    // 합승을 안할 수도 있다. 
    // n= 지점갯수, s는 출발, a,b각각 fares 는 간격
    // 다익스트라 각각 의 노드를 구한 다음, 이렇게 싹 구함. 
    // a-s s-b 
    // a-1 1-b s-1
    // a-2 2-b s-2
    // a에서 다익스트라
    // b에서 다익스트라
    // s에서 다익스트라 
    public int MAX_VALUE = 987654321;
    public void dikstra(int[] distance, int start) {
        Arrays.fill(distance, MAX_VALUE);
        distance[start] =0;
        PriorityQueue<Node> q = new PriorityQueue<>((a,b) -> a.cost -b.cost);
        q.add(new Node(start, 0));
        while (!q.isEmpty()) {
            Node temp = q.poll();
            if (distance[temp.node] < temp.cost) continue;
            for (int i = 0 ; i < graph.get(temp.node).size() ; i++) {
                int nextNode = graph.get(temp.node).get(i).node;
                int nextCost = graph.get(temp.node).get(i).cost;
                if (distance[nextNode] > distance[temp.node] + nextCost) {
                    distance[nextNode] = distance[temp.node] + nextCost;
                    q.add(new Node(nextNode, distance[nextNode]));
                }
            }   
        }
    }
    static class Node {
        int node;
        int cost;
        Node(int node, int cost) {
            this.node= node;
            this.cost= cost;
        }
    }
    public int[][] distance; // 0은 출발, a는1, b는 2
    
    public ArrayList<ArrayList<Node>> graph= new ArrayList<>();
    public int solution(int n, int s, int a, int b, int[][] fares) {
        distance= new int[3][n+1];
        for (int i = 0 ; i<= n ; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0 ; i< fares.length ; i++) {
            graph.get(fares[i][0]).add(new Node(fares[i][1], fares[i][2]));
            graph.get(fares[i][1]).add(new Node(fares[i][0], fares[i][2]));
        }
        
        dikstra(distance[0], s);
        dikstra(distance[1], a);
        dikstra(distance[2], b);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1 ; i<= n ; i++) {
            if (distance[1][i] >= MAX_VALUE) continue;
            if (distance[2][i] >= MAX_VALUE) continue;
            if (distance[0][i] >= MAX_VALUE) continue;
            answer = Math.min(answer, distance[1][i] + distance[2][i] + distance[0][i]);
        }
        return answer;
    }
}