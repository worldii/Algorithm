import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static StringTokenizer st= null;
    public static boolean flag = false;
    public static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        String S,T;
        S = br.readLine();
        T = br.readLine();
        flag = false;
        recur (S, T);
        if (flag) System.out.println(1);
        else System.out.println(0);
    }

    // 0
    // 1010

    private static void recur(String s, String t) {

       // System.out.println(s + " " + t);
        if (t.equals(s)) {
            flag = true;
            return;
        }
      
        if (t.length() -1 >= s.length()  && t.charAt(t.length()-1) == 'A') recur(s,t.substring(0,t.length()-1));
        // b 제거
        StringBuilder sb = new StringBuilder(t.substring(1)).reverse();
        if (t.length() -1 >= s.length() &&  t.charAt(0) == 'B') recur(s,sb.toString());
    }
}
