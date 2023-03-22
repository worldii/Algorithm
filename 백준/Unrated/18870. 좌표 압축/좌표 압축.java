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

		int n = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq =new PriorityQueue<>();
		TreeMap <Integer, Integer> map = new TreeMap<>();
		HashSet<Integer> set = new HashSet<>();
		st = new StringTokenizer(br.readLine());

		int []arr =new int [n];
		for (int i= 0 ; i< n  ; i++) {
			arr[i]= (Integer.parseInt(st.nextToken()));
			set.add(arr[i]);
		}
		for (Integer num : set) {

			pq.add(num);
		}

		int cnt =0;

		while (!pq.isEmpty()){
			map.put(pq.peek(), cnt++);
			pq.poll();
		}
		StringBuilder sb = new StringBuilder();
		for (int i= 0 ; i< n ; i++) {
			arr[i] = map.get(arr[i]);
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb.toString());

		//System.out.println(Arrays.toString(arr));
	}
}
