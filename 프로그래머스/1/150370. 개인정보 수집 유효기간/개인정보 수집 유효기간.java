import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Solution {
    /*
        개인정보 n개
        개인정보 <= 유효기간 전까지만 
        - terms 약관종류 유효기간 
        - 공백으로 띄어져있고 약관종류는 A-Z , terms에서 A는 중복되지 않는다. 
        - 유효기간은 1-100이하이다. 
        - privacy - 날짜 약관 종류
        - 날짜 : YYYY.MMDD 
        - 약관종류 : terms에 나타난 것만 

    */
   
    private String[] parsingString (String date) {
        return date.split("\\.");
    }
    private Map<String, Integer> map = new HashMap<>();
    private List<Integer> answer = new ArrayList<>();
    private static final int MONTH = 28;
    private static final int YEAR = 12;
    private int convertNum (String[] dayMonthYear){
        int year = Integer.parseInt(dayMonthYear[0]);
        int month = Integer.parseInt(dayMonthYear[1]);
        int day = Integer.parseInt(dayMonthYear[2]);
        return year * MONTH * YEAR + month * MONTH + day;
    }
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        for (int i = 0 ; i< terms.length ; i++) {
            String[] dateStr = terms[i].split(" ");
            map.put(dateStr[0], Integer.parseInt(dateStr[1]));
        }
        
        for (int i = 0 ; i< privacies.length ; i++) {
            String[] dateStr = privacies[i].split(" ");
            String[] dayMonthYear = parsingString(dateStr[0]);
            
            int dayMonthYearNum = convertNum(dayMonthYear);
            if (dayMonthYearNum + MONTH * map.get(dateStr[1]) <= convertNum(parsingString(today))) {
                answer.add(i+1);
            }
        }
        
        int [] answers= new int [answer.size()];
        for (int i = 0 ; i< answer.size() ; i++) {
            answers[i] = answer.get(i);
        }
        return answers;
    }
}