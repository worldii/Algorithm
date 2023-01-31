import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] d;
	static int len = 0;
	static String str;
	static int[] minArray;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		str = sc.next();
		// 팰린드롬 문제
		len = str.length();
		d = new int[str.length() + 1][str.length() + 1];
		minArray = new int[str.length() + 1];

		// 모든 인덱스 마다는 1이 최소임.
		for (int i = 0; i < len; i++) {
			d[i][i] = 1;
		}

		// i 는 크기임
		for (int i = 0; i < len; i++) {
			int j = i + 1;
			if (j >= len)
				continue;
			if (str.charAt(i) == str.charAt(j)) {
				d[i][j] = 1;
			}
		}

		for (int i = 2; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int t = j + i;
				if (t >= len)
					continue;
				if (str.charAt(j) == str.charAt(t) && d[j + 1][t - 1] == 1)
					d[j][t] = 1;
			}
		}

		// Min array d[i] i번째 까지 최소 분할 개수
		// d[1] = 1;
		// if
		// d[i] = min (d[i-1] +1,

		minArray[0] = 1;
		for (int i = 1; i < len; i++) {
			minArray[i] = Integer.MAX_VALUE;
			for (int j = 0; j <= i; j++) {
				if (d[j][i] == 1) {
					if (j == 0)
						minArray[i] = 1;
					else
						minArray[i] = Math.min(minArray[i], minArray[j - 1] + 1);
				}
			}
		}
		System.out.print(minArray[len - 1]);

	}

}
