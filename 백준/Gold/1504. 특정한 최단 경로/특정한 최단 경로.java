import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;

	static class Cor {
		int vertex;
		long cost ;
		Cor (int vertex, long cost) {
			this.vertex = vertex;
			this.cost= cost;
		}
	}

	public static  int INF = 100000001;


	public static int v,e;
	public static long dikstra(int start , int end ) {

		if (start == end ) return 0;
		long [] dist = new long [v+1];
		boolean [] check = new boolean [v+1];

		Arrays.fill(dist, INF);

		dist[start] = 0;

		PriorityQueue<Cor> pq = new PriorityQueue<>(new Comparator<Cor>() {
			@Override
			public int compare(Cor o1, Cor o2) {
				return Long.compare(o1.cost, o2.cost);
			}
		});

		pq.add(new Cor(start, 0));

		int eCount = 0;

		while (!pq.isEmpty()) {
			Cor temp = pq.poll();

			if (dist[temp.vertex] < temp.cost) continue;
			for (int i = 0 ; i< graph.get(temp.vertex).size() ; i++) {
				if (dist[graph.get(temp.vertex).get(i).vertex] >  dist[temp.vertex] + graph.get(temp.vertex).get(i).cost )
				{
					dist[graph.get(temp.vertex).get(i).vertex] = dist[temp.vertex] + graph.get(temp.vertex).get(i).cost;
					pq.add(new Cor(graph.get(temp.vertex).get(i).vertex, dist[graph.get(temp.vertex).get(i).vertex]));

				}
			}
		}

		return dist[end];
	}
	public static ArrayList<ArrayList<Cor>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		 v = Integer.parseInt(st.nextToken());
		 e= Integer.parseInt(st.nextToken());

		for (int i = 0 ; i<= v ; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i= 0 ; i< e; i++) {
			int a,b,c;
			st= new StringTokenizer(br.readLine());
			a= Integer.parseInt(st.nextToken());
			b= Integer.parseInt(st.nextToken());
			c= Integer.parseInt(st.nextToken());
			graph.get(a).add(new Cor(b,c));
			graph.get(b).add(new Cor(a,c));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());


		long sum2 = (dikstra(1, start)+ dikstra(end,v)+ dikstra(start, end));
		long sum3 = (dikstra(1, end)+ dikstra(start,v)+dikstra(end, start));

		if (sum2 >=INF && sum3 >=INF) System.out.println(-1);
		else if (sum2>=INF) System.out.println(sum3);
		else {
			System.out.println(Math.min(sum2, sum3));
		}


	}
}
