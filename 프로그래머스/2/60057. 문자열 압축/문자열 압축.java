class Solution {
    private int getDivider(String s) {
        int num = 1;
        while (s.length()/num > 1) num++;
        return num-1;
    }
    private int getMaxLen(String s, int unit) {
        int len = 0;
        if (s.length()%unit==0) {
            String start = s.substring(0,unit);
            int count = 1;
            for (int i = unit; i<s.length(); i+=unit) {
                if (start.equals(s.substring(i,i+unit))){
                    count++;
                }
                else {
                    if(count==1) len += unit;
                    else len += unit+String.valueOf(count).length();
                    start = s.substring(i,i+unit);
                    count = 1;
                }
            }
            if (count!=1) len+=unit+String.valueOf(count).length();
            else len+=unit;
        }
        else {
         
            String start = s.substring(0,unit);
            int count = 1;
            
            for (int i = unit; i<s.length(); i+=unit) {
                if (i+unit > s.length()) {
                    len+= s.length()-i;
                    break;
                }
                if (start.equals(s.substring(i,i+unit))){
                    count++;
                }
                else {
                    if (count!=1) len+=unit+String.valueOf(count).length();
                    else len+=unit;
                    start = s.substring(i,i+unit);
                    count = 1;
                }
            }
            if (count!=1) len+=unit+String.valueOf(count).length();
            else len+=unit;
        }
        return len;
    }
    
    public int solution(String s) {
        int answer = s.length();
        for (int i = 1 ; i<= s.length() ; i++) {
            answer = Math.min(answer, getMaxLen(s, i));
        }
        return answer;
    }
}