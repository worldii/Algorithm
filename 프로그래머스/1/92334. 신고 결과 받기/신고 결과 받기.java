import java.util.*;

class Solution {
    public Map<String, ArrayList<String>> nameMap = new HashMap<>();
    public Map<String, Integer> cntMap = new HashMap<>();
    public Set<String> set = new HashSet<>();
    public int[] solution(String[] id_list, String[] report, int k) {
        for (String id : id_list){
            nameMap.put(id, new ArrayList<>());
            cntMap.put(id, 0);
        }
        
        
        for (int i = 0 ; i< report.length ; i++) {
            if (set.add(report[i])) {
                String[] reports = report[i].split(" ");
                ArrayList<String> ids = nameMap.get(reports[0]);
                cntMap.put(reports[1],cntMap.get(reports[1])+1);
                ids.add(reports[1]);
                nameMap.put(reports[0], ids);
            }
        }
        
        int[] answer = new int [id_list.length];
        int idx= 0;
        for (int i = 0 ; i< id_list.length ; i++) {
            answer[idx++] = getCount(id_list[i],k);
        }
        return answer;
    }
    
    private int getCount(String name, int k) {
        int count =0;
        ArrayList<String> reporters = nameMap.get(name);
        for (String reporter : reporters) {
            if (cntMap.get(reporter) >= k) count++;
        }
        return count;
    }
}