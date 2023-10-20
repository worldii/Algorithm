import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static ArrayList<Integer> arr = new ArrayList<>();

    public static int n, c;

    /*
    첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다.
    둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가
    한 줄에 하나씩 주어진다.
    * */
    public static boolean check(int diff) {
        int count =1;
        int first = arr.get(0);
        int next = arr.get(0) + diff;
        for (int i = 1 ; i< arr.size() ; i++) {
            if (next <= arr.get(i)) {
                first = arr.get(i);
                next = first + diff;
                count++;
            }
            if (count == c) return true;
        }
        if (count == c) return true;

        return false;
    }
    public static int binarySearch (){
        int realDiff= 0;
        int start = 0;
        int end = Integer.MAX_VALUE;
        while (start<end) {
            int mid= (start + end) /2;
            if (check(mid)) {
                realDiff = mid;
                start = mid+1;
            }
            else end =mid;
        }
        return realDiff;
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arr);
        System.out.println(binarySearch());
        // 1 2 4 8 9

    }
}