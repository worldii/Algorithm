import java.util.*;
class Solution {
    class Node {
        int next;
        int cost;
        Node (int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }
    
 
    public ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public boolean[] isGate;
    public boolean[] isSummits;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        
        isGate = new boolean[n+1];
        isSummits = new boolean[n+1];
        
        for (int i = 0 ; i<= n ; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0 ; i< paths.length ; i++){
            graph.get(paths[i][0]).add(new Node(paths[i][1], paths[i][2]));
            graph.get(paths[i][1]).add(new Node(paths[i][0], paths[i][2]));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> {
          if (a.cost != b.cost) return a.cost -b.cost;
          else return a.next- b.next;
        });
        int [] distance= new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i = 0 ; i<gates.length ; i++) {
            isGate[gates[i]] = true;
            pq.add(new Node(gates[i], 0));
            distance[gates[i]] = 0;
        }
        
        for (int i = 0 ; i< summits.length ;i++){
            isSummits[summits[i]] = true;
        }
        List<Node> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            if (isSummits[temp.next]) {
                list.add(temp);
                continue;
            }
            if (distance[temp.next] < temp.cost) continue;
            for (int i = 0 ; i< graph.get(temp.next).size(); i++){
                Node next = graph.get(temp.next).get(i);
                if (isGate[next.next]) continue;
                if (distance[next.next] > Math.max(distance[temp.next], next.cost)) {
                    distance[next.next] = Math.max(distance[temp.next], next.cost);
                    pq.add(new Node(next.next, distance[next.next]));
                }
            }
        }
        Collections.sort(list, (a,b)-> {
          if (a.cost != b.cost) return a.cost -b.cost;
          else return a.next- b.next;
        });
        
        return new int[] {list.get(0).next, list.get(0).cost};
    }
  
}