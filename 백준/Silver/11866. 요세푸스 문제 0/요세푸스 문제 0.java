import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= num1; i++) {
			q.add(i);
		}

		int cnt = 0;
		System.out.print("<");
		while (!q.isEmpty()) {
			int temp = q.poll();
			cnt++;
			if (cnt == num2) {
				
				System.out.print(temp);
				if (!q.isEmpty()) System.out.print(", "); 
				cnt = 0;
			}
			else {
				q.add(temp);
			}
		}
		System.out.print(">");
	}
}
