import java.util.*;
class Solution {
    /*
    1. 대소문자 구분 정렬 하지 않는다. 
    2. 9 , 10, -> 숫자 순으로 정렬한다. -> 이는 0012 = 012 = 12 이다. 
    3. 원래 입력 순
    */
    public class Node {
        private String fileName;
        private String head;
        private int number;
        private int cnt;
        
        private boolean checkNum(char s) {
            return '0' <= s&& s<= '9';
        }
        public Node (String file, int cnt) {
            this.fileName = file;
            int idx =0;
            for (idx = 0 ;idx< file.length(); idx++){
                if (checkNum(file.charAt(idx))) break;
            }
            this.head = file.substring(0, idx).toUpperCase();
            int numberIdx = idx;
            while (idx < file.length() && checkNum(file.charAt(idx))) {
                idx++;
            }
            this.number = Integer.parseInt(file.substring(numberIdx ,idx));
            this.cnt = cnt;
        }
    }
    public List<Node> filesList = new ArrayList<>();
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        int cnt =0;
        for (String file : files) {
            filesList.add(new Node(file,cnt));
            cnt++;
        }
        
        Collections.sort(filesList, (a,b)-> {
            if (!(a.head.equals(b.head))) return a.head.compareTo(b.head);
            else if (a.number!= b.number) return a.number - b.number;
            else return a.cnt -b.cnt;
        });
            
        cnt =0;    
        for (Node file : filesList) {
            answer[cnt++] = file.fileName;
        }
        return answer;
    }
}