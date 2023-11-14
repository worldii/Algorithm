import java.util.*;
class Solution {
    public boolean checkBan (String ban, String user) {
        if (user.length() != ban.length()) return false;
        for (int i = 0 ; i< user.length() ; i++) {
            if (ban.charAt(i)== '*') continue;
            if (ban.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }
    
    public HashSet<HashSet<Integer>> strSet= new HashSet<>();
    public ArrayList<ArrayList<String>> strList= new ArrayList<>();
 
    public String [] user_id;
        public String [] banned_id;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        this.user_id= user_id;
        this.banned_id= banned_id;

        for (int i = 0 ; i <= banned_id.length; i++){
            strList.add(new ArrayList<>());
        }
        

        
        HashSet<Integer> set = new HashSet<>();
        boolean [] idxs = new boolean[user_id.length];
        dfs(0, banned_id.length, set);
        
        return strSet.size();
    }
    
    public int count ;
    public void dfs(int start, int end, HashSet<Integer> set){     
        if (start == end) {
            strSet.add(set);
            return ;
        }
        
        for (int i = 0 ;i< user_id.length; i++){
            HashSet<Integer> newSet = new HashSet<>(set);
            if (!checkBan(banned_id[start], user_id[i])) continue;
            if (newSet.add(i)) dfs(start+1, end, newSet);
        }
    }
 
    
}