import java.util.*;
class Solution {
    private int [] emoticons;
    private int[][] users;
    public List<Node> answerList = new ArrayList<>();

    class Node {
        long emoPlus;
        long total;
        Node (long emoPlus, long total) {
            this.emoPlus=emoPlus;
            this.total = total;
        }
    }  
    public long maxEmoPlus = -1;
    public long totalEmo = -1;
    public void dfs(int [] discount, int start ,int end) {
        if (start == end) {
            long emoPlus = 0;
            long total = 0;
            for (int i = 0 ; i < this.users.length ; i++){
                long count =0;
                for (int j= 0 ; j< discount.length ;j++) {
                    if (discount[j] < this.users[i][0]) continue;
                    count += ((100 - discount[j]) * this.emoticons[j] / 100);
                }
                if (count>= this.users[i][1]) emoPlus++;
                else total += count;
            }
            answerList.add(new Node(emoPlus, total));
            return ;
        }
        for (int i = 10 ; i<= 40; i+=10) 
        {
            discount[start] = i;
            dfs(discount, start+1, end);
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        this.emoticons = emoticons;
        this.users= users;
        
        int [] discount = new int [emoticons.length];
        
        dfs(discount, 0, emoticons.length);
        
        Collections.sort(answerList, (a,b)-> {
            if (a.emoPlus != b.emoPlus) return (int) b.emoPlus - (int) a.emoPlus;
            return (int) b.total -(int) a.total;
        });
        
        return getAnswer();
    }
    private int[] getAnswer(){
        Collections.sort(answerList, (a,b)-> {
            if (a.emoPlus != b.emoPlus) return (int) b.emoPlus - (int) a.emoPlus;
            return (int) b.total -(int) a.total;
        });
        return new int[]{(int)answerList.get(0).emoPlus, (int) answerList.get(0).total};
    }
}