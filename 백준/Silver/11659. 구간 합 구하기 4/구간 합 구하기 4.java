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
		for (int i = 1; i< n ; i++ ) {
			arr[i] = arr[i-1] + arr[i];
		}
		for (int i = 0  ; i< m ;  i++) {
			int a,b;
			a= sc.nextInt();
			b= sc.nextInt();
			a--;
			b--;
			int sum = arr[b];
			if (a-1>=0 ) sum -= arr[a-1];
			System.out.println(sum);
		}
	}

}
