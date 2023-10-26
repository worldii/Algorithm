import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    static class Egg {

        int naegu;
        int weight;

        Egg(int naegu, int weight) {
            this.naegu = naegu;
            this.weight = weight;
        }
    }

    public static int maxSum = 0;
    public static List<Egg> eggs = new ArrayList<>();

    public static int[] naeGuList;

    public static void main(String[] args) throws IOException {
        /*
         * 첫째 줄에 계란의 수를 나타내는 N(1 ≤ N ≤ 8)가 주어진다.
         *  그 다음 N개의 줄에는 계란의 내구도와 무게에 대한 정보가 주어진다.
         * i+1번째 줄에는 왼쪽에서 i번째에 위치한 계란의 내구도 Si(1 ≤ Si ≤ 300)와 무게 Wi(1 ≤ Wi ≤ 300)가 한 칸의 빈칸을 사이에 두고 주어진다.
         * */

        int num = Integer.parseInt(br.readLine());
        naeGuList = new int[num];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            eggs.add(new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            naeGuList[i] = eggs.get(i).naegu;
        }
        dfs(0, eggs.size(), 0);
        System.out.println(maxSum);
    }

    public static void dfs(int start, int end, int sum) {
        maxSum = Math.max(sum, maxSum);
        if (start == end ) {
            return;
        }
        if (naeGuList[start] <=0) {
            dfs(start+1, end, sum);
            return;
        }
        // 계란 깨뜨림
        for (int i = 0; i < end; i++) {
            if (i == start) {
                continue;
            }
            if (naeGuList[i] <= 0) {
                continue;
            }
            int tempSum = sum;
            naeGuList[i] -= eggs.get(start).weight;
            if (naeGuList[i] <=0) tempSum++;
            naeGuList[start] -= eggs.get(i).weight;
            if (naeGuList[start]<=0) tempSum++;
            dfs(start + 1, end, tempSum);
            naeGuList[start] += eggs.get(i).weight;
            naeGuList[i] += eggs.get(start).weight;
        }

    }

}