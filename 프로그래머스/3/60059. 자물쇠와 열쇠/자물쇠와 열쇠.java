class Solution {
    /*
     n >= m 
     0 0 0 
     1 0 0
     0 1 1
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 1
    0 0 1 1 1 0 0 m-1 0
    0 0 1 1 0 0 0 1
    0 0 1 0 1 0 0 
    0 0 0 0 0 0 0
    0 0 0 0 0 0 0
    */
    
    public int[][] lockMap;
    public int keySize = 0;
    public int n, m;
    
    public boolean allMatch(int [][] lockMap) {
        int size =0 ;
        for (int i = m ; i< m + n ; i++) {
            for (int j = m ; j< m+n ; j++) {
                if (lockMap[i][j] > 0) size++;
            }
        }
        if (size == n*n) return true;
        return false;
    }
    
    public void lockMapInit(int [][] lock){
        lockMap = new int [m*2 + n][m*2 + n];
        for (int i = m ; i< m + n ; i++) {
            for (int j = m ; j< m+n ; j++) {
                lockMap[i][j] = lock[i-m][j-m];
            }
        }
     }
    public int[][] copy (int [][]arr) {
        int [][] copy = new int[arr.length][arr.length];
        for (int i = 0 ; i< arr.length ; i++) {
            for (int j= 0 ; j< arr.length ; j++) {
                copy[i][j] =arr[i][j];
            }
        }
        return copy;
    }
    public void rotateArr(int [][]arr){
        int [][] copyArr= copy(arr);
        
        for (int i = 0 ; i< arr.length ; i++) {
            for (int j = 0 ; j< arr.length ; j++) {
                arr[i][j] = copyArr[j][arr.length-i-1];                
            }
        }
    }
    public boolean fillMap(int [][] lockMap, int [][] key, int startX, int startY) {
        for (int i = 0 , ii= startX; i < m  ; i++, ii++) {
            for (int j = 0 , jj= startY; j< m ; j++, jj++) {
                if (key[i][j] > 0 && lockMap[ii][jj] == 1) return false;
                if (key[i][j] > 0  && lockMap[ii][jj] ==0) lockMap[ii][jj] =1; 
                
            }
        }
        return true;
    }
    public boolean solution(int[][] key, int[][] lock) {
        this.m = key.length;
        this.n = lock.length;
        lockMapInit(lock);
        for (int t= 0 ; t< 4 ; t++) {
            for (int i = 0 ; i< m + n; i++) {
                for (int j = 0 ; j< m+ n ; j++) {
                    int [][]copyArr = copy(lockMap);
                    if (!fillMap(copyArr, key, i, j)) continue;
                   // printArr(copyArr);
                    if (allMatch(copyArr)) return true;
                }
            } 
            rotateArr(key);
        }
       
        
        return false;
    }
    public void printArr(int[][]copyArr) {
        for (int i = 0 ;  i< copyArr.length ; i++) {
            for (int j =0; j< copyArr.length ; j++) {
                System.out.print(copyArr[i][j]);
            }
            System.out.println();
        }            
        System.out.println();

    }
}