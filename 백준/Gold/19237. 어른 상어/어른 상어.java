import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class SharkLoc {
		int x;
		int y;
		Shark shark;

		SharkLoc(int x, int y, Shark shark) {
			this.x = x;
			this.y = y;
			this.shark = shark;
		}
	}

	static class SharkController {
		private Shark[][] map;
		private ArrayList<int[][]> sharkInfos;
		private Queue<SharkLoc> q;

		public int cnt = 0;
		private int[] dx = {0, -1, 1, 0, 0};
		private int[] dy = {0, 0, 0, -1, 1};

		SharkController(Shark[][] map, ArrayList<int[][]> sharkInfos) {
			this.map = map;
			this.sharkInfos = sharkInfos;
			q = new LinkedList<>();
		}

		public void start() {
			while (true) {
				move();
				updateSmell();
				if (cnt++== 1000) {
					break;
				}
				if (checkTime()) {
					break;
				}
			}
			if (cnt > 1000)
				System.out.println(-1);
			else
				System.out.println(cnt);
		}

		public boolean checkTime() {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] != null && map[i][j].num > 0) {
						if (map[i][j].num != 1)
							return false;
					}
				}
			}
			return true;
		}

		public void updateSmell() {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] != null && map[i][j].num == 0) {
						map[i][j].timeLimit--;
						if (map[i][j].timeLimit == 0)
							map[i][j] = null;
					}

				}
			}
		}

		public void move() {
			// 샤크 현재 상태 담아줍니다.
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] != null && map[i][j].num > 0) {
						Shark shark = new Shark(map[i][j].timeLimit, map[i][j].num, map[i][j].dir, map[i][j].smell);
						q.add(new SharkLoc(i, j, shark));
						// 샤크가 떠나간 거 0 으로 표시함.
						// 근데 냄새는 남겨져 있음
						map[i][j].num = 0;
					}
				}
			}

			Queue<SharkLoc> tempList = new LinkedList<>();
			// 샤크 움직인거 map 에 다시 배치합니다.
			while (!q.isEmpty()) {
				SharkLoc temp = q.poll();
				int loc = 0;
				int size = checkLocNum(temp);
				if (size == 0) {
					// 인접한 칸이 없다;
					// 자신 방향으로
					loc = getNoLoc(temp);
				} else {
					loc = getLoc(temp);
				}
				int nx = temp.x + dx[loc];
				int ny = temp.y + dy[loc];
				tempList.add(new SharkLoc(nx, ny, new Shark(k, temp.shark.num, loc, temp.shark.smell)));
			}

			while (!tempList.isEmpty()) {
				SharkLoc temp = tempList.poll();
				int nx = temp.x;
				int ny = temp.y;
				if (map[nx][ny] != null && map[nx][ny].num > 0) {
					if (map[nx][ny].num > temp.shark.num) {
						map[nx][ny] = temp.shark;
					}
				} else {
					// 상어가 없다
					map[nx][ny] = temp.shark;
				}
			}
		}

		// 인접한 칸 (null 이 아닐 때만 호출)
		public int getNoLoc(SharkLoc temp) {
			int curX = temp.x;
			int curY = temp.y;
			int curDir = temp.shark.dir;

			int[] dir = sharkInfos.get(temp.shark.num)[curDir];

			for (int i = 0; i < 4; i++) {
				int nX = curX + dx[dir[i]];
				int nY = curY + dy[dir[i]];
				if (0 <= nX && nX < n && 0 <= nY && nY < n) {
					if (temp.shark.num == map[nX][nY].smell) {
						return dir[i];
					}
				}
			}
			return 0;
		}

		public int getLoc(SharkLoc temp) {
			int curX = temp.x;
			int curY = temp.y;
			int curDir = temp.shark.dir;
			int[] dir = sharkInfos.get(temp.shark.num)[curDir];
			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[dir[i]];
				int ny = curY + dy[dir[i]];
				if (0 <= nx && nx < n && 0 <= ny && ny < n) {
					if (map[nx][ny] == null) {
						return dir[i];
					}
				}
			}
			return 0;
		}

		public int checkLocNum(SharkLoc temp) {
			int size = 0;
			// check Loc 인접 칸
			// 인접 칸 중 아무 냄새가 없는 곳이 있다.
			// 아무 냄새 없는 곳 많으면 우선 순위

			// 인접 칸이 없다.
			// num 은 상어의 숫자
			// dir 은 상어의 방향
			int curX = temp.x;
			int curY = temp.y;
			int curDir = temp.shark.dir;
			int[] dir = sharkInfos.get(temp.shark.num)[curDir];

			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[dir[i]];
				int ny = curY + dy[dir[i]];
				if (0 <= nx && nx < n && 0 <= ny && ny < n) {
					if (map[nx][ny] == null) {
						size++;
					}
				}
			}

			return size;
		}

	}

	static class Shark {
		int dir;
		int timeLimit;
		int num;
		int smell;

		@Override
		public String toString() {
			return "Shark{" +
				"dir=" + dir +
				", timeLimit=" + timeLimit +
				", num=" + num + " smell" + smell +
				'}';
		}

		Shark(int timeLimit, int num) {
			this.num = num;
			this.timeLimit = timeLimit;
		}

		Shark(int timeLimit, int num, int dir, int smell) {
			this.timeLimit = timeLimit;
			this.num = num;
			this.dir = dir;
			this.smell = smell;
		}
	}

	public static int n, m, k;

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		Shark[][] arr;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new Shark[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num > 0) {
					arr[i][j] = new Shark(k, num);
					arr[i][j].smell = num;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int[] loc = new int[m + 1];
		for (int i = 1; i <= m; i++) {
			loc[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] != null && arr[i][j].num > 0) {
					for (int t = 1; t <= m; t++) {
						if (arr[i][j].num == t) {
							arr[i][j].dir = loc[t];
						}
					}
				}
			}
		}

		ArrayList<int[][]> sharkInfos = new ArrayList<>();
		sharkInfos.add(new int[][] {});

		for (int i = 1; i <= m; i++) {
			int[][] info = new int[5][4];
			for (int t = 1; t <= 4; t++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < 4; j++) {
					info[t][j] = Integer.parseInt(st.nextToken());
				}
			}
			sharkInfos.add(info);
		}
		// for (int i = 1; i <= m; i++) {
		// 	for (int j = 1 ;j<= 4; j++) {
		// 		for (int t = 0 ;t < 4 ; t++ ){
		// 			System.out.print(sharkInfos.get(i)[j][t]+ " ");
		// 		}
		// 		System.out.println();
		// 	}
		// }

		SharkController sharkController = new SharkController(arr, sharkInfos);
		sharkController.start();
	}
}