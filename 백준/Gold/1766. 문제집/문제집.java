import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;
	public static int n;


	public static void main(String[] args) throws IOException {

		st= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m  = Integer.parseInt(st.nextToken());
		int []in =new int [n+1];

		ArrayList<ArrayList<Integer>> graph =new ArrayList<>();
		for (int i = 0 ; i<= n ; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0 ; i<  m ; i++) {
			st= new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			in[b]++;

			graph.get(a).add(b);
		}
		PriorityQueue<Integer> q= new PriorityQueue<>();

		int vVisit = 0;
		for (int i = 1 ; i<=n ; i++) {
			if (in[i] ==0) {
				in[i] = -1;
				q.add(i);
			}
		}

			while (!q.isEmpty()) {
				int tempNum = q.peek();
				q.poll();


				System.out.print(tempNum+" ");

				for (int i = 0 ; i< graph.get(tempNum).size() ; i++) {

					int num = graph.get(tempNum).get(i);
					if (--in[num]==0) {
						q.add(num);
					}
				}

			}



	}
}
