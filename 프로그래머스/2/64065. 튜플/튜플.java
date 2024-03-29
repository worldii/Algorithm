import java.util.*;
class Solution {

    public Set<Integer> set = new TreeSet<>();
    public int[] solution(String s) {
        
        String temp = s.substring(2,s.length()-2);
        String[] str= temp.split("\\},\\{");
        List<String[]>  strList = new ArrayList<>();
        int maxNum = 0;
        
        for (int ii = 0 ; ii< str.length ; ii++) {
            String[] tt = str[ii].split(",");
            strList.add(tt);
            maxNum = Math.max(maxNum, tt.length);
        }
        
        Collections.sort(strList, (a,b)-> a.length - b.length);
        
        int[] answer = {};
        answer = new int[maxNum];
        int idx =0;
        for (int j = 0 ;  j< strList.size() ; j++) {
            String[] splitArr = strList.get(j);
            for (int jj= 0 ; jj< splitArr.length ; jj++) {
                if (set.contains(Integer.parseInt(splitArr[jj]))) continue;
                set.add(Integer.parseInt(splitArr[jj]));
                answer[idx++] = Integer.parseInt(splitArr[jj]);
            }
        }
       
        return answer;
    }
}