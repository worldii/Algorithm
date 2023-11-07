import java.util.*;
class Solution {
    public Set<String> set = new HashSet<>();
    public Map<String, Integer> windowMap= new HashMap<>();
    public int count ;
    
    public boolean checkFunc(){
        return windowMap.size() == count;
    }
    public int[] solution(String[] gems) {
        for (String gem : gems){
            set.add(gem);
        }
        count = set.size();
        
        int start =0;
        int end = 0;            
        
        windowMap.put(gems[end], 1);
        
        List<int[]> idxs = new ArrayList<>();
        
        while (end < gems.length && start<= end){
            if (checkFunc()) {
                idxs.add(new int[]{start+1, end+1, end-start+1});
                if (windowMap.getOrDefault(gems[start], 0) <= 1) {
                    windowMap.remove(gems[start]);
                }
                else windowMap.put(gems[start], windowMap.getOrDefault(gems[start], 0)-1);
                start++;
            }
            else {
               end++;
               if (end== gems.length) break;
               windowMap.put(gems[end], windowMap.getOrDefault(gems[end], 0)+1);
            } 
        }
        
        Collections.sort(idxs, new Comparator<int[]>()
        {
            public int compare(int[] a, int[]b) {
                if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
                return Integer.compare(a[0], b[0]);
            }
        }
        );
        int[] answer = new int[] {idxs.get(0)[0], idxs.get(0)[1]};
        return answer;
    }
}