import java.util.*;
class Solution {
    public int [] parent;
    public int getParent(int a){
        if (a==parent[a]) return a;
        parent[a] = getParent(parent[a]);
        return parent[a];
    }
    public boolean union (int a, int b) {
        a= getParent(a);
        b= getParent(b);
        if (a!=b)
        {
            if (a<=b) parent[b] = a;
            else parent[a] = b;
            return true;
        }
        return false;
    }
    public int[] cntArr;
    public int solution(int[] cards) {
        int answer = 0;
        parent = new int[cards.length];
        for (int i = 0 ;  i< cards.length ; i++) {
            parent[i]= i;
        }
        // 0 3 6 7
        // 1 4 5 
        // 2
        cntArr= new int[cards.length];
        for (int i = 0 ; i< cards.length ; i++) {
            union(i, cards[i]-1);
        }
 
        for (int i = 0 ; i< parent.length ; i++) {
            parent[i] = getParent(i);
        }
        for (int i = 0 ; i< parent.length ; i++) {
            cntArr[parent[i]]++;
        }
        Arrays.sort(cntArr);
        answer = cntArr[cntArr.length-1] * cntArr[cntArr.length-2];
        return answer;
    }
}