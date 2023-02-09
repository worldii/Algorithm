import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class info {
	int l;
	int r;
	int s;

	info(int l, int r, int s) {
		this.l = l;
		this.r = r;
		this.s = s;
	}
}

public class Main {

	static int n, m, x;
	static int maxSum;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static info[] infoList;
	static int maxNum = -1;
	static ArrayList<int[]> arrList;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		long [][]arr = new long [n][2];
		long maxNum = Integer.MAX_VALUE;
		
		for (int i = 0 ; i< n ; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] =sc.nextInt();
		}
		
		for (int i = 1; i < (1 << n); i++) {
			 long sum1 = 1;
			 long sum2 = 0;			
			for (int j = 0; j < n; j++) {

				if ((i & (1 << j)) > 0) {
					sum1 *= arr[j][0];
					sum2 += arr[j][1];
				//	System.out.println(j+ " " +sum1+ " " + sum2);

				}
			}
			maxNum = Math.min(Math.abs(sum1-sum2), maxNum);
			
		}
		System.out.println(maxNum);
	}

}
