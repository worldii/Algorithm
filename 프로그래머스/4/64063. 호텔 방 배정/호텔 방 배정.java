import java.util.*;
class Solution {
    public Map<Long, Long> parent = new HashMap<>();
    public long findParent(long ori, long a) {
        if (parent.get(a) == null) {
            parent.put(a, a+1);
            //parent.put(ori, a+1);
            return a;
        }
        parent.put(a, findParent(ori, parent.get(a)));
        return parent.get(a);
    }
   
    public long[] solution(long k, long[] room_number) {
        long [] answer = new long[room_number.length];
     
        for (int i = 0 ; i< room_number.length ; i++) {
            long num = room_number[i];
            long par = findParent(num, num);
            answer[i] = par;
        }
        
        return answer;
    }
}