import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {


	public static void main(String[] args) {

		int [][]arr= new int [1001][3];
		long [][] d = new long [1001][3];


		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0  ; i< n; i++) {
			for (int j = 0 ; j<3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		// d[i][j] = i 번째 까지 칠했을 때 최소 비용
		// j = 0 red, j= 1 blue , j=2 green
		// d[i][0] = min(d[i-1][1], d[i-1][2]) + a[i][0];
		// d[i][1] = min(d[i-1][2], d[i-1][3])  + a[i][1];
		// d[i][2] = min(d[i-1][0], d[i-1][1]) + a[i][2];
		//
		long minCost = Integer.MAX_VALUE;

		d[0][0] = arr[0][0];
		d[0][1] = Integer.MAX_VALUE;
		d[0][2] = Integer.MAX_VALUE;
		for (int i =1 ; i< n ; i++) {
			d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + arr[i][0];
			d[i][1] = Math.min(d[i-1][2], d[i-1][0])  + arr[i][1];
			d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + arr[i][2];
		}
		minCost = Math.min(minCost, d[n-1][1]);
		minCost = Math.min(minCost, d[n-1][2]);


		d[0][0] = Integer.MAX_VALUE;
		d[0][1] = arr[0][1];
		d[0][2] = Integer.MAX_VALUE;
		for (int i =1 ; i< n ; i++) {
			d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + arr[i][0];
			d[i][1] = Math.min(d[i-1][2], d[i-1][0])  + arr[i][1];
			d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + arr[i][2];
		}
		minCost = Math.min(minCost, d[n-1][0]);
		minCost = Math.min(minCost, d[n-1][2]);

		d[0][0] = Integer.MAX_VALUE;
		d[0][1] = Integer.MAX_VALUE;
		d[0][2] = arr[0][2];
		for (int i =1 ; i< n ; i++) {
			d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + arr[i][0];
			d[i][1] = Math.min(d[i-1][2], d[i-1][0])  + arr[i][1];
			d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + arr[i][2];
		}
		minCost = Math.min(minCost, d[n-1][1]);
		minCost = Math.min(minCost, d[n-1][0]);
		//System.out.println(d[n-1][0] + " " + d[n-1][1] + " " + d[n-1][2]);
		System.out.println(minCost);
	}

}
