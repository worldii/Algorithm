import java.util.*;
class Solution {
    // queue1 
    // queue2 
    // 상태를 갖고 있는 노드
    // 각 큐 원소 합을 같게 만들어야 함. 만들수없다면 -1
    // long 타입의 고려가 필요하다. 

    public long getCount(int [] queue) {
        long count = 0;
        for (int i = 0 ; i< queue.length ; i++) {
            count += queue[i];
        }
        return count;
    }
   
    public int solution(int[] queue1, int[] queue2) {
        long q1Count = getCount(queue1);
        long q2Count = getCount(queue2);
        long total = (q1Count + q2Count ) ;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for (int i = 0 ; i< queue1.length ; i++) {
            q1.add(queue1[i]);
        }
        
        for (int i = 0 ; i< queue2.length ; i++) {
            q2.add(queue2[i]);
        }
        
        int count =0;
        HashSet<String> set = new HashSet<>();
        
        while (q1Count != q2Count) {
            if (q1.isEmpty() || q2.isEmpty()) return -1;
            if (set.contains(q1Count+"" + q1.peek()+ ""+ q2Count+ ""+q2.peek())) return -1;
            set.add(q1Count+ ""+ q1.peek()+""  + q2Count+ q2.peek());
            if (q1Count < q2Count) {
                int num = q2.poll();
                q1Count += num;
                q2Count -= num;
                q1.add(num);
            }
            else {
                int num = q1.poll();
                q2Count += num;
                q1Count -= num;
                q2.add(num);
            }
            count++;
        }
        
        return count ;

    }
}