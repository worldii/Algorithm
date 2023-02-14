
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Solution {
	// static int[][] route = {
	// 	{2, 4, 6, 8, 10, 13, 16, 19, 25, 30, 35, 40},
	// 	{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25, 30, 35, 40},
	// 	{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 28, 27, 26, 25, 30, 35, 40},
	// 	{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40}};
	// static int[] arr = new int[10];
	//
	// // 10 이면 1번 루트는 타면 안됨(2,3,4 됨)
	// // 20 이면 2번 루트만 타야 함
	// // 30 이면 3번 루트만 타야 함.
	// // cnt
	// // 같은 곳있으면 안됨.
	//
	// public static int minNum;
	//
	// public static void dfs(int cnt, int[] loc, int sum, horse[] curHorse) {
	// 	if (cnt == 10) {
	// 		if (minNum < sum) {
	// 			minNum = Math.max(minNum, sum);
	// 			for (int i = 0; i < 10; i++) {
	// 				System.out.print(loc[i] + " ");
	// 			}
	// 			System.out.println(minNum);
	// 			System.out.println();
	// 		}
	// 		// 다 선택했으면 점수 최댓 값 비교
	//
	// 		return;
	// 	}
	// 	// 백트래킹
	// 	// cnt번 째 숫자  가져가냐
	// 	for (int j = 0; j < 4; j++) {
	//
	// 		// j번째 말이 가져갑니다.
	// 		int oldcurRouteNum = curHorse[j].routesNUm;
	// 		int oldCurHouseIdx = curHorse[j].idx;
	// 		if (oldCurHouseIdx == 40)
	// 			continue;
	// 		int nextRouteNum = 0;
	// 		int curLoc = -1;
	// 		int nextIdx = arr[cnt] - 1;
	// 		if (curHorse[j].idx != -1) {
	// 			curLoc = route[curHorse[j].routesNUm][curHorse[j].idx];
	// 			if (curLoc % 10 == 0) {
	// 				nextRouteNum = curLoc / 10 - 1;
	// 			} else
	// 				nextRouteNum = 3;
	// 		}
	// 		if (curLoc != -1) {
	// 			for (int t = 0; t < route[nextRouteNum].length; t++) {
	// 				if (route[nextRouteNum][t] == curLoc) {
	// 					nextIdx = t + arr[cnt];
	// 					break;
	// 				}
	// 			}
	// 		}
	// 		if (nextIdx >= route[nextRouteNum].length)
	// 			nextIdx = 40;
	// 		else {
	// 			int flag = 0;
	// 			for (int i = 0; i < 4; i++) {
	// 				if (curHorse[i].idx == 40)
	// 					continue;
	// 				;
	// 				if (i == j)
	// 					continue;
	// 				if (curHorse[i].idx != -1
	// 					&& route[curHorse[i].routesNUm][curHorse[i].idx] == route[nextRouteNum][nextIdx]
	// 					&& route[nextRouteNum][nextIdx] % 5 == 0)
	// 					flag = 1;
	// 				else if (curHorse[i].idx == nextIdx && curHorse[i].routesNUm == nextRouteNum)
	// 					flag = 1;
	// 			}
	// 			if (flag == 1)
	// 				continue;
	// 		}
	// 		//System.out.println(nextIdx + " " + nextRouteNum + " " + curLoc);
	// 		int nextSum = 0;
	// 		if (nextIdx == 40) {
	// 			nextSum = 0;
	// 		} else
	// 			nextSum = sum + route[nextRouteNum][nextIdx];
	//
	// 		curHorse[j] = new horse(nextIdx, nextRouteNum);
	// 		loc[cnt] = j;
	//
	// 		dfs(cnt + 1, loc, nextSum, curHorse);
	//
	// 		curHorse[j] = new horse(oldCurHouseIdx, oldcurRouteNum);
	// 	}
	// }

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tt = 1; tt<= t ;tt++) {
		int n = sc.nextInt();
		int m = sc.nextInt();
		int MaxSum = -1;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (m >= arr[i] + arr[j])
					MaxSum = Math.max(MaxSum, arr[i] + arr[j]);
			}
		}
		System.out.println("#"+ tt+" "+ MaxSum);}
		// minNum = -1;
		// for (int i = 0; i < 10; i++) {
		// 	arr[i] = sc.nextInt();
		// }
		// int[] loc = new int[10];
		//
		// horse[] curHorse = new horse[4];
		//
		// for (int i = 0; i < 4; i++) {
		// 	curHorse[i] = new horse(-1, 0);
		// }
		//
		// // 순서대로 0,1,2,3 말의 위치 어디까지 갔는지 담음
		// // 선택의 문제 결국 몇번말이 어디꺼 가져가는지 문제이다.
		// dfs(0, loc, 0, curHorse);
		// System.out.println(minNum);
	}

}
