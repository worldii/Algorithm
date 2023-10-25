import java.util.*;
class Solution {
    public Map<String, String> userName = new HashMap<>();
    public static final int ENTER = 0;
    public static final int LEAVE = 1;
    public static final int CHANGE = 2;
    public class Person {
        int status;
        String uuid;
        Person(int status, String uuid) {
            this.status = status;
            this.uuid= uuid;
        }
        
    }
    public static List<Person> list = new ArrayList<>();

    public String[] solution(String[] record) {
        for (int i = 0 ; i< record.length ; i++) {
            String[] name = record[i].split(" ");
            if (name[0].equals("Change")){
                userName.put(name[1], name[2]);
                continue;
            }
            if (name[0].equals("Enter")) {
                userName.put(name[1], name[2]);
                list.add(new Person(ENTER, name[1]));
            }
            if (name[0].equals("Leave")) {
                list.add(new Person(LEAVE, name[1]));
            }
        }
        String[] answer = new String[list.size()];
        for (int i = 0 ; i< list.size();i++) {
            StringBuilder sb= new StringBuilder();
            String name = userName.get(list.get(i).uuid);
            sb.append(name);
            if (list.get(i).status == ENTER) sb.append("님이 들어왔습니다.");
            else sb.append("님이 나갔습니다.");
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}