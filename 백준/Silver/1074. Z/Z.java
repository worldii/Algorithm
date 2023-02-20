import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    static int N, R, C, size2;
    static int[][] map;
    static int num;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        size2 = (int) Math.pow(2, N);
        num = 0;
        make(R, C, size2);
        System.out.println(output.toString());

       // System.out.println(num);
    }

    public static void make(int r, int c, int size) {
     //  System.out.println(r + " " + c + " " + num+ " "+ size);
      if( r== 0 && c==0 ) {
          System.out.println(num);
          return ;
      }

        int half = size / 2;
        if (r < half && c < half) {
            make(r, c, half);
        } else if (r < half && c >= half) {
            num = num + half * half;
            make(r, c-half , half);
        } else if (r>= half && c < half) {
            num = num + half * half * 2;
            make(r -half, c, half);
        } else {
            num = num + half * half * 3;
            //System.out.println("num " + num);
            make(r-half , c-half , half);
        }
    }
}

