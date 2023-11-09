class Solution {
    /*
    지표가 주어짐
    */
    public int[] cnt = new int [34];
    
    public void choose( int before, int after, int choice){
        if (choice == 1) cnt[before]+=3;
        else if (choice==2) cnt[before]+=2;
        else if (choice==3) cnt[before]+=1;
        else if (choice==5) cnt[after]+=1;
        else if (choice==6) cnt[after]+=2;
        else if (choice==7) cnt[after]+=3;
    }
    
    public int calculate (String str){
        int before = str.charAt(0) -'A';
        int after = str.charAt(1)-'A';
        if (cnt[before] != cnt[after]) {
            if (cnt[before]< cnt[after]) return after;
            else return before;
        }
        if (before< after) return before;
        return after;
    }
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        for (int i = 0 ; i< survey.length; i++) {
            String str = survey[i];
            choose(str.charAt(0)-'A', str.charAt(1)-'A',choices[i]);
        }
        
        answer +=  (char)('A' + calculate("RT"));
        answer +=  (char)('A' +calculate("CF"));
        answer += (char)('A' +calculate("JM"));
        answer += (char)('A' +calculate("AN"));
        return answer;
    }
}