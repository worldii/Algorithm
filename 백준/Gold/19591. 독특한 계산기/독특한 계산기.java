import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static Deque<Long> numList = new LinkedList<>();
	static Deque<Character> calnum = new LinkedList<>();

	public static void precalculate(String str) {

		int flag = 0;

		for (int i = 0; i < str.length(); i++) {
			if (flag == 0) {
				if (str.charAt(0) == '-') {
					flag = 1;
					continue;
				} else
					flag = 2;
			}
			if ('0' <= str.charAt(i) && str.charAt(i) <= '9') {
				long tempnum = 0;
				while (i < str.length() && '0' <= str.charAt(i) && str.charAt(i) <= '9') {
					tempnum = tempnum * 10 + str.charAt(i) - '0';
					i++;
				}
				if (flag == 1) {
					tempnum = -tempnum;
					flag = 2;
				}
				numList.add(tempnum);
				i--;
			} else {

				calnum.add(str.charAt(i));
			}
		}
	}

	public static long cal(long n1, char ch, long n2) {
		if (ch == '*')
			return n1 * n2;
		else if (ch == '/')
			return n1 / n2;
		else if (ch == '+')
			return n1 + n2;
		else if (ch == '-')
			return n1 - n2;
		return 0;
	}

	public static int getPriority(char ch) {
		if (ch == '*')
			return 2;
		else if (ch == '/')
			return 2;
		else if (ch == '+')
			return 1;
		else if (ch == '-')
			return 1;
		return 0;
	}

	public static void selectOne(long tempone) {
		calnum.removeFirst();
		numList.removeFirst();
		numList.removeFirst();
		numList.addFirst(tempone);

	}

	public static void selectTwo(long temptwo) {
		calnum.removeLast();
		numList.removeLast();
		numList.removeLast();
		numList.addLast(temptwo);
	}

	public static long findOne() {
		long firstNum = numList.getFirst();
		numList.removeFirst();
		long secondNum = numList.getFirst();
		char firstch = calnum.getFirst();
		numList.addFirst(firstNum);
		return cal(firstNum, firstch, secondNum);
	}

	public static long findTwo() {

		long firstNum = numList.getLast();
		numList.removeLast();
		long secondNum = numList.getLast();
		char secondch = calnum.getLast();
		numList.addLast(firstNum);
		return cal(secondNum, secondch, firstNum);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = scanner.nextLine();
		precalculate(str);
		if (calnum.size() == 0) {
			System.out.println(numList.peek());
		} else {
			while (numList.size() != 1) {

				char firstch = calnum.getFirst();
				char secondch = calnum.getLast();

				long tempone = findOne();
				long temptwo = findTwo();

				if (getPriority(secondch) > getPriority(firstch)) {
					selectTwo(temptwo);
				} else if (getPriority(secondch) < getPriority(firstch)) {
					selectOne(tempone);
				} else {
					if (tempone >= temptwo) {
						selectOne(tempone);
					} else {
						selectTwo(temptwo);
					}
				}

			}
			System.out.println(numList.getFirst());

		}
	}

}
