import java.io.*;
import java.util.*;


public class main {     


	public static void main(String[] args) {
		//TODO:build the hash table and insert keys using the insertKeyArray function.
		Chaining testChainingInsert = new Chaining(10,0,-1);
		Open_Addressing testProbe = new Open_Addressing(5,1,6);
		System.out.println(Arrays.toString(testProbe.Table));
		System.out.println(testProbe.insertKey(38));
		System.out.println(testProbe.insertKey(70));
		System.out.println(testProbe.insertKey(102));
		System.out.println(testProbe.insertKey(134));
		System.out.println(testProbe.insertKey(166));
		System.out.println(testProbe.insertKey(198));
		System.out.println(testProbe.insertKey(260));
		System.out.println(Arrays.toString(testProbe.Table));
		System.out.println(Arrays.toString(testProbe.collidingKeys(6,7,5)));
		/*System.out.println(testProbe.insertKey(7));
		System.out.println(testProbe.insertKey(9));
		System.out.println(testProbe.insertKey(3));*/
		System.out.println(Arrays.toString(testProbe.Table));
		/*Universal_Hashing testuniversal = new Universal_Hashing(3,1);
		System.out.println(Arrays.toString(testuniversal.Table));
		System.out.println(testuniversal.insertKeyResize(7));
		System.out.println(testuniversal.insertKeyResize(3));
		System.out.println(testuniversal.insertKeyResize(30));
		System.out.println(Arrays.toString(testuniversal.Table));
		System.out.println(testuniversal.insertKeyResize(50));
		System.out.println(Arrays.toString(testuniversal.Table));*/
		/*System.out.println(testProbe.insertKeyResize(5));
		System.out.println(Arrays.toString(testProbe.Table));
		System.out.println(testProbe.insertKeyResize(1));
		System.out.println(Arrays.toString(testProbe.Table));
		System.out.println(testProbe.insertKeyResize(10));
		System.out.println(Arrays.toString(testProbe.Table));
		System.out.println(testProbe.insertKeyResize(12));
		System.out.println(Arrays.toString(testProbe.Table));
		System.out.println(testProbe.insertKeyResize(2));
		System.out.println(Arrays.toString(testProbe.Table));
		System.out.println(testProbe.insertKeyResize(4));
		System.out.println(Arrays.toString(testProbe.Table));
		System.out.println(testProbe.insertKeyResize(65));
		System.out.println(Arrays.toString(testProbe.Table));
		//System.out.println(testProbe.insertKey(5));
		//System.out.println(testProbe.removeKey(5));
		//System.out.println(testProbe.removeKey(4));
		//System.out.println(Arrays.toString(testProbe.Table));*/

	}
}