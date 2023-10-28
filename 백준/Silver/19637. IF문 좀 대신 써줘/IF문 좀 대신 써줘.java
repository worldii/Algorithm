import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n, m;

    public static class Node {

        String name;
        int limit;

        public Node(String name, int limit) {
            this.name = name;
            this.limit = limit;
        }
    }

    public static List<Node> nodeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        /*
        첫 번째 줄에는 칭호의 개수 N (1 ≤ N ≤ 105)과 칭호를
        출력해야 하는 캐릭터들의 개수 M (1 ≤ M ≤ 105)이 빈칸을 사이에 두고 주어진다. (1 ≤ N, M ≤ 105)
        두 번째 줄부터 N개의 줄에 각 칭호의 이름을 나타내는 길이 1 이상, 11 이하의 영어 대문자로만 구성된 문자열과
        해당 칭호의 전투력 상한값을 나타내는 109 이하의 음이 아닌 정수가 주어진다. 칭호는 전투력 상한값의 비내림차순으로 주어진다.
        N + 2번째 줄부터 M개의 각 줄에는 캐릭터의 전투력을 나타내는 음이 아닌 정수가 주어진다.
        해당하는 칭호가 없는 전투력은 입력으로 주어지지 않는다.
        * */

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nodeList.add(new Node(st.nextToken(), Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());
            int idx = binarySearch(num);
            sb.append(nodeList.get(idx).name).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int binarySearch(int target) {
        int start = 0;
        int end = nodeList.size()-1;
        int beforeIdx  =-1;
        /*
        * (0~10000) (10001~100000) (100001 ~ 1000000)
        * 10000 100000 1000000
        0 9999 10000 10001 50000 100000 500000 1000000
        * */
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nodeList.get(mid).limit < target) {
                start= mid+1;
            } else {
                end = mid;
            }
            if (beforeIdx == mid) break;
            beforeIdx = mid;
        }
        return start;
    }
}