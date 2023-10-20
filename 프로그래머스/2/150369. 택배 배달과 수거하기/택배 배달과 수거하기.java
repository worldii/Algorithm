class Solution {

    public int decrease(int[] arr, int idx) {
        if (idx >=0) {
            if (arr[idx] > 0) {
                arr[idx]--;
            }
            else {
                idx--;
                if (idx==-1) return idx;
                arr[idx]--;
            }
        }
        while (idx>=0 && arr[idx]<=0) idx--;
        return idx;
    }
    public int findMaxNum(int size1, int size2) {
        if (size1 > size2) return size1;
        return size2;
    }
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deIdx = deliveries.length -1;
        int picIdx = pickups.length-1;
        while (deIdx>=0 && deliveries[deIdx] <= 0) deIdx--;
        while (picIdx>=0 && pickups[picIdx] <= 0) picIdx--;
        
        while (deIdx >= 0 || picIdx >=0) {
            int maxLen = findMaxNum(deIdx+1, picIdx+1);
            answer += maxLen * 2;
            for (int i =0 ; i<cap; i++) {
                deIdx = decrease(deliveries,deIdx);
                picIdx = decrease(pickups,picIdx);
            }
        }
     
        return answer;
    }
}