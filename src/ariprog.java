/*
ID: rrqqgg2
LANG: JAVA
TASK: ariprog
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class ariprog {
	/**
	 * 
	 * @param n
	 *            N (3 <= N <= 25), the length of progressions for which to search
	 * @param m
	 *            M (1 <= M <= 250), an upper bound to limit the search to the bisquares with 0 <= p,q <= M
	 * @return a is a non-negative integer and b is a positive integer
	 */
	static ArrayDeque<int[]> find(final int n, final int m) {
		ArrayDeque<int[]> returnArrayDeque = new ArrayDeque<int[]>();

		int a = 0, b;
		b = 1;
		int max = m * m * 2;
		while ((n - 1) * b <= max) {
			a = 0;
			while (a + (n - 1) * b <= max) {
				if (checkExist(a, b, n)) {
					returnArrayDeque.add(new int[] { a, b });
				}
				a++;
			}
			b++;
		}
		return returnArrayDeque;
	}

	static boolean checkExist(final int a, final int b, final int n) {

		for (int i = 0; i < n; i++) {
			if (!status[a + i * b]) {
				// System.out.println(a + i * b + "," + i);
				return false;
			}
		}
		return true;
	}

	private static boolean[] status = new boolean[250 * 250 * 2 + 1];

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster

		int n, m;
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		m = Integer.parseInt(st.nextToken());
		// init status
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= m; j++) {
				status[i * i + j * j] = true;
			}
		}
		ArrayDeque<int[]> r = find(n, m);
		if (r.size() == 0) {
			out.println("NONE");
			out.close();
			System.exit(0);
		}

		for (int[] is : r) {
			out.println(is[0] + " " + is[1]);
		}
		out.close();
		System.exit(0);
	}
}
