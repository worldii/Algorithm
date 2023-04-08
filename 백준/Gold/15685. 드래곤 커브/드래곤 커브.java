import sun.awt.image.ImageWatched;
import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};

    static class dragon {
        int x;
        int y;
        int d;
        int g;

        dragon(int x, int y, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }
    }

    public static void add(boolean[][] check, dragon dragon) {
        int cnt = dragon.g;

        int startX = dragon.x;
        int startY = dragon.y;
        int nextDir = dragon.d;

        ArrayList<Integer> arr = new ArrayList<>();

        check[startX][startY] = true;
        startX = startX + dx[nextDir];
        startY = startY + dy[nextDir];
        arr.add(nextDir);
        check[startX][startY] = true;
        for (int i = 1; i <= cnt; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < arr.size(); j++) {
                stack.add(arr.get(j));
            }
            while (!stack.isEmpty()) {
                int temp = stack.pop();
                nextDir = (temp + 1) % 4;
                arr.add(nextDir);

                startX = startX + dx[nextDir];
                startY = startY + dy[nextDir];
                check[startX][startY] = true;
            }


        }

        // 1
        // 2
        // 3 2
        // 3 0 3 2

    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());


        boolean[][] check = new boolean[101][101];
        for (int i = 0; i < n; i++) {
            int x, y, d, g;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            add(check, new dragon(x, y, d, g));

        }
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                int nextI = i + 1;
                int nextY = j + 1;
                if (0 <= nextY && nextY <= 100 && 0 <= nextI && nextI <= 100) {

                    if (check[i][j] && check[i][nextY] && check[nextI][j] && check[nextI][nextY]) {
                        sum++;

                    }
                }
            }
        }
        System.out.println(sum);
    }


}
