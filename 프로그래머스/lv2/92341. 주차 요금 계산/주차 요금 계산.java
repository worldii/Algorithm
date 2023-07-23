import java.util.*;
class Solution {


    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new TreeMap<>();
        for (String temp : records) {
            String []str = temp.split(" ");
            int time = 0;
            time = str[2].equals("IN") ? -1 : 1;
            String [] duration = str[0].split(":");
            time *= Integer.parseInt(duration[0]) * 60 + Integer.parseInt(duration[1]);
            Integer num = map.getOrDefault(str[1], 0);
            map.put(str[1], num+time);
        }
        int idx=0;
        int [] answer= new int[map.size()];
        for (Integer num : map.values()) {
            if (num < 1) num += 1439;
            int cost = (fees[1]);
            int basicTime = (fees[0]);
         //   System.out.println(num);
            if (basicTime < num) {
                int perTime =  (fees[2]) ;
                int perCost = (num - basicTime) % perTime ==0? (num- basicTime)/perTime: (num- basicTime)/perTime+1;
                perCost *= (fees[3]);
                cost += perCost;
            }
            //System.out.println(cost);
        answer[idx++]= cost;
        }
        return answer;
    }
}