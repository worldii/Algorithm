import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n;

    public static HashMap<String, Integer> idxMap = new HashMap<>();
    public static List<String> strArr = new LinkedList<>();

    public static Map<String, List<String>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i =0  ; i< n ; i++) {
            String str = br.readLine();
            if (idxMap.containsKey(str)) continue;
            idxMap.put(str, i);
            strArr.add(str);
            for (int j = 1 ; j<= str.length() ; j++) {
                List<String> orDefault = map.getOrDefault(str.substring(0, j), new LinkedList<>());
                orDefault.add(str);
                map.put(str.substring(0,j),orDefault);
            }
        }

        int maxTotalNum = 0;
        for (String key : map.keySet()) {
            if (map.get(key).size() >=2 ) {
                maxTotalNum = Math.max(maxTotalNum, key.length());
            }
        }

        int maxIdx = Integer.MAX_VALUE;
        List<String> realString = null;
        for (String key : map.keySet()) {
            if (key.length() == maxTotalNum && map.get(key).size()>=2) {
                //System.out.println(key + " "+ map.get(key)) ;
                List<String> strings = map.get(key);
                int findSFirstNum = findfirstIdx(strings);
                if (maxIdx > findSFirstNum) {
                    maxIdx = findSFirstNum;
                    realString = strings;
                }
            }
        }
        if (realString == null) {
            System.out.println(strArr.get(0));
            System.out.println(strArr.get(1));
        }
        else {
            System.out.println(realString.get(0));
            System.out.println(realString.get(1));
        }
    }

    private static int findfirstIdx(List<String> strings) {
        int idx = Integer.MAX_VALUE;
        for (int i = 0 ; i< strings.size() ; i++) {
            idx = Math.min(idx, idxMap.get(strings.get(i)));
        }
        return idx;
    }


}