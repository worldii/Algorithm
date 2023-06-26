import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int [] arr;
    public static void main(String[] args) throws IOException {

        int n , h , t;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h= Integer.parseInt(st.nextToken());
        t= Integer.parseInt(st.nextToken());
        arr = new int [n];

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0 ; i< n ; i++) {
            st =new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            pq.add(num);
        }
        int cnt =0;
        for (int i = 0 ; i< t ; i++) {
            if (pq.peek() < h) break;
            int num = pq.peek()/2;
            if (num <=0) break;
            pq.poll();
            pq.add(num);
            cnt++;
        }
        if (pq.peek() >= h) {
            System.out.println("NO");
            System.out.println(pq.peek());
        }
        else  {
            System.out.println("YES");
            System.out.println(cnt);
        }


    }
}
