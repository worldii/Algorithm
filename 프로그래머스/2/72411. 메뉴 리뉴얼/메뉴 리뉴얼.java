import java.util.*;
class Solution {
    /*
        손님은 단품 메뉴를 2개이상 주문해야하며, 단품 메뉴는 A-Z 알파벳 대문자로 표기.
    */
    public List<String> answers = new ArrayList<>();
    public Map<String, Integer> map = new HashMap<>();
    public void recur(String order, int start, int end, int [] idx, int cur) {
        if (start == end) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i< idx.length ; i++) {
                sb.append(String.valueOf(order.charAt(idx[i])));
            }
            int num = map.getOrDefault(sb.toString(),0)+1;
            map.put(sb.toString(), num);
            return ;
        }
        for (int i = cur ; i<order.length() ; i++){
            idx[start] = i;
            recur(order, start+1, end, idx, i+1);
        }
    }
    public int []maxNum = new int[21];
    public String[] solution(String[] orders, int[] course) {
        for (String order : orders) {
            for (int j = 0 ; j< course.length; j++) {
                int[] idx = new int[course[j]];
                 char[] chars = order.toCharArray();
                 Arrays.sort(chars);
                order = new String(chars);
                recur(order, 0, course[j], idx, 0);
            }
        }
        
        for (String key : map.keySet()){
            if (map.get(key)>=2) {
                maxNum[key.length()]= Math.max(maxNum[key.length()], map.get(key));
            }
        }
      
        for (int i = 0 ; i< course.length; i++) {
            int num = maxNum[course[i]];
            for (String key : map.keySet()){
                if (map.get(key) == num && key.length() == course[i]) {
                  answers.add(key);
                }
            }
        }
        Collections.sort(answers);
        String [] answer = new String[answers.size()];
        int idx = 0;
        for (String ans : answers) {
            answer[idx++] = ans;
        }
        
        return answer;
    }
}