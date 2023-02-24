import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [키워드]
 * <p>
 * [풀이과정]
 * <p>
 * [입력]
 * [출력]
 *
 * @author SSAFY
 * @performance
 * @category #그래프, #BFS
 * @see
 */
class Cor {
    int leftR;
    int leftK;
    int rightR;
    int rightK;
    Cor (int leftR, int leftK, int rightR ,int rightK) {
        this.leftR = leftR;
        this.leftK = leftK;
        this.rightK = rightK;
        this.rightR = rightR;
    }

    @Override
    public String toString() {
        return "Cor{" +
                "leftR=" + leftR +
                ", leftK=" + leftK +
                ", rightR=" + rightR +
                ", rightK=" + rightK +
                '}';
    }
}

public class Main {

    public static int R, C;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static char[][] arr;
    public static int minNum = -1;


    public static void main(String[] args) throws IOException {


        //st = new StringTokenizer(br.readLine());
        String str = br.readLine();

        if (str.length()==0 ) {
            System.out.println(0);
            return ;
        }

        int[] left = new int[30];
        int[] right = new int[30];

        int maxSum = 0;
        for (int i = 0; i < str.length(); i++) {
            right[str.charAt(i) - 'A']++;
        }

        Cor [] list = new Cor[str.length()+2];

        for (int i = 0; i < str.length(); i++) {
            right[str.charAt(i) - 'A']--;
            // count 세어줌
            if (str.charAt(i) == 'R') {
//                System.out.println("R Idx : " + i) ;
//                System.out.println("LEFT R" + left['R' - 'A'] + " LEFT K" + left['K' - 'A']);
//                System.out.println("RIGHT R" + right['R' - 'A'] + " RIGHT K" + right['K' - 'A']);

                //int tempSum = 1 + right['R' - 'A'];
                //tempSum = Math.max(1 + left['R' - 'A'], tempSum);
                //tempSum = Math.max(tempSum, 1 + Math.min(right['K' - 'A'], left['K' - 'A']) * 2);
                // System.out.println(tempSum);
            }
            list[i] = new Cor(left['R' - 'A'] , left['K' - 'A'], right['R'- 'A'], right['K'-'A']);
            left[str.charAt(i) - 'A']++;
        }

        int  start = 0;
        int  end = str.length()-1;

        while (start < str.length() &&str.charAt(start) == 'K') start++;
        if (start == str.length() ) {
            System.out.println(0);
            return ;
        }
        while (end> 0 && str.charAt(end)== 'K') end--;

        while (start<= end)
        {

            int tempSum = list[end].leftR - list[start].leftR+  2* Math.min(list[start].leftK,list[end].rightK );
            if ( str.charAt(end) == 'R')
            {
                tempSum ++;
            }
            maxSum = Math.max(tempSum, maxSum);
            if (start == end ) break;

            if (list[start].leftK < list[end].rightK) {
                start++;
                while (str.charAt(start)== 'K') start++;
            }
            else { end--;
                while (str.charAt(end) == 'K') end--;
            }
        }



        System.out.println(maxSum);
    }
}