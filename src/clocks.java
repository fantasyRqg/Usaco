/*
ID: rrqqgg2
LANG: JAVA
TASK: clocks
 */

import java.io.*;
import java.util.*;

public class clocks {
	final static int[][] steps = new int[][] {
			{ 3, 3, 0, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 0, 0, 0, 0, 0, 0 },
			{ 0, 3, 3, 0, 3, 3, 0, 0, 0 },
			{ 3, 0, 0, 3, 0, 0, 3, 0, 0 },
			{ 0, 3, 0, 3, 3, 3, 0, 3, 0 },
			{ 0, 0, 3, 0, 0, 3, 0, 0, 3 },
			{ 0, 0, 0, 3, 3, 0, 3, 3, 0 },
			{ 0, 0, 0, 0, 0, 0, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 0, 3, 3 } };

	static boolean isAll12(int[] clock) {
		for (int i = 0; i < clock.length; i++) {
			if (clock[i] != 12) {
				return false;
			}
		}
		return true;
	}

	static boolean moves(int[] _clock, int[] moveSquence) {
		int[] clock = _clock.clone();
		for (int i = 0; i < moveSquence.length; i++) {
			for (int j = 0; j < moveSquence[i]; j++) {
				for (int k = 0; k < clock.length; k++) {
					clock[k] += steps[i][k];
					if (clock[k] == 15) {
						clock[k] = 3;
					}
				}
			}
		}

		if (isAll12(clock)) {
			return true;
		}
		return false;
	}

	static boolean move(int[] a) {
		int t = 1;
		for (int i = a.length - 1; i >= 0; i--) {
			if (a[i] != 0) {
				if (i == a.length - 1) {
					t += a[i];
				} else {
					if (t <= (a.length - 1 - i) * 3) {
						a[i]--;
						Reset(a, i + 1, t);
						return true;
					}
					else {
						t += a[i];
					}
				}
			}
		}
		return false;
	}

	static void Reset(int[] a, int n, int p) {
		int t = p;

		for (int i = n; i < a.length; i++) {
			a[i] = 0;
		}

		for (int i = n; i < a.length; i++) {
			if (t < 3) {
				a[i] = t;
				break;
			} else {
				a[i] = 3;
				t -= 3;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster

		int[] carr = new int[9];

		// get clock data
		for (int i = 0; i < 9; i += 3) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			carr[i] = Byte.parseByte(st.nextToken());
			carr[i + 1] = Byte.parseByte(st.nextToken());
			carr[i + 2] = Byte.parseByte(st.nextToken());
		}

		// do search
		int[] a = new int[9];
		loop1: for (int i = 1; i < 28; i++) {
			Reset(a, 0, i);
			do {
				if (moves(carr, a)) {
					String temp = "";
					for (int ii = 0; ii < a.length; ii++) {
						for (int j = 0; j < a[ii]; j++) {
							temp += (ii + 1) + " ";
						}
					}
					temp = temp.substring(0, temp.length() - 1);
					out.println(temp);
					break loop1;
				}
			} while (move(a));
		}

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}
