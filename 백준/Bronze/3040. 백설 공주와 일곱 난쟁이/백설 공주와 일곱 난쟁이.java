import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[] arr;
	static int[] subsum;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	
	static void recur (int choose, int start, int[] arr2) {
		if (choose ==7) {
			int sum =0 ;
			for (int i = 0 ; i< 7 ; i++)  {
			sum += arr2[i];
			}
			if (sum == 100) {
				for (int i = 0 ; i< 7 ; i++) {
					System.out.println(arr2[i]);
				}
System.exit(0);
}
			return;
		}
		if (start >= 9) return; 
	
		// 선택 
		arr2[choose] = arr[start];
		recur (choose +1, start+1, arr2);
		
		// 선택 x ,
		arr2[choose] = 0;
		recur(choose, start+1, arr2);
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		arr = new int[9];
		for (int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		int []arr2 = new int [9];
		recur(0, 0, arr2);
	}

}
