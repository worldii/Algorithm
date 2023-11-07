import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    /*
    첫째 줄에 사람의 수 N이 주어진다. N은 10보다 작거나 같은 자연수이다.
     둘째 줄에는 키가 1인 사람부터 차례대로 자기보다 키가 큰 사람이 왼쪽에 몇 명이 있었는지 주어진다.
     i번째 수는 0보다 크거나 같고, N-i보다 작거나 같다. i는 0부터 시작한다.


    * */
    public static int[] arr;
    public static int[] count;
    public static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        count = new int[n + 1];
        /*
        첫째 줄에 사람의 수 N이 주어진다.
        N은 10보다 작거나 같은 자연수이다.
        둘째 줄에는 키가 1인 사람부터 차례대로 자기보다 키가 큰 사람이 왼쪽에 몇 명이 있었는지 주어진다.
        i번째 수는 0보다 크거나 같고, N-i보다 작거나 같다. i는 0부터 시작한다.
        */
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            int cnt = arr[i];
            int idx = 1;
            int tempCount = 0;
            while (tempCount < cnt) {
                if (count[idx] == 0) {
                    tempCount++;
                }
                idx++;
            }
            while (count[idx] != 0) {
                idx++;
            }
            count[idx] = i;
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(count[i] + " ");
        }
        System.out.println();
    }


}