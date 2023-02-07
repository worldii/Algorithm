import java.util.LinkedList;
import java.util.Queue;
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
	static int[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			int first = sc.nextInt();
			int second = sc.nextInt();
			if (first == 1) {
				for (int j = second; j <= n; j += second) {
					arr[j] = 1 - arr[j];
				}
			} else {
				int right = second;
				int left = second;
				arr[second] =1-arr[second];
				int flag = 1;
				while (flag == 1) {
					right = right + 1;
					left = left - 1;
					if (1 <= right && right <= n) {
						if (1 <= left && left <= n) {
							if (arr[left] == arr[right]) {
								flag = 1;
								arr[left] = 1 - arr[left];
								arr[right] = 1 - arr[right];
							} else {
								flag = 0;
							}
							;
						}

					} else {
						flag = 0;
					}
				}
			}
		

		}
		for (int i =1 ; i<= n ; i++) {
			System.out.print(arr[i] + " ");
			if (i%20 ==0) System.out.println();
		}

	}

}
