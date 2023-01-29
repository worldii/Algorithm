import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ascore = 100;
		int bscore = 100;

		for (int i = 0; i < n; i++) {

			int a = sc.nextInt();
			int b = sc.nextInt();
			if (a > b) {
				bscore -= a;
			} else if (a < b) {
				ascore -=b;
			}
		}
		System.out.println(ascore+"\n"+ bscore);

	}
}
