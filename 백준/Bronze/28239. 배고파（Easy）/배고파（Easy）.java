import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n;

    public static int getCnt (long num) {
       String str=  Long.toBinaryString(num);

       for (int i = str.length()-1,  j=0; i>= 0 ; i--, j++) {
           if (str.charAt(i)=='1') return j;
        }
       return 0;

    }

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        for (int  i = 0  ;i< n ; i++) {
            long num = Long.parseLong(br.readLine());
            // num 2의 제곱근 판별 & (앤드해서 1이 하나여야 함)
            long digit = 1; // 2^0
            while (true) {
                long num1= digit;
                long num2= num-num1;
               // System.out.println(num1+ " " + num2);
                if (num1 > num2) break;
                if (Long.bitCount(num2) ==1)
                {
                    System.out.println(getCnt(num1) + " "+getCnt(num2));
                    break;
                }
                digit= digit<<1;
            }
        }

    }
}
