import java.util.*;
class Solution {
    public class Person {
        int x;
        int y;
        int beforeX;
        int beforeY;

        Person(int x, int y){
            beforeX = -1;
            beforeY = -1;
            this.x= x;
            this.y= y;
        }
        
        
        Person(int x, int y, int beforeX, int beforeY){
            this.beforeX = beforeX;
            this.beforeY = beforeY;
            this.x= x;
            this.y= y;
        }
    }
    public int [] dx ={ -1,1,0,0};
    public int[] dy = {0,0,-1,1};
    
    public int getAnswer(String[] place) {
        Queue<Person> pq = new LinkedList<>();
        boolean[][] check = new boolean[5][5];

        for (int j = 0 ; j< 5; j++) {
            for (int i = 0 ; i< 5; i++) {
                if (place[j].charAt(i) == 'P') {
                    pq.add(new Person(j,i));
                    check[j][i] = true;
                }
            }
        }
        
        int size = 0;
        while (!pq.isEmpty()){
            int qSize = pq.size();
            for (int j= 0; j< qSize; j++) {
                Person person = pq.poll();
                for (int i = 0 ; i< 4 ; i++) {
                  
                    int nextX = dx[i] + person.x;
                    int nextY = dy[i] + person.y;
                    if (0<= nextX && nextX < 5 && 0<= nextY && nextY <5) {
                        if (place[nextX].charAt(nextY) == 'X') continue;
                        if (place[nextX].charAt(nextY)  == 'P' && size <=2) {
                            if (!(person.beforeX == nextX && person.beforeY ==nextY)) return 0;
                        }
                        if (check[nextX][nextY]) continue;

                        check[nextX][nextY] = true;
                        pq.add(new Person(nextX, nextY, person.x, person.y));   
                    }
                }
            }
            size++;
        }
        return 1;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int idx = 0;
        for (String[] place : places) {
            answer[idx++] = getAnswer(place);
        }
        return answer;
    }
}