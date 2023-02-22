import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[][] arr;
    static String str = "";

    public static void recur(int x, int y, int size) {

        //System.out.println(x+ " " + y+ " " + size);
        if (size == 1) {
            str += Character.toString(arr[x][y]);
            return;
        } else {
            // 또다른 종료 조건
            // 다 같을 경우에 사이즈 만큼
            boolean isSame = true;
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    if (arr[x][y] != arr[i][j]) {
                        isSame = false;
                    }
                }
            }
            if (isSame) {
                str += Character.toString(arr[x][y]);
                return;
            }

        }

        int half = size / 2;
        str+= "(";
        recur(x, y, half);
        recur(x, y + half, half);
        recur(x + half, y, half);
        recur(x + half, y + half, half);
        str+= ")";
    }

    public static void main(String[] args) throws IOException {

        int num = Integer.parseInt(br.readLine());
        arr = new char[num][num];
        for (int i = 0; i < num; i++) {

            String str = br.readLine();
            arr[i] = str.toCharArray();
        }
        recur(0, 0, num);
        System.out.println(str);
    }


}

