import java.util.*;
class Solution {
    private boolean checkRightSentence(final String u) {
        if (u.equals(")")) return false;
        Stack<Character> s = new Stack<>();
        for (int i = 0 ; i< u.length() ; i++) {
            char alph = u.charAt(i);
            if (alph == '(') {
                s.push(alph);
            }
            else {
                if (!s.isEmpty()) {
                    if (s.peek() == '(') {
                        s.pop();
                    }

                }
            }
        }
        if (s.size() > 0) return false;
        return true;
        
    }
    
    private boolean checkBalance(String str) {
        int cnt = 0;
        for (int i = 0 ; i<str.length() ; i++) {
            if (str.charAt(i) == '(') cnt++;
        }
        return cnt == str.length()/2;
    }
    
    private String recur(String w) {
        if (w.equals("") || w == null) return "";
        if (checkRightSentence(w)) return w;
        
        int i = 1;
        String u = "";
        String v = "";
        
        while (i <= w.length()) {
            u = w.substring(0,i);
            v = w.substring(i);
            if ((checkBalance(u) && checkBalance(v))) break;
            i++;
        }
        
        StringBuilder total = new StringBuilder();
        if (checkRightSentence(u)) {
            StringBuilder sb = new StringBuilder();
            total.append(u).append(recur(v).toString());
        }
        else {
            StringBuilder sb = new StringBuilder().append("(");
            
            sb.append(recur(v)).append(")");
            
            String newW = "";
            for (int t = 1; t < u.length()-1 ;t++) {
                if (u.charAt(t) == '(') newW += ")";
                else newW+='(';
            }
            //String newU = new StringBuilder().append(newW).reverse().toString();
            sb.append(newW);
            total.append(sb.toString());
        }
        
        return total.toString();
    }
    
    public String solution(String p) {
        String answer = recur(p);
        return answer;
    }
}