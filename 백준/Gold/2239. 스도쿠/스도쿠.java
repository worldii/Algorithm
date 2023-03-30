import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n;
	public  static  ArrayList<int[]> cor =new ArrayList<>();

	public static boolean check(int num, int row, int col) {
		for (int i = 0 ; i< 9 ; i++)
		{
			if (arr[row][i] == num) return false;
		}

		for (int i = 0 ; i< 9 ; i++) {
			if (arr[i][col]== num) return false;
		}

		int realRow = (row/3) * 3;
		int realCol = (col/3) * 3;
		for (int i = realRow ; i< realRow +3 ; i++) {
			for (int j =realCol ; j< realCol+ 3 ; j++) {
				if (arr[i][j] == num) return false;
			}
		}
		return true;
	}
	public static  int [][] arr;
	public static void dfs(int cnt, int [][] arr) {
		if (cnt == cor.size()) {
			for (int i = 0 ; i< 9 ; i++) {
				for (int j = 0 ; j< 9  ; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();

			}
			System.exit(0);
		}

		for (int i = 1; i<= 9 ; i++)
		{
			//System.out.println(cor.get(cnt)[0] + " " + cor.get(cnt)[1]);
			if (!check(i,cor.get(cnt)[0], cor.get(cnt)[1])) continue;
			arr[cor.get(cnt)[0]][cor.get(cnt)[1] ] = i;
			dfs(cnt +1,arr);
			arr[cor.get(cnt)[0]][cor.get(cnt)[1] ] = 0;
		}
	}
	public static void main(String[] args) throws IOException {

		arr = new int [10][10];

		for (int i = 0 ; i<9  ; i++) {
			char [] temp  = br.readLine().toCharArray();
			for (int j = 0  ; j< 9 ; j++) {
				arr[i][j] = temp[j] -'0';

				if (arr[i][j] == 0 ) {
					cor.add(new int [] {i,j});
				}

			}
		}
		dfs(0,arr);

    }
}
