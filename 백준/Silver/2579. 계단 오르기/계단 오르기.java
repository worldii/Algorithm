import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Integer> list = new ArrayList<Integer>();
	static int [][] d= new  int[10001][2];
	public static void main(String[] args) throws IOException {
		int n ;
		Scanner sc = new Scanner(System.in) ;
		n = sc.nextInt();
		for (int i = 0  ; i< n ; i++)
		{
			int temp = sc.nextInt();
			list.add(temp);
		}

		// d[i][j] = i번째 까지 올라갔을 떄의 최대 수 i번째를 꼭 올라 타야함.
		// j는 연속인 수

		// d[i][j] =
		// d[i][0] =max( d[i-2][1], d[i-2][0] ) +a[i];
		// d[i][1] = max(d[i-1][0]) + a[i];

		d[0][0] = list.get(0);
		d[0][1] = 0;
		if (n != 1) {
			d[1][1] = list.get(0) + list.get(1);
			d[1][0] = list.get(1);
		}
		for (int i = 2; i< n ; i++) {
			d[i][0] = Math.max(d[i-2][1], d[i-2][0]) +list.get(i);
			d[i][1] =d[i-1][0] + list.get(i);
		}

		System.out.println(Math.max(d[n-1][0], d[n-1][1]));

	}

}

