import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// 클래스 생성
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
	static int timeSum = 1;

	// 트럭
	static List<truck> tlist = new LinkedList<>();

	// 시간 1초 흐름.
	public static void increaseTime() {
		timeSum++;
		for (int t = 0; t < tlist.size(); t++) {
			tlist.get(t).time += 1;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		// 입력 받기
		StringTokenizer st  = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int weiSum = 0;
		int i = 0;

		while (true) {
			// 트럭 있는 것 확인
			// 트럭 삽입!!
			if (i == n)
			{
				// n 이고 리스트에 남아 있는 것이 없으면 뺀다.
				if (tlist.size() ==0) break;
			}
			else if (i < n) {
				// 삽입할게 남았다는 뜻임.
				if (weiSum + arr[i] <= l)
				{
					// 들어 갈 수 있으면 더해준다.
					weiSum += arr[i];
					tlist.add(new truck(arr[i], 0));
					i++;
				}
			}

			increaseTime();

			// 트럭 제거 !
			for (int t = 0; t < tlist.size(); t++) {
			//	System.out.println(tlist.get(t).wei + " " + tlist.get(t).time);
				if (tlist.get(t).time == w ) {
					weiSum -= tlist.get(t).wei;
				//	System.out.println("remove" + t + tlist.get(t).wei);
					tlist.remove(t);
					break;
				}
			}
		}

		System.out.println(timeSum);

	}

}
