import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Node {
		int cur;
		int earCnt;

		Node() {

		}
		boolean earlyFlag ;
		Node (int cur, int earCnt ,boolean earlyFlag) {
			this.cur= cur;
			this.earCnt = earCnt;
			this.earlyFlag =earlyFlag;
		}

		@Override
		public String toString() {
			return "Node{" +
				"cur=" + cur +
				", earCnt=" + earCnt +
				", earlyFlag=" + earlyFlag +
				'}';
		}
	}
	public static Node []list ;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static Node dfs(int cur,boolean[] visited) {

		if (graph.get(cur).size() == 0) {
			list[cur] = new Node(cur, 0, false);
			return list[cur];
		}

		int earCnt = 0;
		boolean earlyFlag = false; //default ;
		for (int i = 0 ; i< graph.get(cur).size() ; i++) {
			if (visited[graph.get(cur).get(i)]) continue;
			visited[graph.get(cur).get(i)] = true;
			Node temp = dfs(graph.get(cur).get(i), visited);
			earCnt += temp.earCnt;
			if (!temp.earlyFlag) {
				if (earlyFlag) continue;
				earCnt++;
				earlyFlag = true;
			}
		}
		list[cur] = new Node(cur, earCnt, earlyFlag);
		return list[cur];
	}
	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0 ; i<= n ; i++) {
			graph.add(new ArrayList<>());
		}
		list = new Node[n+1];
		int c = 0;
		boolean []visited = new boolean[n+1];
		for (int i= 0 ; i< n-1; i++) {
			int a, b;
			st= new StringTokenizer(br.readLine());
			a= Integer.parseInt(st.nextToken());
			b= Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
			c = a;
		}
		visited[c] = true;
		Node temp = dfs(c, visited);

		// for (int i = 1 ; i<= n ; i++) {
		// 	System.out.println(list[i]);
		// }
		System.out.println(list[c].earCnt);

	}
}
