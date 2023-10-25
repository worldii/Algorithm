import java.util.*;
class Solution {
    public Map<String, Integer> number = new HashMap<>();
    
    public int solution(String s) {
        int answer = 0;
        number.put("zero", 0);number.put("one", 1);
        number.put("two", 2);number.put("three", 3);
        number.put("four", 4);number.put("five", 5);
        number.put("six", 6);number.put("seven", 7);
        number.put("eight", 8);number.put("nine", 9);

        for (int i = 0 ; i< s.length() ; i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                answer = answer * 10 + s.charAt(i)-'0';
            }
            else {
                int idx = i+1;
                String key = s.substring(i,idx);
                while (idx < s.length() && number.get(key) ==null) {
                    idx++;
                    key = s.substring(i, idx);
                }
                int value = number.get(key);
                i = idx-1;
                answer = answer * 10 + value;
            }
        }
        return answer;
    }
}