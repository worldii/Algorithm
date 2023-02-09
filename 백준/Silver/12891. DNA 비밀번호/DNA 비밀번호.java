import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static String str;
	static int s, p;
	static int[] num = new int[26];

	static int sum = 0;
	static char [] temp2 = {'A','C','G','T'};
	static int[] cntArr = new int[26];

	static boolean check(String str,int start, int end) {
		for (int i = 0 ; i< 4 ; i++) {
			if(num[temp2[i]-'A'] > cntArr[temp2[i] -'A']) return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		s =Integer.parseInt(st.nextToken());
		p =Integer.parseInt(st.nextToken());
		str = br.readLine();
		num = new int[26];
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			num[temp2[i] -'A'] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0 ; i<p ; i++) {
			cntArr[str.charAt(i)-'A']++;
		}
		if (check( str, 0,p-1)) 
		{
			sum++;
		}
		for (int i = 1; i < str.length(); i++) {
			if (i + p -1>= str.length())
				break;
			cntArr[str.charAt(i+p-1)-'A']++;
			cntArr[str.charAt(i-1)-'A']--;
			if (check( str, i, i+p-1)) 
			{
				sum++;
			}
		}
		System.out.print(sum);
	}

}
