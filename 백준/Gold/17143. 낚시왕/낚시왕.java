import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.StringTokenizer;


class Shark {
	int r;
	int c;
	int speed;
	int dir;
	int size;
	Shark(int r, int c, int speed, int dir, int size) {
		this.r = r;
		this.c= c;
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Shark{" +
			"r=" + r +
			", c=" + c +
			", speed=" + speed +
			", dir=" + dir +
			", size=" + size +
			'}';
	}
}
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static HashMap<Integer, Shark> sharkList = new HashMap<>();
	static int R = 0 ;
	static int C = 0 ;
	static StringTokenizer st =  null;
	static int[][] arr  ;
	static int totalsum = 0;
	static int[] dx = {0,-1, 1, 0,0};
	static  int []dy = {0,0,0,1,-1};
	static void catchShark(int col){
		for (int i = 1 ; i<= R ; i++) {
			if (arr[i][col] != 0 ) {
				// 상어가 있다는 뜻임
				// 해당 상어 크기 더해줌.
				totalsum+= sharkList.get(arr[i][col]).size;
				sharkList.remove(arr[i][col]);
				arr[i][col]= 0;
				break;
			}
		}
	}
	static void moveShark(){

		int [][]temparr = new int[R+1][C+1];
		for (int i = 0 ; i<= R  ; i++) {
			for (int j = 0  ; j<=  C ; j++) {
				temparr[i][j] = 0;
			}
		}
		ArrayList<Integer> removedList = new ArrayList<>();
		for (Integer key : sharkList.keySet()) {
			// 상어 움직임
			int oldr= sharkList.get(key).r;
			int oldc = sharkList.get(key).c;
			int speed = sharkList.get(key).speed;
			int dir = sharkList.get(key).dir;
			for (int i = 1 ; i<= speed ; i++)
			{
				if (dir == 1 && oldr == 1) dir = 2;
				else if (dir == 2 && oldr == R) dir = 1;
				else if (dir == 3  && oldc == C) dir = 4;
				else if (oldc == 1 &&  dir == 4 ) dir = 3;

				int newx = dx[dir] + oldr;
				int newy = dy[dir] + oldc;

				oldr = newx;
				oldc = newy;
			}
			sharkList.get(key).r = oldr;
			sharkList.get(key).c = oldc;
			sharkList.get(key).dir = dir;

			if (temparr[ oldr][oldc] == 0)
			{ temparr[ oldr][ oldc] = key;}
			else {
				int oldkey = temparr[ oldr][oldc];
				if (sharkList.get(oldkey).size < sharkList.get(key).size) {
					temparr[ oldr][oldc] = key;
					removedList.add(oldkey);
				}
				else {
					removedList.add(key);

				}
			}
		}

		for (int i = 1 ; i<= R; i++) {
			for (int j = 1; j<= C; j++) {
				arr[i][j] = temparr[i][j];
			}
		}
		for (int i  = 0  ; i<removedList.size() ; i++){
			sharkList.remove(removedList.get(i));
		}
	}


	public static void main(String[] args) throws IOException {
		// 입력 받기
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int sharkNum = Integer.parseInt(st.nextToken());

		arr = new int[1001][1001];

		for (int i = 1 ; i<= R ; i++) {
			for (int j = 1; j<= C; j++) {
				arr[i][j] = 0;
			}
		}

		for (int i = 1  ; i<= sharkNum ; i++)
		{
			st= new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// r,c 번째에 i 번째 shark 가 있다는 것 기록해줌.
			arr[r][c] = i;
			int s = Integer.parseInt(st.nextToken());
			int d= Integer.parseInt(st.nextToken());
			int z= Integer.parseInt(st.nextToken());
			sharkList.put(i,new Shark(r,c,s,d,z));
		}

		for (int i = 1;  i<= C ; i++) {
			if (sharkList.isEmpty()) break;
			catchShark(i);
			moveShark();
		}
		System.out.println(totalsum);
	}

}
