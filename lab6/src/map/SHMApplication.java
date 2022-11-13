package map;

import java.util.Random;

public class SHMApplication {

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<Integer, Integer>(10);
		Random rand = new Random();
		map.put(2, 2);
		map.put(12, 12);
		for (int i = 0; i < 100; i++) {
			int k = rand.nextInt(100);
			map.put(k, k);
		}
		System.out.println(map.show());
	}

}
