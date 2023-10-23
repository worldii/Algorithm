import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String str = br.readLine();

        for (int i = 1; i <= 30000; i++) {
            String numStr=  Integer.toString(i);
            // 맨처음 숫자를 재껴야 함
            for (int j = 0 ;j < numStr.length() ;j++) {
                if (numStr.charAt(j) == str.charAt(0)) str = str.substring(1);
                if (str.length() ==0 ){System.out.println(i);return;}
            }

        }
    }
}
