import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Sushi {
    private int[] list;
    Sushi(int n, int d, int k, int c){
        list= new int[n];
    }
}

class Game {
    public void start(){

    }
}
public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int maxSum = 0;
    public static int[] arr;

    public static int beforeCnt (int idx, int n) {
        return (idx-1+n)%n;
    }
    public static int afterCnt(int idx, int n ) {
        return (idx+1+n)%n;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        
        int[] checked = new int[d  + 2];

        int cnt = 0;
        int end = k-1;
        int start = 0;
        int coupon =0;

        for (int i = start; i <= end; i++) {
            if (checked[arr[i]] == 0) cnt++;
            checked[arr[i]]++;
        }

        for (int i = 0 ; i< n ; i++) {
            if (checked[c] ==0) {
                coupon=1;
            }
            maxSum  = Math.max(maxSum, coupon+cnt);

            coupon = 0;
            checked[arr[start]]--;
            if (checked[arr[start]]==0) cnt--;

            start = afterCnt(start,n);
            end = afterCnt(end,n);

            if (checked[arr[end]] ==0) cnt++;
            checked[arr[end]]++;
        }
        System.out.println(maxSum);
    }
}

