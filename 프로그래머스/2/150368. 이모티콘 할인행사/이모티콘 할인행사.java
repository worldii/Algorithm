import java.util.*;
class Solution {
    private int [] emoticons;
    private int[][] users;
    /*
    user - 비율 가격  1-n
    비율 이상의 할인이 있는 이모티콘 모두 구매한다. 
    가격 이상의 돈을 이모티콘 구매에 사용한다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입
    가격은 100 배수 
    */
    // 이모티콘 1,2,3,4,5,6,7 
      // 이모티콘의 할인율 10, 20, 30, 40
    // n 사람들 -> m개를 할인하여 판매 
    // 만약 본인의 가격을 넘어가면,이모티콘 플러스를 구입
    // 이모티콘의 원소는 100의 배수이다. 
    // 1. emoticon의 할인율 정하기 
    // 2. 한명의 유저당, 이모티콘 금액이 초과하는지 따지기-> 
    // 초과한다면 이모티콘 플러스, 아니라면 금액 올리기 
    // 이모티콘, 금액 계산 
    
    public static class Node {
        long emoPlus;
        long total;
        Node (long emoPlus, long total) {
            this.emoPlus=emoPlus;
            this.total = total;
        }
    }
    public List<Node> list = new ArrayList<>();
  
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
            list.add(new Node(emoPlus, total));
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
        Collections.sort(list, (a,b)-> {
            if (a.emoPlus != b.emoPlus) return (int) b.emoPlus - (int) a.emoPlus;
            return (int) b.total -(int) a.total;
        });
        int[] answer = {(int)list.get(0).emoPlus, (int) list.get(0).total };
        
        return answer;
    }
}