import java.util.Scanner;

public class Main {

	public static int recur(int k, int cnt) {
		// 종료 조건
		int flag = 0;
		int maxnum = 0;
		int sum = Integer.MAX_VALUE;
		for (int i = 2; i * i <= k; i++) {
			if (k % i == 0) {
				flag = 1;
				int maxx = Math.max(recur(i, cnt + 1), recur(k / i, cnt + 1));
				sum = Math.min(maxx, sum);
			}
		}

		if (flag == 0) {
			return cnt;
		}
		return sum;

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		System.out.println(recur(k, 0));

	}
}
