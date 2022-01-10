import java.io.*;
import java.util.*;

public class Universal_Hashing extends Open_Addressing {
	int a;
	int b;
	int p;

	protected Universal_Hashing(int w, int seed) {
		super(w, seed, -1);
		int temp = this.m + 1; // m is even, so temp is odd here
		while (!isPrime(temp)) {
			temp += 2;
		}
		this.p = temp;
		a = generateRandom(0, p, seed);
		b = generateRandom(-1, p, seed);
	}

	/**
	 * Checks if the input int is prime
	 */
	public static boolean isPrime(int n) {
		if (n <= 1) return false;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

	/**
	 * Implements universal hashing
	 */
	@Override
	public int probe(int key, int i) {
		int h = ((this.a * key + this.b) % this.p) % this.m;
		return ((h + i) % power2(this.r));
	}

	/**
	 * Inserts key k into hash table. Returns the number of collisions encountered,
	 * and resizes the hash table if needed
	 */
	@Override
	public int insertKeyResize(int key) {
		float f = (float) (this.size + 1) / this.m;
		if (f > 0.75) {
			this.m = this.m * 2;
			this.r = (int) (Math.log(m) / Math.log(2.0));
			this.w = 2 * (r - 1) + 1;
			this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
			int[] inter = new int[this.size];
			int g = 0;
			for (int i = 0; i < this.Table.length; i++) {
				if (this.Table[i] != -1) {
					inter[g] = this.Table[i];
					g++;
				}

			}
			this.Table = new int[m];
			this.size = 0;
			for (int i = 0; i < m; i++) {
				Table[i] = -1;
			}
			int temp = this.m + 1;
			while (!isPrime(temp)) {
				temp += 2;
			}
			this.p = temp;
			this.a = generateRandom(0, p, seed);
			this.b = generateRandom(-1, p, seed);
			this.insertKeyArray(inter);
			return insertKey(key);

		} else {
			return insertKey(key);
		}
	}

}
