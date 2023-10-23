import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        char[] arr = br.readLine().toCharArray();
        int acount = 0;

        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] =='a') acount++;
        }
        int maxCount = Integer.MAX_VALUE;
        // 0부터 시작 하여 계속
        for (int i = 0 ; i< arr.length ; i++) {
            int end = acount + i-1;
            int bcount =0;
            for (int j =i ; j<= end; j++) {
                if (arr[j%arr.length]== 'b') bcount++;
            }
            maxCount = Math.min(bcount,maxCount);

        }
        System.out.println(maxCount);
    }


}