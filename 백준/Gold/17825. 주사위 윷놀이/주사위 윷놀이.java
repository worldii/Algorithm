import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Horse {
    int routeNum; // 경로 단위의 넘버
    int routeIdx; // 경로에서 인덱스
    int num; // 실제 넘버

    public Horse(int a, int b, int c) {
        this.routeNum = a;
        this.routeIdx = b;
        this.num = c;
    }
}

public class Main {
    // 0번 경로 (2,4,6,8,10,12,14,16,18,20, 22,24, 26, 28, 30, 32, 34, 38, 38, 40)
    // 1번 경로 (10,13,16,19,25,30, 35,40) -> 10일 때 1번 경로로 감.
    // 2번 경로 (20,22,24,25,30,35,40)  -> 20일 때 2번 경로로 감.
    // 3번 경로 (30,28,27,26,25,30,35,40) -> 30일 때 3번 경로로 감.
    public static int[][] routes = {
            {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
            {0, 13, 16, 19, 25, 30, 35, 40},
            {0, 22, 24, 25, 30, 35, 40},
            {0, 28, 27, 26, 25, 30, 35, 40}
    };

    public static boolean notUniqueCheck(int num, boolean[][] checked) {
        if (num == 25) {
            if (checked[1][4] || checked[2][3] || checked[3][4]) return true;
        } else if (num == 30) {
            if (checked[1][5] || checked[2][4] || checked[3][5]) return true;
        } else if (num == 35) {
            if (checked[1][6] || checked[2][5] || checked[3][6]) return true;
        } else if (num == 40) {
            if (checked[1][7] || checked[2][6] || checked[3][7] || checked[0][20]) return true;
        }
        return false;
    }
    public static Horse[] horseLoc = new Horse[4];
    public static int[] arr = new int[10];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static boolean[][] checked = null;
    public static int totalSum = -1;

    // 항상 최선의 선택을 해야함.
    public static void recur(int cnt, int sum, boolean[][] checked, Horse[] horseLoc) {
        if (cnt == 10) {
            totalSum = Math.max(totalSum, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            Horse cur = horseLoc[i];
            int dice = arr[cnt];
            int nextNum = 0;
            if (cur.routeNum == -1) {
                // 이미 40을 넘은 것임
                continue;
            } else if (cur.routeNum == 0 && (cur.num == 10 || cur.num == 20 || cur.num == 30)) {
                // 루트를 바꾸어 주어야 함.
                nextNum = routes[cur.num / 10][dice];
                if (checked[1][dice]) continue;
                if (notUniqueCheck(nextNum, checked)) continue;
                horseLoc[i] = new Horse(cur.num / 10, dice, nextNum);
            } else {
                // 그 외의 경우에는 그냥 감.
                if (cur.routeIdx + dice > routes[cur.routeNum].length - 1) {
                    // 40을 넘은 경우임
                    // 40 이미 지났다는 것을 처리해주기 위하여 현재의 routeNum을 -1로 처리함.
                    // curNum = 0임 더이상 더해주지 말아야 함.
                    horseLoc[i] = new Horse(-1, -1, 0);
                } else {
                    nextNum = routes[cur.routeNum][cur.routeIdx + dice];
                    if (checked[cur.routeNum][cur.routeIdx + dice]) continue;
                    if ((nextNum == 25 || (nextNum == 30 && cur.routeNum != 0) || nextNum == 35 || nextNum == 40) && notUniqueCheck(nextNum, checked))
                        continue;
                    horseLoc[i] = new Horse(cur.routeNum, cur.routeIdx + dice, nextNum);
                }
            }
            // 그 이후에는 방금 전에 간거  false 로 표시 해줘야함.
            if (cur.routeNum != -1) {checked[cur.routeNum][cur.routeIdx] = false;}
            if (horseLoc[i].routeNum != -1) {checked[horseLoc[i].routeNum][horseLoc[i].routeIdx] = true;}

            recur(cnt + 1, sum + nextNum, checked,  horseLoc);

            // 다시 복귀 해줘야함.
            if (cur.routeNum != -1) checked[cur.routeNum][cur.routeIdx] = true;
            if (horseLoc[i].routeNum != -1) {checked[horseLoc[i].routeNum][horseLoc[i].routeIdx] = false;}
            horseLoc[i] = cur;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // horse 넘버 초기화
        for (int i = 0; i < 4; i++) {
            horseLoc[i] = new Horse(0, 0, 0);
        }
        checked = new boolean[4][21];
        recur(0, 0, checked, horseLoc);
        System.out.print(totalSum);
    }
}