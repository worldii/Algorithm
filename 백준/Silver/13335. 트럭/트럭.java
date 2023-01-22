
import java.util.ArrayList;
import java.util.Scanner;

class truck {
	int wei;
	int time;

	truck(int wei, int time) {
		this.time = time;
		this.wei = wei;
	}
}

public class Main {
	static int n, w, l;
	static int timeSum = 0;
	static ArrayList<truck> tlist = new ArrayList<>();

	public static void increaseTime() {
		timeSum++;
		//System.out.println(timeSum);

		for (int t = 0; t < tlist.size(); t++) {
			tlist.get(t).time += 1;
		}
		//System.out.println();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		w = sc.nextInt();
		l = sc.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int weiSum = 0;
		int i = 0;
		
		while (true) {
			// 트럭 있는 것 확인
			//System.out.println("Start");
			// 트럭 삽입!!
			if (i == n)
			{				
				if (tlist.size() ==0) break;
			}
			else if (i < n) {
				if (weiSum + arr[i] <= l) 
				{
					weiSum += arr[i];
					tlist.add(new truck(arr[i], 0));
					i++;
				}
			}
			increaseTime();


			// 트럭 제거 !
			for (int t = 0; t < tlist.size(); t++) {
				//System.out.println(tlist.get(t).wei + " " + tlist.get(t).time);
				if (tlist.get(t).time == w ) {
					weiSum -= tlist.get(t).wei;
					//System.out.println("remove" + t + tlist.get(t).wei);
					tlist.remove(t);
					break;
				}
			}
		}

		System.out.println(timeSum+1);

	}

}
