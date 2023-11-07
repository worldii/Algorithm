import java.util.*;
class Solution {

    public Map<String, Integer> map = new HashMap<>();
    public Map<String, Integer> map2 = new HashMap<>();

 
    public boolean checkStr(String str) {
        for (int i = 0 ; i< str.length() ; i++) {
            if (!('A' <= str.charAt(i) && str.charAt(i) <= 'Z')) return false;
        }
        return true;
    }
    public void makeMap(String str, Map<String, Integer> map) {
        str = str.toUpperCase();
        for (int i = 0 ; i< str.length() -1; i++) {
            String key = str.substring(i, i+2);
            if (!checkStr(key)) continue;
            map.put(key, map.getOrDefault(key, 0)+1);
        }
    }
    public int getHab () {
        // keySet을 모음
        Set<String> set = new HashSet<>();
        for (final String key : map.keySet()){
            set.add(key);
        }
        for (final String key : map2.keySet()){
            set.add(key);
        }
        int count =0;
        for (String key : set) {
            int num = map.getOrDefault(key, 0);
            int num2 = map2.getOrDefault(key, 0);
            count += Math.max(num, num2);
        }
        return count;
    }
    
       public int getGyo () {
        // keySet을 모음
        Set<String> set = new HashSet<>();
        for (final String key : map.keySet()){
            set.add(key);
        }
        for (final String key : map2.keySet()){
            set.add(key);
        }
        int count =0;
        for (String key : set) {
            int num = map.getOrDefault(key, 0);
            int num2 = map2.getOrDefault(key, 0);
            count += Math.min(num, num2);
        }
        return count;
    }
    
    public int solution(String str1, String str2) {
        makeMap(str1, map);
        makeMap(str2, map2);
        try {
            return getGyo() * 65536 / getHab();
            
        }
       catch (Exception e) {
                return 65536;
            }

    }
}