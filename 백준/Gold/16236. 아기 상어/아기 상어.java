
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Cor {
	int x;
	int y;

	Cor(int x, int y) {
		this.x = x;
		this.y = y;

	}
};

class Shark implements Comparable<Shark> {
	Cor cor;
	int size;

	Shark(Cor cor, int size) {
		this.cor = cor;
		this.size = size;
	}

	@Override
	public int compareTo(Shark j) {
		if (this.size != j.size) {
			if (this.size > j.size)
				return 1;
			else
				return -1;
		} else {
			if (this.cor.x != j.cor.x) {
				if (this.cor.x > j.cor.x)
					return 1;
				else
					return -1;
			} else {
				if (this.cor.y > j.cor.y)
					return 1;
				else
					return -1;
			}
		}

	}

}

public class Main {
	public static int n;
	public static int[][] arr = new int[21][21];
	public static List<Shark> list = new ArrayList<>(21);
	public static Cor start = null;
	public static Cor end = null;

	public static int sharkSize = 2;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	static int[][] dist = new int[21][21];

	public static int bfs(int startx, int starty) {
		Queue<Cor> q = new LinkedList<>();
		int[][] checked = new int[21][21];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		int tempSharkSize = sharkSize;
		dist[startx][starty] = 0;
		list.clear();

		q.add(new Cor(startx, starty));
		while (!q.isEmpty()) {
			Cor temp = q.peek();
			q.poll();
			for (int i = 0; i < 4; i++) {
				int newx = dx[i] + temp.x;
				int newy = dy[i] + temp.y;
				if (0 <= newx && newx < n && 0 <= newy && newy < n) {
					if (checked[newx][newy] == 1)
						continue;
					checked[newx][newy] = 1;
					if (arr[newx][newy] == 0 || arr[newx][newy] == tempSharkSize) {
						q.add(new Cor(newx, newy));
						dist[newx][newy] = dist[temp.x][temp.y] + 1;
					} else if (arr[newx][newy] < tempSharkSize) {
						q.add(new Cor(newx, newy));
						dist[newx][newy] = dist[temp.x][temp.y] + 1;
						list.add(new Shark(new Cor(newx, newy), dist[newx][newy]));

					}
				}
			}
		}
		Collections.sort(list);
	
		if (list.size() == 0) {
			// 이제 먹을 물고기 없다는 뜻
			return -1;

		}

		end = new Cor(list.get(0).cor.x, list.get(0).cor.y);
		arr[list.get(0).cor.x][list.get(0).cor.y] = 0;

		return list.get(0).size;

	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		// 입력 받기
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 9) {
					start = new Cor(i, j);
					arr[i][j] = 0;
				}
			}
		}
		int distSum = 0;
		int trySum = 0;
		while (true) {
			int distResult = bfs(start.x, start.y);
			//System.out.println("distResult" + distResult);
			if (distResult == -1)
				break;
			distSum += distResult;
			trySum++;

			if (trySum == sharkSize) {
				trySum = 0;
				sharkSize++;
			}

			start = new Cor(end.x, end.y);
		}
		// 1. bfs 를 돌린다. ->거리 순으로 가장 가까운 곳 endx, endy 로하고 반환
		// 2. endx, endy를 또새로운 startx, starty로 하고 돌린다.
		// bfs ->O (n^2) 인데 이거를n^2한다고 하면 o(n^4)n이작아서 시간 초과 가 안난다.
		System.out.println(distSum);
	}

}
