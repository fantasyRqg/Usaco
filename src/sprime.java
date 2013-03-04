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
import java.util.StringTokenizer;

public class sprime {

	private static int[] i_sprime = new int[] { 2, 3, 3, 3, 3, 3, 3, 3 };
	private static int slen = 0;

	/**
	 * 
	 * @param slen
	 * @return
	 */
	private static int firstSPrime(int len) {
		sprime.slen = len - 1;
		return arr2int(len);
	}

	/**
	 * 
	 * @return
	 */
	private static int nextSPrime() {
		int tmp = 0;
		int carry = 1;
		int i = slen - 1;

		outlab: while (carry > 0) {
			i = slen - carry + 1;
			do {
				if (i_sprime[i] == 9) {
					if (i == 0) {
						return -1;
					}
					carry++;
					i_sprime[i] = 1;
					continue outlab;
				} else {
					if (i_sprime[i] == 2) {
						i_sprime[i]++;
					} else {
						i_sprime[i] += 2;
					}
				}
				tmp = arr2int(i + 1);
			} while (!isPrime(tmp));
			carry--;
		}
		return tmp;
	}

	private static int arr2int(int l) {
		int tmp = 0;
		for (int i = 0; i < l; i++) {
			tmp = tmp * 10 + i_sprime[i];
		}
		return tmp;
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	static boolean isPrime(int a) {
		if (a < 2)
			return false;
		int n = (int) Math.sqrt(a);
		for (int i = 3; i <= n; i += 2) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster
		int len = Integer.parseInt((new StringTokenizer(f.readLine())).nextToken());
		int s;
		s = firstSPrime(len);
		while (s > 0) {
			out.println(s);
			s = nextSPrime();
		}
		out.close(); // close the output file
		System.exit(0); // don't omit this!

	}
}
