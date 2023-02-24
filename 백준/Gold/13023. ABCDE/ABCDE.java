import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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

class Friend {
    private int []friends ;
    private int idx ;

    Friend(int n) {
        friends = new int [n];
        idx =0;
    }

    public int[] getFriends() {
        return friends;
    }

    public int getIdx() {
        return idx;
    }

    public void addRelation (int n) {
        friends[idx++] = n;
    }
}
public class Main {

    public static int n;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int minNum = 0;
    public static  Friend [] person;
    public static void recur(int cnt,int num,boolean[] checked) {
        if (num == 4) {

            minNum = 1;
            return ;
        }

        // 친구들
        int [] relationShip = person[cnt].getFriends();
        for (int i = 0 ; i< person[cnt].getIdx(); i++) {
            if (!checked[relationShip[i]]) {
                checked[relationShip[i]] = true;
                recur(relationShip[i], num+1, checked);
                checked[relationShip[i]] = false;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        person = new Friend[n];
        for (int i = 0 ; i< n ; i++) {
            person[i] = new Friend(n);
        }

        for (int i = 0 ; i< m ; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second =Integer.parseInt(st.nextToken());

            person[first].addRelation(second);
            person[second].addRelation(first);

        }
        for (int i =  0 ; i<n;  i++) {
            boolean [] check = new boolean[n];
            check[i] = true;
            if (minNum==0) recur(i, 0, check);
        }

        System.out.println(minNum);
    }
}