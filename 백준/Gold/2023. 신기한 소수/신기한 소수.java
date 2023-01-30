import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[] checked = new int[1001];

	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	static int k;
	static int[] arr = new int[10];

	public static boolean checkSo(int[] arr, int cnt) {
		String str = "";
		for (int i = 0; i <= cnt; i++) {
			str += arr[i] + "";
		}
		int num = Integer.parseInt(str);
		//System.out.println("Num"+ num);
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;

	}

	public static void dfs(int[] arr, int cnt) {
		if (cnt == k) {
			for (int i = 0; i < k; i++) {
				System.out.print(arr[i]);
				
			}System.out.println();
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (cnt == 0 && i ==0) continue;  
			if (cnt == 0 && i==1 ) continue;
			arr[cnt] = i;
			if (checkSo(arr, cnt))
				dfs(arr, cnt + 1);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		k = sc.nextInt();
		dfs(arr, 0);

	}
}
