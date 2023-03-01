
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;


	public static int bfs(int start,  boolean[] checked) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {start, 0});
		checked[start] = true;

		int maxDepth = -1;
		int maxStart = -1;
		int [] distCnt = new int[101];
		Arrays.fill(distCnt, -1);

		while (!q.isEmpty()) {
			int temp = q.peek()[0];
			int depth = q.peek()[1];
			q.poll();
			maxDepth = Math.max(maxDepth, depth);
			distCnt[depth] = Math.max(distCnt[depth], temp);
			for (int i = 0; i < graph.get(temp).size(); i++) {
				if (checked[graph.get(temp).get(i)])
					continue;
				checked[graph.get(temp).get(i)] = true;
				q.add(new int[] {graph.get(temp).get(i), depth+1});
			}
		}
		return distCnt[maxDepth];
	}

	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			for (int i = 0; i < 101; i++) {
				graph.add(new ArrayList<Integer>());
			}
			st = new StringTokenizer(br.readLine());


			for (int i = 0; i < cnt / 2; i++) {
				int from, end;
				from = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				graph.get(from).add(end);
			}

			boolean[] check = new boolean[101];


			System.out.println("#" + t + " " + bfs(start, check));
		}
	}
}
