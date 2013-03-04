/*
ID: rrqqgg2
LANG: JAVA
TASK: packrec
 */

import java.io.*;
import java.util.StringTokenizer;

public class crypt1 {

	static boolean InArray(char c, char num[]) {
		for (char ch : num) {
			if (c == ch)
				return true;
		}
		return false;
	}

	static boolean meet(char a[], char b[], char num[]) { // a *** b ** num
															// vaild numbers
		int a_int, b_int;
		a_int = Integer.parseInt(new String(a));
		b_int = Integer.parseInt(new String(b));
		// System.out.println(num);
		// System.out.println(new String(a) + "  " + new String(b));
		if (a_int * b_int > 9999)
			return false;
		int tmp = (b_int % 10) * a_int; // test line 1
		if (tmp > 999)
			return false;
		String c = Integer.toString(tmp);
		for (char ch : c.toCharArray()) {
			if (!InArray(ch, num))
				return false;
		}
		tmp = ((b_int / 10) % 10) * a_int;// test line 2
		if (tmp > 999)
			return false;
		c = Integer.toString(tmp);
		for (char ch : c.toCharArray()) {
			if (!InArray(ch, num))
				return false;
		}
		c = Integer.toString(b_int * a_int);
		for (char ch : c.toCharArray()) {
			if (!InArray(ch, num))
				return false;
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("crypt1.in"));
		// BufferedWriter fout = new BufferedWriter(new
		// FileWriter("crypt1.out"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(
				"crypt1.out")));
		StringTokenizer st = new StringTokenizer(fin.readLine());
		int n, i;

		String str;
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(fin.readLine());

		i = 0;
		char num[] = new char[n];
		while (i < n) {
			str = st.nextToken();
			num[i] = str.charAt(0);
			i++;
		}
		String a, b;
		int count = 0;
		for (char ch1 : num)
			for (char ch2 : num)
				for (char ch3 : num)
					for (char ch4 : num)
						for (char ch5 : num) {
							a = "" + ch1 + ch2 + ch3;
							b = "" + ch4 + ch5;
							if (meet(a.toCharArray(), b.toCharArray(), num))
								count++;
						}
		;
		fout.println(count);
		fout.flush();
	}
}
