package maxflow;

import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException{
		Maxflow test = new Maxflow("small.txt");
		System.out.println(test.getMaxFlow());
	}
}