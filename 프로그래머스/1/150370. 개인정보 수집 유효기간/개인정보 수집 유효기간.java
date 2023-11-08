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
    public LocalDate getLocalDate (String term) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
       return LocalDate.parse(term, formatter);
    }
    public Map<String, Integer> map = new HashMap<>();
    public boolean checkExpired (LocalDate today, LocalDate privacy, String key) {
       int month =  map.get(key);
       if (privacy.plusMonths(month).compareTo(today) <=0) return true;
       return false;
    }
    public List<Integer> answer = new ArrayList<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        for (int i = 0 ; i< terms.length ; i++) {
            String[] dateStr = terms[i].split(" ");
            map.put(dateStr[0], Integer.parseInt(dateStr[1]));
        }
        
        for (int i = 0 ; i< privacies.length ; i++) {
            String[] dateStr = privacies[i].split(" ");
            if (checkExpired(getLocalDate(today), getLocalDate(dateStr[0]), dateStr[1])) answer.add(i+1);
        }
        int [] answers= new int [answer.size()];
        for (int i = 0 ; i< answer.size() ; i++) {
            answers[i] = answer.get(i);
        }
        return answers;
    }
}