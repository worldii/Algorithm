import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static StringTokenizer st = null;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int findIdx(long num) {
        int end = 64;
        int start = 0;
        int mid = -1;
        while (start < end) {
            mid = (start + end) / 2;
            if (num > (2 << mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }

    public static long[] digit = new long[61];

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
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            long diff = Long.MAX_VALUE;
            long targetNum = 0;
            for (int t = 0; t <= 60; t++) {
                for (int tt = t; tt <= 60; tt++) {
                    if (diff > Math.abs(((1L << t) + (1L << tt)) - num)) {
                        diff = Math.abs(((1L << t) + (1L << tt)) - num);
                        targetNum = ((1L << t) + (1L << tt));
                    }
                }
            }
            if (Long.bitCount(targetNum) == 1) {
                for (int t = 0; t <= 60; t++) {
                    if ((targetNum & (1L << t)) > 0) {
                        sb.append(t-1).append(" ").append(t-1).append("\n");
                    }
                }
            }
            else {
                for (int t = 0; t <= 60; t++) {
                    if ((targetNum & (1L << t)) > 0) {
                        sb.append(t).append(" ");
                    }
                }
                sb.append("\n");
            }


        }
        System.out.println(sb);
    }
}
