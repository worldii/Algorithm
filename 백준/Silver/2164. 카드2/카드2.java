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
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= num1; i++) {
			q.add(i);
		}

	
		int last = -1;
		while (!q.isEmpty()) {
			 last = q.poll();
			if (!q.isEmpty()) {
				last = q.peek();
				q.poll();
				q.add(last);
				
			}	
		}
		System.out.println(last);
	}
}
