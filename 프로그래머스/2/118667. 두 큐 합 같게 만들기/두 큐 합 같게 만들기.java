import java.util.*;
class Solution {
    public class QueueData {
        Queue<Integer> queue;
        long totalCount; 
        
        QueueData(final int[] queueArr){
            queue = new LinkedList<>();
            for (int i = 0 ; i< queueArr.length ; i++) {
            queue.add(queueArr[i]);
            }
            totalCount = getCount(queueArr);
        }
        
        void add(int num) {
            totalCount += num;
            queue.add(num);
        }
        
        int poll(){
            int num =  queue.poll();
            totalCount -= num;
            return num;
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }
    public int solution(int[] queue1, int[] queue2) {
        QueueData q1 = new QueueData(queue1);
        QueueData q2 = new QueueData(queue2);
        
        int count =0;        
        while (q1.totalCount != q2.totalCount) {
            if (q1.isEmpty() || q2.isEmpty()) return -1;
            if (count > 2* (queue1.length + queue2.length)) return -1;
        
            if (q1.totalCount  < q2.totalCount) {
                int num = q2.poll();
                q1.add(num);
            }
            else {
                int num = q1.poll();
                q2.add(num);
            }
            count++;
        }
        return count ;
    }
    
    private long getCount(final int [] queue) {
        long count = 0;
        for (int i = 0 ; i< queue.length ; i++) {
            count += queue[i];
        }
        return count;
    }
}