import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n, m;
    public static StringTokenizer st = null;
    public static long[] digit = new long[61];

    // 상한선 찾기


    public static void main(String[] args) throws IOException {

        digit[0] = 1;
        digit[1] = 2;
        for (int i = 2; i <= 60; i++) {
            // digit 짝수 일 때,
            if (i % 2 == 0) {
                digit[i] = digit[i / 2] * digit[i / 2];
            } else {
                digit[i] = digit[i / 2] * digit[i / 2] * 2;
            }
        }

        n = Integer.parseInt(br.readLine());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            long[] arr = findAnswer(num);
            if (arr[1] == 0) {
                stringBuilder.append(arr[0]-1).append(" ").append(arr[0]-1).append("\n");
            } else {
                long[] arr2 = findAnswer(arr[1]);
                stringBuilder.append(arr2[0]).append(" ").append(arr[0]).append("\n");
            }
        }
        System.out.println(stringBuilder);
    }

    private static long[] findAnswer(long num) {
        int start = 0;
        int end = 60;
        while (start <= end ) {
            int mid = (start + end)/2;
            if (digit[mid] > num) {
                end = mid- 1;
            }
            else start = mid+1;
        }
        return new long [] {end, num - digit[end]};
    }
}
