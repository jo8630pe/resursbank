package map;


public class Main {

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> m;

		m = new SimpleHashMap<Integer, Integer>(10);
		
		m.put(10, 100);
		m.put(10, 200);
		m.put(100, 200);
		m.put(1000, 200);
		m.put(15, 200);
		m.put(1, 200);
		m.put(103, 300);
		m.put(24, 200);
		m.put(18, 10);
		m.put(112, 200);
		m.put(3, 100);
		m.put(9, 200);
		m.put(2, 200);
		m.put(7, 200);
		System.out.println(m.show());
		System.out.println("Storlek: " + m.size());
		System.out.println();
		System.out.println("Värde för nyckel 3: "+ m.get(3));
		System.out.println();
		m.remove(1000);
		m.remove(1000);
		m.remove(3);
		m.remove(103);
		m.remove(18);
		System.out.println(m.show());
		System.out.println("Storlek: " + m.size());
		


	}

}
