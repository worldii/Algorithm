import java.util.Scanner;

public class Main {

	static int n, m ;
	static int []arr;
	
	static void dfs(int choose,  int start, int[] arr ) {
		if (choose == m ) {
			for (int i = 0 ;i< m ; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		if(start > n ) return ;

	
		// 선택했을 경우
		arr[choose] = start;
		dfs(choose+1, start+1, arr);
		
		// 선택 안했을 경우
		arr[choose] = 0;
		dfs(choose, start+1, arr);
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner (System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int [m+1];
		dfs(0,1,arr);
		

		
	}
	
	

}
