import java.util.*;
class Solution {
    public static Map<String, Integer> map = new HashMap<>( );
    public static class Node {
        
        String node;
        int num;
        Node(String node, int num){
            this.node= node;
            this.num = num;
        }
    }
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        for (int i = 0 ; i< cities.length ; i++) {
            // 캐쉬 hit!
            if (map.get(cities[i].toUpperCase()) != null) {
                answer += 1;
                map.put(cities[i].toUpperCase(), i);
            } else {
                if (map.size() == cacheSize) {
                    // 맨 처음꺼 지우기 
                    // 새로운 것 넣어주기
                    String targetKey = null;
                    int maxNum = Integer.MAX_VALUE;
                    for (String key : map.keySet()) {
                        if (maxNum > map.get(key.toUpperCase())) {
                            maxNum = map.get(key.toUpperCase());
                            targetKey= key;
                        }
                    }
                    map.remove(targetKey);
                }
                if (map.size() < cacheSize) map.put(cities[i].toUpperCase(),i );
                answer+= 5;
            }
        }
        return answer;
    }

}