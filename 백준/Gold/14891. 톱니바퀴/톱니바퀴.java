import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static StringTokenizer st = null;

    static class Tokni {
        int[] wheel;
        int direction;

        Tokni(int[] wheel, int direction) {
            this.wheel = wheel;
            this.direction = direction;
        }

        void changeDirection() {
            int before = wheel[7];
            for (int i = 7; i >= 1; i--) {
                wheel[i] = wheel[i - 1];
            }
            wheel[0] = before;
        }

        void changeBanDirection() {
            int before = wheel[0];
            for (int i = 0; i < 7; i++) {
                wheel[i] = wheel[i + 1];
            }
            wheel[7] = before;
        }

        public int getThirdSi() {
            return this.wheel[2];
        }

        public int getSixSi() {
            return this.wheel[6];
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getDirection() {
            return this.direction;
        }

        public int getTwelveSi() {
            return this.wheel[0];
        }
    }

    public static List<Tokni> tokniList = new ArrayList<>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            int[] arr = new int[8];
            for (int j = 0; j < str.length(); j++) {
                arr[j] = str.charAt(j) - '0';
            }
            tokniList.add(new Tokni(arr, 0));
        }

        final int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            tokniList.forEach(tokni -> tokni.setDirection(0));

            final int num = Integer.parseInt(st.nextToken());
            final int direction = Integer.parseInt(st.nextToken());
            tokniList.get(num - 1).setDirection(direction);
            checkLeftDirection(num - 1);
            checkRightDirection(num - 1);

            tokniList.forEach(tokni -> {
                if (tokni.getDirection() == 1) {
                    tokni.changeDirection();
                } else if (tokni.getDirection() == -1) {
                    tokni.changeBanDirection();
                }
            });
        }
        int sum = 0;
        int digit = 1;
        for (int i = 0; i < 4; i++) {
            if (tokniList.get(i).getTwelveSi() == 1) sum += digit;
            digit *= 2;
        }
        System.out.println(sum);
    }

    private static void checkRightDirection(int target) {
        // 오른쪽
        if (target - 1 >= 0 && tokniList.get(target).getSixSi() != tokniList.get(target - 1).getThirdSi()) {
            tokniList.get(target - 1).setDirection(-1 * tokniList.get(target).getDirection());
            checkRightDirection(target - 1);
        }
    }

    private static void checkLeftDirection(int target) {
        // 왼쪽
        if (target + 1 <= 3 && tokniList.get(target).getThirdSi() != tokniList.get(target + 1).getSixSi()) {
            tokniList.get(target + 1).setDirection(-1 * tokniList.get(target).getDirection());
            checkLeftDirection(target + 1);
        }
    }

}
