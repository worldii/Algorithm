import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class Main {


	static int[] checked = new int [1001];

	static ArrayList<ArrayList<Integer>> graph= new ArrayList<>();
	public static void bfs (int start) {
		checked[start] = 1; 
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) {
			int temp = q.poll();
			
			for (int i = 0 ; i< graph.get(temp).size() ; i++) {
				int next = graph.get(temp).get(i);

				if (checked[next] == 0) {
					checked[next] =1;
					q.add(next);
				}
			}
			
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int n = sc.nextInt();
		int m = sc.nextInt();
		for (int i = 0 ; i<= n ; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int  i = 0 ; i< m ; i++) {
			int u ,v;
			u = sc.nextInt();
			v = sc.nextInt();
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
	
		int cnt = 0;
		for (int i = 1; i<= n ; i++) {
			
			if (checked[i] == 0) {
				cnt++;
				bfs(i);
			}
		}
		System.out.print(cnt);
	}
}
