import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Cor {
    int x;
    int y;

    Cor(int x, int y) {
        this.x = x;
        this.y = y;

    }
}

public class Main {
    public static int[][] arr;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static boolean[] checked = null;
    public static List<Cor> zeroList = new ArrayList<>();

    public static boolean check (int [][]arr, int flag ) {
       for (int i = 0 ; i< 3; i++) {
            int sum = 0;
            for (int j=0 ;  j< 3; j++) {
                if (arr[i][j] == flag) sum++;
            }
            if (sum == 3) return true;
       }
       for (int j = 0 ; j< 3; j++) {
            int sum =0;
            for (int i = 0 ; i< 3 ; i++) {
                if (arr[i][j]== flag) sum++;
            }
            if (sum==3) return true;
        }

       int sum = 0;
       for (int i = 0 ; i< 3 ; i++) {
           if (arr[i][i] == flag) sum++;
       }
       if (sum ==3) return true;

       sum = 0;
       for (int i =0 ; i< 3; i++) {
           if (arr[i][2-i] == flag )sum++;
       }
       if (sum ==3) return true;
        return false;
    }
    public static  void printArr(int [][] arr2) {
        for (int i = 0 ; i< 3; i++) {
            for (int j = 0 ; j<3 ; j++) {
                System.out.print(arr2[i][j]+" ");
            }System.out.println();
        }
        System.out.println();

    }

    // 항상 최선의 선택을 해야함.
    public static int recur(int cnt, int[][] arr, int k, int flag) {

        if (cnt == k) {
            // 비기는 경우임
            return 0;
        }

        // 지는 경우라 가정
        int result =-1;

        // 최선의 결과를 넣어줘야 함
        for (int i = 0 ; i< zeroList.size(); i++) {
            if (checked[i]) continue;
            checked[i] = true;
            arr[zeroList.get(i).x][zeroList.get(i).y] =flag;

            // 체크를 여기서 함.
            if (check(arr,flag)) {
                arr[zeroList.get(i).x][zeroList.get(i).y] = 0;
                checked[i] = false;
                return 1;
            }

            // 그 다음 꺼  넣어줌
            int oldFlag = flag;
            if(flag == 1) flag =2;
            else flag =1;

            // 상대편의 결과를 뒤집으면 내가 이긴 것임
            int tempResult = recur(cnt+1, arr, k,flag);
            result = Math.max(tempResult *-1, result);

            // 되돌아 가기
            arr[zeroList.get(i).x][zeroList.get(i).y] = 0;
            checked[i] = false;
            flag = oldFlag;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        arr = new int[3][3];
        int zero = 0;
        int one =0;
        int two = 0;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) zeroList.add(new Cor(i,j));
                if (arr[i][j] == 1) one++;
                else if (arr[i][j] == 2) two++;
            }
        }
        int flag =1;
        if (one > two) flag =2;
        checked = new boolean[zeroList.size()];

        if (zeroList.size() >= 8) { System.out.println("D"); return ;}
        // flag = 1부터 시작함.
        int temp = recur(0, arr, zeroList.size(), flag);
        if (temp == 1) System.out.println("W");
        else if (temp == -1 ) System.out.println("L");
        else System.out.println("D");
    }
}