import java.io.*;
import java.util.*;

public class Open_Addressing {
	public static final double MAX_LOAD_FACTOR = 0.75;
	
	public int m; // number of slots
	public int A; // the default random number
	int w;
	int r;
	int seed;
	public int[] Table;
	int size; // number of elements stored in the hash table

	protected Open_Addressing(int w, int seed, int A) {
		this.seed = seed;
		this.w = w;
		this.r = (int) (w - 1) / 2 + 1;
		this.m = power2(r);
		if (A == -1) {
			this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
		} else {
			this.A = A;
		}
		this.Table = new int[m];
		for (int i = 0; i < m; i++) {
			Table[i] = -1;
		}
		this.size = 0;
	}

	/**
	 * Calculate 2^w
	 */
	public static int power2(int w) {
		return (int) Math.pow(2, w);
	}

	public static int generateRandom(int min, int max, int seed) {
		Random generator = new Random();
		if (seed >= 0) {
			generator.setSeed(seed);
		}
		int i = generator.nextInt(max - min - 1);
		return i + min + 1;
	}

	/**
	 * Implements the hash function g(k)
	 */
	public int probe(int key, int i) {
		Chaining c = new Chaining(this.w,this.seed,this.A);
		int g = c.chain(key);
		return ((g + i) % power2(this.r));
	}

	/**
	 * Inserts key k into hash table. Returns the number of collisions encountered
	 */
	public int insertKey(int key) {
		int keynumber = probe(key,0);
		int collisions = 0;
		while (this.Table[keynumber] != -1){
			if (collisions > this.Table.length){
				return this.Table.length;
			}
			else{
				collisions++;
				keynumber = probe(key, collisions);
			}
		}
		this.Table[keynumber] = key;
		this.size++;
		return collisions;
	}


	/**
	 * Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions
	 */
	public int insertKeyArray(int[] keyArray) {
		int collision = 0;
		for (int key : keyArray) {
			collision += insertKey(key);
		}
		return collision;
	}

	/*
	 * @param the key k to be searched
	 * @return an int array containing 2 elements:
	 * first element = index of k in this.Table if the key is present, = -1 if not present
	 * second element = number of collisions occured during the search
	 */
	public int[] searchKey(int k) {
		int keynumberphish = probe(k,0);
		int collisions = 0;
		while (this.Table[keynumberphish] != k){
			if (collisions >= this.Table.length){
				return new int[] {-1, collisions};
			}
			else{
				collisions++;
				keynumberphish = probe(k,collisions);
			}
		}
		int[] output = {keynumberphish, collisions};
		return output;
	}
	
	/**
	 * Removes key k from hash table. Returns the number of collisions encountered
	 */
	public int removeKey(int k){
		int [] check = searchKey(k);
		if (check[0] != -1){
			this.Table[check[0]] = -1;
			return check[1];
		}else{
			return check[1];
		}
	}

	/**
	 * Inserts key k into hash table. Returns the number of collisions encountered,
	 * and resizes the hash table if needed
	 */
	public int insertKeyResize(int key) {
		float f = (float)(this.size+1)/this.m;
		if (f > 0.75){
			this.m = this.m * 2;
			this.r = (int) (Math.log(m)/Math.log(2.0));
			this.w = 2 * (r - 1) + 1;
			this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
			int [] inter = new int[this.size];
			int g = 0;
			for (int i = 0; i < this.Table.length; i++){
				if (this.Table[i] != -1){
					inter[g] = this.Table[i];
					g++;
				}

			}
			this.Table = new int[m];
			this.size = 0;
			for (int i = 0; i < m; i++) {
				Table[i] = -1;
			}
			this.insertKeyArray(inter);
			return insertKey(key);

		}else {
			return insertKey(key);
		}
	}

	/**
	 * Sequentially inserts a list of keys into the HashTable, and resize the hash table
	 * if needed. Outputs total number of collisions
	 */
	public int insertKeyArrayResize(int[] keyArray) {
		int collision = 0;
		for (int key : keyArray) {
			collision += insertKeyResize(key);
		}
		return collision;
	}

	/*
	 * @param the key k to be searched (and relocated if needed)
	 * @return an int array containing 2 elements:
	 * first element = index of k in this.Table (after the relocation) if the key is present, 
	 * 				 = -1 if not present
	 * second element = number of collisions occured during the search
	 */
	public int[] searchKeyOptimized(int k) {
		int[] check = searchKey(k);
		if (check[0] == -1){
			return check;
		}
		int keynumberphish = probe(k,0);
		int collisions = 0;
		while (this.Table[keynumberphish] != -1){
			if (collisions >= check[1]){
				return check;
			}
			else{
				collisions++;
				keynumberphish = probe(k,collisions);
			}
		}
		this.Table[keynumberphish] = k;
		this.Table[check[0]] = -1;
		return check;
	}

	/**
	 * @return an int array of n keys that would collide with key k
	 */
	public int[] collidingKeys(int k, int n, int w) {
		int modw = power2(w);
		int[] output = new int[n];
		int start = k;
		for (int i = 0;i < n; i++){
			start = start+modw;
			output[i] = start;
		}
		return output;
	}
}
