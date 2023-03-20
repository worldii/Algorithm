import java.util.*;
import java.util.Map.Entry;
class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0 ; i< participant.length ; i++) {
            map.put(participant[i], map.getOrDefault(participant[i],0)+1);
        }
        for (int i = 0 ; i<completion.length ; i++) {
            map.put (completion[i], map.get(completion[i]) -1);
        }
        
        for ( Entry<String, Integer> elem : map.entrySet())     {
            if (elem.getValue() > 0) {
                return elem.getKey();
            }
        }
        
        return null;
    }
}