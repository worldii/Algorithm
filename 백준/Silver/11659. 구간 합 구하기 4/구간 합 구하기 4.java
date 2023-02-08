import java.util.Scanner;

public class Main {

	static int n, m;
	static int[] arr;
	static int[] subsum;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		arr = new int[n];
		subsum = new int[n];
		for (int i = 0 ; i<n ; i++) {
			arr[i] = sc.nextInt();
		}
		subsum[0] = arr[0];
		for (int i = 1; i< n ; i++ ) {
			subsum[i] = subsum[i-1] + arr[i];
		}
//		for (int i = 0  ; i< n ; i++) {
//			System.out.println(subsum[i]);
//		}
		for (int i = 0  ; i< m ;  i++) {
			int a,b;
			a= sc.nextInt();
			b= sc.nextInt();
			a--;
			b--;
			int sum = subsum[b];
			if (a-1>=0 ) sum -= subsum[a-1];
			System.out.println(sum);
		}
	}

}
