import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static PriorityQueue<Integer> pq;
    public static PriorityQueue<Integer> pq2;



    public static void main(String[] args) throws IOException {

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            pq = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });
            pq2 = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });

            HashMap<Integer, Integer> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                int tempNum = Integer.parseInt(st.nextToken());
                if (str.equals("I")) {
                    pq.add(tempNum);
                    pq2.add(tempNum);
                    map.put(tempNum, map.getOrDefault(tempNum, 0) + 1);
                } else {
                    if (tempNum == -1) {
                        if (pq2.isEmpty()) {
                            continue;
                        }
                        ;
                        // 최소값
                        while (!pq2.isEmpty()) {
                            int num2 = pq2.peek();
                            pq2.poll();
                            if (map.get(num2) > 0) {
                                map.put(num2, map.get(num2) - 1);
                                break;
                            }
                        }

                    } else {
                        if (pq.isEmpty()) {
                            continue;
                        }
                        ;
                        int num2 = pq.peek();
                        while (!pq.isEmpty()) {
                            num2 = pq.peek();
                            pq.poll();
                            if (map.get(num2) > 0) {
                                map.put(num2, map.get(num2) - 1);
                                break;
                            }
                        }
                    }
                }
            }
            // 최댓값 찾기
            boolean flag = false;
            int maxNum=0;
            while (!pq.isEmpty()) {
               int  num2 = pq.peek();
                pq.poll();
                if (map.get(num2) > 0) {
                    System.out.print(num2 + " ");
                    map.put(num2, map.get(num2) - 1);
                     maxNum = num2;

                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("EMPTY");
                continue;
            }
            flag = false;
            while (!pq2.isEmpty()) {
                int num2 = pq2.peek();
                pq2.poll();

                if (map.get(num2) > 0) {
                    System.out.println(num2);
                    flag = true;

                    map.put(num2, map.get(num2) - 1);
                    break;
                }
            }
            if (!flag) {
                System.out.println(maxNum);
                continue;
            }
        }
    }
}
