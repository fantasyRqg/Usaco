/*
 ID: rrqqgg2
 LANG: JAVA
 TASK: pprime
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pprime {
	/**
	 * 
	 * @param a
	 *            Palindrome,bigger than 4
	 * @return bigger than a and is a Palindromes
	 */
	static int nextPalindrome(int a) {
		int tmp = a;
		int i;
		for (i = 0; tmp > 0; i++) {
			status[i] = tmp % 10;
			tmp /= 10;
		}
		i--;
		int middle = i / 2;
		int carry = 1;
		if (i % 2 == 0) {
			// odd number
			if (status[middle] < 9) {
				if (middle == 0) {
					status[middle] += 2;
				} else {
					status[middle]++;
				}
				carry = 0;
			} else {
				status[middle] = 0;
				carry = 1;
				if (middle == 0) {
					status[0] = 1;
					status[i + 1] = 1;
					i++;
					carry = 0;
					middle++;
				}
				middle--;
			}
		}
		while (carry == 1) {
			if (status[middle] == 9) {
				status[middle] = 0;
				status[i - middle] = 0;
				if (middle == 0) {
					status[0] = 1;
					status[i] = 0;
					status[i + 1] = 1;
					carry = 0;
					i++;
					middle++;
				}
			} else {
				if (middle == 0) {
					status[middle] += 2;
					status[i - middle] += 2;
				} else {
					status[middle]++;
					status[i - middle]++;
				}
				carry = 0;
			}
			middle--;
		}

		return arr2int(i);
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	static int firstPalindrome(int a) {
		int tmp = a;
		int i;
		for (i = 0; tmp > 0; i++) {
			status[i] = tmp % 10;
			tmp /= 10;
		}
		i--;
		int middle = i / 2;

		if (i % 2 == 0) {
			// i is odd number
			middle--;
		}

		while (middle >= 0) {
			if (status[middle] != status[i - middle]) {
				if (status[middle] < status[i - middle]) {
					while (middle >= 0) {
						status[middle] = status[i - middle];
						middle--;
					}
					return arr2int(i);
				} else {
					while (middle >= 0) {
						status[middle] = status[i - middle];
						middle--;
					}
					return nextPalindrome(arr2int(i));
				}
			}
			middle--;
		}

		return a;
	}

	/**
	 * convert array to int
	 * 
	 * @param n
	 * @return
	 */
	static int arr2int(int n) {
		int tmp = 0;
		for (int i = n; i >= 0; i--) {
			tmp = tmp * 10 + status[i];
		}
		return tmp;
	}

	static boolean isPrime(int a) {
		int n = (int) Math.sqrt(a);
		for (int i = 3; i <= n; i += 2) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}

	static int[] status = new int[9];

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster

		int min, max;
		StringTokenizer st = new StringTokenizer(f.readLine());
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		int tmp;
		tmp = firstPalindrome(min);
		while (tmp <= max) {
			if (isPrime(tmp)) {
				out.println(tmp);				
			}
			tmp = nextPalindrome(tmp);
		}

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}
