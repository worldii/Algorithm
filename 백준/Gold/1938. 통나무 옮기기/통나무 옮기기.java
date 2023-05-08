import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;

	// 방향은 다음과 같다.
	// 가로 = 0 
	// 세로 = 1
	// check  배열  [x좌표][y좌표][방향]
	
	static class Cor {
		int x;
		int y ;
		public Cor(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Gidung {
		int midX;
		int midY;
		// 0 이가로, 1이 세로
		int moyang;
		ArrayList<Cor> list = new ArrayList<>();
		public Gidung(int midX, int midY, int moyang, ArrayList<Cor> list) {
			this.midX = midX;
			this.midY = midY;
			this.moyang = moyang;
			this.list = list;
		}		
	}
	public static int n;
	static ArrayList<Cor> startCorList = new ArrayList<>();
	static ArrayList<Cor> endCorList = new ArrayList<>();
	public static char [][] arr = new char [n][n];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		 n = Integer.parseInt(br.readLine());	
		arr = new char [n][n];
		
		for (int i = 0 ; i< n ; i++) {
			String str = br.readLine();
			arr[i] = str.toCharArray();
			for (int j = 0 ; j< n  ; j++) {
				if (arr[i][j] == 'B') startCorList.add(new Cor(i,j));
				else if (arr[i][j] == 'E') endCorList.add(new Cor(i,j));
			}
		}
		
		Gidung start = null;
		Gidung end = null;
		// mid 의 값과, 방향을 넣어준다. 
		if (startCorList.get(0).x == startCorList.get(1).x) 
		{
			// 가로임 
			start = new Gidung(startCorList.get(1).x, startCorList.get(1).y,0,startCorList );
		}
		else {
			// 세로임
			start = new Gidung(startCorList.get(1).x, startCorList.get(1).y,1,startCorList);
		}
		
		if (endCorList.get(0).x == endCorList.get(1).x) 
		{
			// 가로임 
			end = new Gidung(endCorList.get(1).x, endCorList.get(1).y,0, endCorList);
		}
		else {
			// 세로임
			end = new Gidung(endCorList.get(1).x, endCorList.get(1).y,1,endCorList);
		}
		

		// BFS 시작.
		Queue<Gidung> q= new LinkedList<>();
		q.add(start);
		boolean [][][] check = new boolean [n][n][2];
		int [][][] dist = new int[n][n][2];
		
		check[start.midX][start.midY][start.moyang] = true;
		dist[start.midX][start.midY][start.moyang] = 0;

		while (!q.isEmpty()) {
			Gidung temp = q.poll();
			
			// 맞으면 바로 탈출.
			if (temp.midX == end.midX && temp.midY == end.midY && temp.moyang == end.moyang) {
				System.out.println(dist[temp.midX][temp.midY][temp.moyang]);
				return ;
			}
		
			// 상하 좌우 여기 서 따짐
			for (int i = 0 ;i< 4; i++) {
				if (checkFunc(temp, i)) {
					// 될 경우에
					
					int temidX =temp.list.get(1).x + dx[i];
					int temidY = temp.list.get(1).y + dy[i];
					// 상하 좌우는 모양이 변하지 않는다. 
					int teGaro = temp.moyang;
					if (check[temidX][temidY][teGaro]) continue;
					
					ArrayList<Cor> newList =new ArrayList<>();
					
					for (int j = 0 ; j< 3; j++) 
					{	
						int nextX = temp.list.get(j).x + dx[i];
						int nextY = temp.list.get(j).y + dy[i];
						newList.add(new Cor(nextX, nextY));
					}
					check[temidX][temidY][teGaro] = true;
					dist[temidX][temidY][teGaro] = dist[temp.midX][temp.midY][temp.moyang] +1;
					q.add(new Gidung(temidX, temidY, teGaro, newList));
				}
			}
			
			// checkTurn (회전 할 수 있는 지 따짐)
			if (checkTurn(temp)) {
				// turn 은 모양이 변한다. 
				int teGaro = 1- temp.moyang;
				int temidX = temp.midX;
				int temidY = temp.midY;
				if (check[temidX][temidY][teGaro]) continue;
				ArrayList<Cor> newList =new ArrayList<>();
				// 모양 가로 였으면 
				if (temp.moyang ==0) 
				{
					newList.add(new Cor(temp.midX-1, temp.midY));
					newList.add(new Cor(temp.midX, temp.midY));
					newList.add(new Cor(temp.midX+1, temp.midY));
				}
				else {
					// 모양 세로 였으면
					newList.add(new Cor(temp.midX, temp.midY-1));
					newList.add(new Cor(temp.midX, temp.midY));
					newList.add(new Cor(temp.midX, temp.midY+1));
				}
				check[temidX][temidY][teGaro] = true;
				q.add(new Gidung(temidX, temidY, teGaro, newList));
				dist[temidX][temidY][teGaro] = dist[temp.midX][temp.midY][temp.moyang] +1;
			}
		}
		// 이동할 수 없을 때
		System.out.println(0);
	}
	// 상 하  좌 우 
	public static int [] dx = { -1,1,0,0};
	public static int [] dy = {0,0, -1,1};
	private static boolean checkTurn(Gidung temp) {
		if (temp.moyang ==0) {
			// 가로 일때
			// 상하가 없어야 함
			for (int i = 0 ; i< 2; i++) {
				for (int j = 0 ; j< 3 ; j++) {
					int nextX = temp.list.get(j).x + dx[i];
					int nextY = temp.list.get(j).y + dy[i];
					if (nextX < 0 || nextX >= n || nextY <0 || nextY >=n ) return false;
					if (arr[nextX][nextY] == '1') return false;
				}
			}
		}
		else {
			// 모양이 세로
			// 좌우가 없어야 함.
			for (int i = 2 ; i<4 ; i++) {
				for (int j = 0 ; j< 3 ; j++) {
					int nextX = temp.list.get(j).x + dx[i];
					int nextY = temp.list.get(j).y + dy[i];
					if (nextX < 0 || nextX >= n || nextY <0 || nextY >=n ) return false;
					if (arr[nextX][nextY] == '1') return false;
				}
			}
		}		
		return true;
	}
	
	private static boolean checkFunc(Gidung temp, int dir) {
		ArrayList<Cor> list = temp.list;		
		for (int i =0  ; i< 3 ; i++) 
		{
			int nextX = list.get(i).x + dx[dir];
			int nextY = list.get(i).y + dy[dir];
			if (nextX >= n || nextX < 0 ) return false;
			if (nextY >= n || nextY < 0) return false;
			if (arr[nextX][nextY] == '1') return false;
		}
		return true;
	}

}
