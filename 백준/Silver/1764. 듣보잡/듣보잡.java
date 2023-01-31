import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> map = new HashMap<>();
		int n = sc.nextInt();
		int m = sc.nextInt();
		for (int i = 0; i < n; i++) {
			String str = sc.next();
			map.put(str, 1);
		}

		for (int j = 0; j < m; j++) {
			String str = sc.next();
			if (map.containsKey(str)) {
				int num = map.get(str) + 1;
				map.put(str, num);
			} else {
				map.put(str, 1);
			}
		}

		List<String> l = new ArrayList<>();
		for (String str : map.keySet()) {
			if (map.get(str) > 1) {
				l.add(str);
			}
		}
		Collections.sort(l);
		System.out.println(l.size());
		for (int i = 0 ; i< l.size(); i++) {
			System.out.println(l.get(i));
		}
	}
}
