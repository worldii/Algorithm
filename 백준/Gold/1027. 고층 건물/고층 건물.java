import java.util.Scanner;

public class Main {
    public static int [] arr;

    public static double slope( int x, int y) {
        return (double) (arr[x] - arr[y] )/ (double) (x-y);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
       arr = new int [n];
        for (int i = 0  ; i< n ; i++) {
            arr[i] = sc.nextInt();
        }
        int [] checked = new int [n];
        for (int i = 0 ; i< n ; i++) {
            double height  = Integer.MIN_VALUE;
            for (int j = i+1 ; j<n ; j++) {
                if (height < slope(i,j)) {
                    height = slope(i,j);
                    checked[i]++;
                }
            }
            height  = Integer.MAX_VALUE;
            for (int j = i-1 ;j>=0 ; j--) {
                if (height >slope(i,j)) {
                    height = slope(i,j);
                    checked[i]++;
                }
            }
        }
        int maxNum = -1;
        for (int i = 0 ; i< n ; i++) {
            maxNum = Math.max(maxNum, checked[i]);
        }
        System.out.println(maxNum);
    }
}