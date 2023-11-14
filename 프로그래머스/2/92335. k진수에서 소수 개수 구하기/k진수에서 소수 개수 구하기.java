import java.util.*;
class Solution {
    public boolean isPrime(long number) {
        if (number<=1) return false;
        for (long i = 2 ; i * i <= number ; i++) {
            if (number%i==0) return false;
        }
        return true;
    }
    public String convertKJinbub(int n, int k) {
        Stack<Integer> number= new Stack<>();
        while (n!=0) 
        {
            number.add(n%k);
            n = n/k;
        }
        StringBuilder sb= new StringBuilder();
        while(!number.isEmpty()){
            int t = number.pop();
            sb.append(t);
        }
        return sb.toString();
    }
    public int solution(int n, int k) {
        int answer = 0;
        String number2 = convertKJinbub(n,k);
        String[] numbers = number2.split("0");
        for (String number : numbers) {
            try {
                if (isPrime(Long.parseLong(number))) answer++;
            }
            catch (Exception e) {
                continue;
            }
        }
        return answer;
    }
}