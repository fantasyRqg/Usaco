/*
ID: rrqqgg2
LANG: JAVA
TASK: numtri
 */

import java.io.*;
import java.util.StringTokenizer;

public class numtri {
	/**
	 * f[i,j] =a[i,j] + max(f[i-1,j],f[i-1,j-1])
	 * 
	 * @param a
	 * @return
	 */
	static int calc(int[][] a) {
		int[][] c = new int[a.length][];
		int r = 0;

		for (int i = 0; i < a.length; i++) {
			c[i] = new int[i + 1];
		}
		c[0][0] = a[0][0];

		for (int i = 1; i < c.length; i++) {
			c[i][0] = c[i - 1][0] + a[i][0];
			c[i][i] = c[i - 1][i - 1] + a[i][i];
		}

		for (int i = 2; i < c.length; i++) {
			for (int j = 1; j < c[i].length - 1; j++) {
				c[i][j] = (c[i - 1][j] > c[i - 1][j - 1] ? c[i - 1][j] : c[i - 1][j - 1]) + a[i][j];
			}
		}

		int[] el = c[c.length - 1];
		for (int i = 0; i < el.length; i++) {
			if (r < el[i])
				r = el[i];
		}

		return r;
	}

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster
		int[][] a;
		int n;

		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		a = new int[n][];
		for (int i = 0; i < n; i++) {
			a[i] = new int[i + 1];
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j <= i; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		out.println(calc(a));

		out.close();
		System.exit(0);

	}
}
