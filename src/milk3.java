/*
ID: rrqqgg2
LANG: JAVA
TASK: milk3
 */
import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class milk3 {
	/**
	 * 
	 * @param _a
	 *            A
	 * @param _b
	 *            B
	 * @param _c
	 *            C
	 * @return possible value
	 */
	static boolean[] find(int _a, int _b, int _c) {
		int Pa = (_a < _c ? _a : _c) + 1;
		int Pb = (_b < _c ? _b : _c) + 1;
		boolean[] r = new boolean[_c + 1];
		// int count = 0;
		int A, B, C;
		boolean[] status = new boolean[Pa * Pb];
		Stack<int[]> stack = new Stack<int[]>();
		stack.push(new int[] { 0, 0, _c });

		while (!stack.isEmpty()) {
			int[] tmp = stack.pop();
			A = tmp[0];
			B = tmp[1];
			C = tmp[2];
			if (A == 0) {
				r[C] = true;
			}
			// first
			if (A != 0) {
				if (B != _b) {
					if (A > _b - B) {
						if (!status[(A - _b + B) * Pb + _b]) {
							stack.push(new int[] { A - _b + B, _b, C });
							status[(A - _b + B) * Pb + _b] = true;
						}
					}
					else if (!status[A + B]) {
						stack.push(new int[] { 0, A + B, C });
						status[A + B] = true;
					}
				}

				if (C != _c) {
					if (A > _c - C) {
						if (!status[(A - _c + C) * Pb + B]) {
							stack.push(new int[] { A - _c + C, B, _c });
							status[(A - _c + C) * Pb + B] = true;
						}
					} else if (!status[B]) {
						stack.push(new int[] { 0, B, A + C });
						status[B] = true;
					}
				}
			}
			// second
			if (B != 0) {
				if (A != _a) {
					if (B > _a - A) {
						if (!status[_a * Pb + B - _a + A]) {
							stack.push(new int[] { _a, B - _a + A, C });
							status[_a * Pb + B - _a + A] = true;
						}
					} else if (!status[(A + B) * Pb]) {
						stack.push(new int[] { A + B, 0, C });
						status[(A + B) * Pb] = true;
					}
				}
				if (C != _c) {
					if (B > _c - C) {
						if (!status[A * Pb + B - _c + C]) {
							stack.push(new int[] { A, B - _c + C, _c });
							status[A * Pb + B - _c + C] = true;
						}
					} else if (!status[A * Pb]) {
						stack.push(new int[] { A, 0, C + B });
						status[A * Pb] = true;
					}

				}
			}
			// third
			if (C != 0) {
				if (A != _a) {
					// System.out.println(A + "," + B + "," + C + "," + ((A + C) * Pb + B));
					if (C > _a - A) {
						if (!status[_a * Pb + B]) {
							stack.push(new int[] { _a, B, C - _a + A });
							status[_a * Pb + B] = true;
						}
					} else if (!status[(A + C) * Pb + B]) {
						stack.push(new int[] { A + C, B, 0 });
						status[(A + C) * Pb + B] = true;
					}
				}

				if (B != _b) {
					if (C > _b - B) {
						if (!status[A * Pb + _b]) {
							stack.push(new int[] { A, _b, C - _b + B });
							status[A * Pb + _b] = true;
						}
					}
					else if (!status[A * Pb + B + C]) {
						stack.push(new int[] { A, B + C, 0 });
						status[A * Pb + B + C] = true;
					}
				}
			}
		}

		return r;
	}

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster

		StringTokenizer st = new StringTokenizer(f.readLine());
		int a, b, c;
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		boolean[] r = find(a, b, c);

		String str = "";

		for (int i = 0; i < r.length; i++) {
			if (r[i])
				str += i + " ";
		}
		out.println(str.substring(0, str.length() - 1));
		out.close();
		System.exit(0);
	}
}
