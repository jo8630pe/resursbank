package wordladders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static SimpleGraph w;
	private static OneLetterDifference ond;
	
	public static void main(String args[]) throws FileNotFoundException {
		
//		ond = new OneLetterDifference();
//		w = new WordGraph("words-7.txt", ond);
//		System.out.println(((WordGraph) w).getWordMap().toString());
		
		processRequests(new OneLetterDifference(),"words-5757.txt", "words-5757-test.in");
		
	}
	
	
	
	
	@SuppressWarnings("resource")
	public static void processRequests(GraphStrategy strategy, String wordfile, String infile) throws FileNotFoundException {
		SimpleGraph w = new WordGraph(wordfile, strategy);
		
		Scanner scan = null;
		scan = new Scanner(new File(infile));
		
		while (scan.hasNext()) {
			String word1 = scan.next();
			String word2 = scan.next();
			
			System.out.println(ShortestPath.shortestPath(w, word1, word2));
		}
		
	}


}
