/* 
 ID: rrqqgg2 
 LANG: JAVA 
 TASK: combo
 * 
 * Created on Jan 24, 2015
 * 
 * @author: Ranqingguo
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class combo {

	public static void main(String[] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster

		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] farmer_lock = new int[3], master_lock = new int[3];

		st = new StringTokenizer(f.readLine());
		farmer_lock[0] = Integer.parseInt(st.nextToken());
		farmer_lock[1] = Integer.parseInt(st.nextToken());
		farmer_lock[2] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(f.readLine());
		master_lock[0] = Integer.parseInt(st.nextToken());
		master_lock[1] = Integer.parseInt(st.nextToken());
		master_lock[2] = Integer.parseInt(st.nextToken());

		int n = 0; // posbible key number
		int same[] = new int[3];
		int a, b, tmp;
		if (N > 5) {
			n = 5;
			for (int i = 0; i < same.length; i++) {
				a = farmer_lock[i] > master_lock[i] ? farmer_lock[i] : master_lock[i];
				b = farmer_lock[i] < master_lock[i] ? farmer_lock[i] : master_lock[i];

				same[i] = b - a + 4 >= 0 ? b - a + 4 + 1 : 0;

				if (b < 3 || a > N - 2) {
					tmp = b + N;
					b = a;
					a = tmp;
					same[i] += b - a + 4 >= 0 ? b - a + 4 + 1 : 0;
				}

			}
		} else {
			n = N;
			same[0] = same[1] = same[2] = n;
		}

		out.println(n * n * n * 2 - same[0] * same[1] * same[2]);

		out.close();
		System.exit(0);
	}
}
