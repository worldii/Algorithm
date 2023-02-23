import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * [핵심키워드]
 * BFS , 최단 거리
 * 
 * [풀이방향]
 * n -> k 까지 가는데 최단 거리를 구해야 하므로 bfs, 다음 노드로 n-1, n+1, 2*k 를 선택해야함
 * 
 * [입력 사항]
 * 지금 현재 위치인 N , 도착하는 위치인 k 를 입력 받음. 둘다 0-100000 사이 범위임. 
 * 
 * 
 * [출력 사항]
 * 최단 거리를 출력하라.
 * 
 * @author  worldi
 * @since 2022.02.23
 * @see https://www.acmicpc.net/problem/1697
 * @performance 
 * @category #BFS
 */

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;


    public static int START_LIMIT =  0;
    public static int END_LIMIT = 100000;
    public static int n;
    public static int bfs(int start, int end ) {

        Queue<Integer> q= new LinkedList<>();

        boolean [] checked = new boolean [END_LIMIT+1];
        int [] dist = new int [END_LIMIT+1];
        dist [ start ] =0;
        q.add(start);
        checked[start] = true;

        while (!q.isEmpty()) {
            int temp = q.peek();
            q.poll();
            if( temp == end ) {
                return dist[temp];
            }
            if (2* temp <= END_LIMIT && !checked[2*temp]) {
                dist[2*temp] = dist[temp ] +1;
                checked[2*temp] = true;
                q.add(2*temp);
            }
             if (temp -1 >= START_LIMIT && !checked[temp-1]) {
                checked[temp-1] =true;
                dist[temp-1] = dist[temp] +1;
                q.add(temp-1);
            }
             if (temp+1 <= END_LIMIT && !checked[temp+1] ) {
                checked[temp+1] = true;
                dist[temp+1] = dist[temp] +1;
                q.add(temp+1);
            }
        }
        return dist[end];
    }


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        n =sc.nextInt();
        int k=  sc.nextInt();
        System.out.println(bfs(n, k));
    }
}