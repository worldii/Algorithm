import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n;
    //(100~1~|01)~
    public static void main(String[] args) throws IOException {

        String str = br.readLine();

        // 100~1~|01    에 match 하는지 따짐

        if (str.matches("(100+1+|01)+")) System.out.println("SUBMARINE");
        else System.out.println("NOISE");
    }
}