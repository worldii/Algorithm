import java.util.*;

class Solution {

    public int resultCnt = 0;
    
    public String[][] relation;
    public Set<Set<Integer>> candidateKeys= new HashSet<>();
  
    public boolean isPossible(Set<Integer> set) {
        for (Set<Integer> candidateKey : candidateKeys) {
            if (set.containsAll(candidateKey)) return false;
        }
        
        Set<String> totalSet = new HashSet<>();
        for (int i = 0 ; i< relation.length ; i++) {
            StringBuilder sb = new StringBuilder();
            for (Integer key : set) {
                sb.append(relation[i][key]);
            }
            totalSet.add(sb.toString());
        }
        if (totalSet.size() == relation.length) return true;
        return false;
    }
    
    public void recur(int start, int end, Set<Integer> set, int cur) {
        if (start == end) {
            if (isPossible(set)) {
                resultCnt++;
                candidateKeys.add(set);
            }
            return ;
        }
    
        for (int i = cur ; i< relation[0].length ; i++) {
            Set<Integer> newSet = new HashSet<>(set);
            newSet.add(i);
            recur(start+1, end, newSet, i+1);
        }
    }
    

    public int solution(String[][] relation) {
        this.relation = relation;
        
        for (int i = 1 ; i<= relation[0].length; i++){
            recur(0, i, new HashSet<>(), 0);
        }
        return candidateKeys.size();
    }
}