import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long  [] p = new long[101];
		long[] temp = {1, 1, 1, 2, 2, 3, 4, 5, 7, 9};
		for (int i = 1; i <= 10; i++) {
			p[i] = temp[i - 1];
		}

		for (int i = 11; i <= 100; i++) {
			p[i] = p[i - 1] + p[i - 5];
		}
		int t = sc.nextInt();
		while (t-- != 0) {

			int n = sc.nextInt();

			System.out.println(p[n]);
		}
	}
}
