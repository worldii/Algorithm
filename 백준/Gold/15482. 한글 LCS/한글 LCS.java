import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String str2 = sc.next();
		 int[][] d = new int[1001][1001];
		int len = Math.max(str.length(), str2.length());
	
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str2.length(); j++) {
					if (str.charAt(i)== str2.charAt(j)) d[i+1][j+1] = d[i][j] +1;
					else {
			
						d[i+1][j+1] = Math.max(d[i][j+1], d[i+1][j]);
					}
			}
		}
//		for (int i = 0 ; i<= str.length(); i++) {
//			for (int j = 0  ; j<= str2.length(); j++) {
//				System.out.print(d[i][j]);
//			}System.out.println();
//		}
		System.out.println(d[str.length()][str2.length()]);
	}
}
