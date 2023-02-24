import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Cor {
	int x;
	int y;

	Cor(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int[][] arr= new int [9][9];


	static int[][] arrdir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static int maxnum = 70;
	static List<Cor> cctv = new ArrayList<>();
	static int n;
	static int m;

	public static int checkarr(int[][] arr) {
		int cnt= 0;
		for (int i = 0 ; i< n ; i++) {
			for (int j = 0 ; j< m ; j++) {
				//System.out.print(arr[i][j]+" ");
				if (arr[i][j] == 0 ) cnt++;
			}//System.out.println();
		}
		//System.out.println();
		return cnt;
	}

	public static void dirfunc(int[] dir, int curx, int cury, int [][]arr2) {
	
		for (int i = 0; i < n; i++) {
			int newx = dir[0] * i + curx;
			if (newx >=n || newx <0 ) break;
			if (arr2[newx][cury] == 6) break;
			if (arr2[newx][cury] ==0) arr2[newx][cury] = -1;
		}
		for (int j = 0; j < m; j++) {
			int newy = dir[1] * j + cury;
			if (newy >= m || newy <0) break;
			if (arr2[curx][newy] == 6 ) break;
			if (arr2[curx][newy] ==0 ) arr2[curx][newy]= -1;
		}
	}
	

	public static void dfs(int cur, int size, int [][]arr) {
		if (cur == size) {
			maxnum = Math.min(checkarr(arr), maxnum);
			return ;
		}
		// cur
		// 90 도로 도는 것.
		int curx = cctv.get(cur).x;
		int cury = cctv.get(cur).y;
		//System.out.print(curx + " "+ cury);

		int num = arr[curx][cury];
		for (int i = 0; i < 4; i++) 
		{

			int [][]arr2 = new int[9][9];
			for (int t = 0 ; t< n ; t++) {
				for (int tt = 0 ;tt< m ;tt++) 
				{
					arr2[t][tt] = arr[t][tt];
				//	System.out.print(arr2[t][tt]);
				}
			//	System.out.println();
			}
			
			//System.out.println(num+ " " +cur +" " + size);
			switch (num)
			{
			case 1:
				dirfunc(arrdir[(3 + i) % 4],  curx, cury,arr2);
				// 
				// 다음
				break;
			case 2:
				dirfunc(arrdir[(3 + i) % 4],  curx, cury,arr2);
				dirfunc(arrdir[(1 + i) % 4],  curx, cury,arr2);
				// 다음
				

				break;
			case 3:
				dirfunc(arrdir[(0 + i) % 4],  curx, cury,arr2);
				dirfunc(arrdir[(3 + i) % 4],  curx, cury,arr2);
				// 다음
				

				break;
			case 4:
				dirfunc(arrdir[(0 + i) % 4], curx, cury,arr2);
				dirfunc(arrdir[(3 + i) % 4], curx, cury,arr2);
				dirfunc(arrdir[(1 + i) % 4],  curx, cury,arr2);
				// 다음
				break;
			case 5:
				dirfunc(arrdir[(0 + i) % 4],  curx, cury,arr2);
				dirfunc(arrdir[(1 + i) % 4],  curx, cury,arr2);
				dirfunc(arrdir[(2 + i) % 4], curx, cury,arr2);
				dirfunc(arrdir[(3 + i) % 4],  curx, cury,arr2);
				break;
			}
			dfs(cur + 1, size, arr2);

		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sn = new Scanner(System.in);

		n = sn.nextInt();
		m = sn.nextInt();
		

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sn.nextInt();
				if (arr[i][j] != 0 && arr[i][j] != 6)
				cctv.add(new Cor(i, j));
			}
		}


		dfs(0, cctv.size(), arr);
		System.out.print(maxnum);
	}

}
