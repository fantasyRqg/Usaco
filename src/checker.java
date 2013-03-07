/*
 ID: rrqqgg2
 LANG: JAVA
 TASK: sprime
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class checker {

	static boolean[] row = new boolean[13];
	static boolean[] col = new boolean[13];
	static int n = 0;

	/**
	 * check weather pos(x,y) could put a checker
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	static boolean canPut(int x, int y) {
		if (row[x] || col[y])
			return false;
		if (x >= y) {
			for (int i = x - y; i <= n; i++) {
				if (row[i] && col[i - x + y])
					return false;
			}
		} else {
			for (int i = 0; i <= n - y + x; i++) {
				if (row[i] && col[y - x + i])
					return false;
			}
		}

		if (x + y <= n) {
			for (int i = 0; i <= x + y; i++) {
				if (row[i] && col[x + y - i])
					return false;
			}
		} else {
			for (int i = x + y - n; i <= n; i++) {
				if (row[i] && col[x + y - i])
					return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster
		out.close(); // close the output file
		System.exit(0); // don't omit this!

	}
}
