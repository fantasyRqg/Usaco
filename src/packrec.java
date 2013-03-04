/*
ID: rrqqgg2
LANG: JAVA
TASK: packrec
 */

import java.io.*;
import java.util.*;

public class packrec {

	final static int COUNT = 4;

	static boolean next_permutation(int[] p) {
		for (int a = p.length - 2; a >= 0; --a)
			if (p[a] < p[a + 1])
				for (int b = p.length - 1;; --b)
					if (p[b] > p[a]) {
						int t = p[a];
						p[a] = p[b];
						p[b] = t;
						for (++a, b = p.length - 1; a < b; ++a, --b) {
							t = p[a];
							p[a] = p[b];
							p[b] = t;
						}
						return true;
					}
		return false;
	}

	static void swap(int[][] a, int n) {
		int temp = a[n][0];
		a[n][0] = a[n][1];
		a[n][1] = temp;
	}

	static boolean nothasN(int[][] a, int n, int arrc) {
		for (int i = 0; i <= arrc; i++) {
			if (a[i][0] == n)
				return false;
		}
		return true;
	}

	// static int[] case6(int[][] a) {
	// }

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("packrec.in"));

		int[][] rects = new int[COUNT][2];
		int[][] _rects = new int[COUNT][2];
		RectCase[] cases = new RectCase[] { new case1(), new case2(), new case3(), new case4(), new case5() };
		for (int i = 0; i < COUNT; i++) {
			// Use StringTokenizer vs. readLine/split -- lots faster
			StringTokenizer st = new StringTokenizer(f.readLine());
			// Get line, break into tokens
			rects[i][0] = Integer.parseInt(st.nextToken()); // first integer
			rects[i][1] = Integer.parseInt(st.nextToken()); // second integer
		}

		int[] ars = new int[] { 1, 2, 3, 4 };

		int[][] result = new int[COUNT][2];
		int resultCount = 0;
		int minaera = Integer.MAX_VALUE;
		int[] res;
		do {
			for (int i = 0; i < rects.length; i++) {
				_rects[i] = rects[ars[i] - 1];
			}
			for (int i = 0; i < 2; i++) {
				swap(_rects, 0);
				for (int j = 0; j < 2; j++) {
					swap(_rects, 1);
					for (int j2 = 0; j2 < 2; j2++) {
						swap(_rects, 2);
						for (int k = 0; k < 2; k++) {
							swap(_rects, 3);

							for (int k2 = 0; k2 < cases.length; k2++) {
								res = cases[k2].run(_rects);
								if (res[0] == 6 && res[1] == 6) {
									System.out.println(res[0] + " " + res[1] + " " + k2);
									System.out.println(ars[0] + " " + ars[1] + " " + ars[2] + " " + ars[3]);
									System.out.println(i + " " + j + " " + j2 + " " + k);
								}
								if (minaera > res[0] * res[1]) {
									resultCount = 0;
									minaera = res[0] * res[1];
									result[resultCount][0] = res[0] < res[1] ? res[0] : res[1];
									result[resultCount][1] = res[0] < res[1] ? res[1] : res[0];
								} else if (minaera == res[0] * res[1]) {
									if (nothasN(result, res[0] < res[1] ? res[0] : res[1], resultCount)) {
										resultCount++;
										result[resultCount][0] = res[0] < res[1] ? res[0] : res[1];
										result[resultCount][1] = res[0] < res[1] ? res[1] : res[0];
									}
								}
							}
						}
					}
				}
			}
		} while (next_permutation(ars));

		Arrays.sort(result, 0, resultCount + 1, new mycomparator());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
		out.println(minaera);
		for (int i = 0; i <= resultCount; i++) {
			out.println(result[i][0] + " " + result[i][1]);
		}
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}

class mycomparator implements Comparator<int[]> {

	@Override
	public int compare(int[] o1, int[] o2) {
		if (o1[0] > o2[0]) {
			return 1;
		} else if (o1[0] < o2[0]) {
			return -1;
		} else {
			return 0;
		}
	}

}

abstract class RectCase {
	abstract public int[] run(int[][] a);

	static protected int[] case1(int[][] a) {
		int[] result = new int[2];

		result[0] = a[0][0] + a[1][0] + a[2][0] + a[3][0];

		result[1] = a[0][1] > a[1][1] ? a[0][1] : a[1][1];

		result[1] = result[1] > a[2][1] ? result[1] : a[2][1];

		result[1] = result[1] > a[3][1] ? result[1] : a[3][1];

		return result;
	}

	static protected int[] case2(int[][] a) {
		int[] result = new int[2];

		result[0] = a[0][0] + a[1][0] + a[2][0];
		result[0] = result[0] > a[3][0] ? result[0] : a[3][0];

		result[1] = a[0][1] > a[1][1] ? a[0][1] : a[1][1];
		result[1] = result[1] > a[2][1] ? result[1] : a[2][1];
		result[1] += a[3][1];

		return result;
	}

	static protected int[] case3(int[][] a) {
		int[] result = new int[2];

		result[0] = a[0][0] + a[1][0];
		result[0] = result[0] > a[2][0] ? result[0] : a[2][0];
		result[0] += a[3][0];

		result[1] = a[0][1] > a[1][1] ? a[0][1] : a[1][1];
		result[1] += a[2][1];
		result[1] = result[1] > a[3][1] ? result[1] : a[3][1];

		return result;

	}

	static protected int[] case4(int[][] a) {
		int[] result = new int[2];

		result[0] = a[1][0] > a[2][0] ? a[1][0] : a[2][0];
		result[0] += a[0][0] + a[3][0];

		result[1] = a[1][1] + a[2][1];
		result[1] = result[1] > a[3][1] ? result[1] : a[3][1];
		result[1] = result[1] > a[0][1] ? result[1] : a[0][1];
		// System.out.println(result[0] + " " + result[1]);
		return result;
	}

	static protected int[] case5(int[][] a) {
		int[] result = new int[2];

		int tmp1, tmp2;
		if (a[3][1] <= a[1][1]) {
			tmp1 = a[0][0] + a[2][0];
			tmp2 = a[1][0] + a[3][0];

		} else if (a[3][1] > a[1][1] && a[1][1] + a[0][1] >= a[3][1]) {
			tmp1 = a[1][0] + a[3][0];
			tmp2 = a[2][0] > a[3][0] ? a[2][0] : a[3][0];
			tmp2 += a[0][0];
		} else {
			tmp1 = a[0][0] > a[1][0] ? a[0][0] : a[1][0];
			tmp1 += a[3][0];
			tmp2 = a[2][0];

		}
		result[0] = tmp1 > tmp2 ? tmp1 : tmp2;

		tmp1 = a[0][1] + a[1][1];
		tmp2 = a[2][1] + a[3][1];
		result[1] = tmp1 > tmp2 ? tmp1 : tmp2;
		tmp1 = a[1][1] + a[2][1];
		result[1] = result[1] > tmp1 ? result[1] : tmp1;

		return result;
	}
}

class case1 extends RectCase {

	@Override
	public int[] run(int[][] a) {
		return super.case1(a);
	}
}

class case2 extends RectCase {

	@Override
	public int[] run(int[][] a) {
		return super.case2(a);
	}
}

class case3 extends RectCase {

	@Override
	public int[] run(int[][] a) {
		return super.case3(a);
	}
}

class case4 extends RectCase {

	@Override
	public int[] run(int[][] a) {
		return super.case4(a);
	}
}

class case5 extends RectCase {

	@Override
	public int[] run(int[][] a) {
		return super.case5(a);
	}
}