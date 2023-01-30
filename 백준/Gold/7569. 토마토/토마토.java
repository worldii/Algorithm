import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Cor {
	int x;
	int y;
	int z;

	Cor(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}

public class Main {

	static int[][][] arr = new int[101][101][101];
	static List<Cor> list = new ArrayList<Cor>();
	static int[][][] checked = new int[101][101][101];
	static int[][][] dist = new int[101][101][101];

	static int[] dx = { -1, 1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };
	static int C, R, H;

	public static void bfs() {
		Queue<Cor> q = new LinkedList<>();

		for (int i = 0 ; i< list.size() ; i++) {
			checked[list.get(i).x][list.get(i).y][list.get(i).z] = 1;
			q.add(new Cor(list.get(i).x, list.get(i).y, list.get(i).z));

		}
		while (!q.isEmpty()) {
			Cor temp = q.poll();
			for (int i = 0; i < 6; i++) {
				int newX = dx[i] + temp.x;
				int newY = dy[i] + temp.y;
				int newZ = dz[i] + temp.z;
				if (0 <= newX && newX < R) {
					if (0 <= newY && newY < C) {
						if (0 <= newZ && newZ < H) {
							if (checked[newX][newY][newZ] == 0 && arr[newX][newY][newZ] == 0) {
								checked[newX][newY][newZ] = 1;
								arr[newX][newY][newZ] = 1;
								q.add(new Cor(newX, newY, newZ));
								dist[newX][newY][newZ] = dist[temp.x][temp.y][temp.z] + 1;
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		C = sc.nextInt();
		R = sc.nextInt();
		H = sc.nextInt();

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					arr[i][j][k] = sc.nextInt();
					if (arr[i][j][k] == 1) {
						list.add(new Cor(i, j, k));
					}
				}
			}
		}

	bfs();


		int maxNum = -1;
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j][k] == 0) {
						System.out.print(-1);
						return;
					}
					//System.out.println(arr[i][j][k]);
					maxNum = Math.max(maxNum, dist[i][j][k]);
				}
			}
		}
		System.out.print(maxNum);
	}
}
