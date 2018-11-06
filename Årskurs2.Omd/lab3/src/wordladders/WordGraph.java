package wordladders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordGraph implements SimpleGraph{
	private Map<String, Set<String>> m;
	
	
/*	Klassen skall ha en konstruktor som bygger upp grafen utgående från en fil som innehåller orden. Filnamnet
	skall vara parameter till konstruktorn.
	I konstruktorn läses orden från filen och grafen (mappen) byggs. Börja med att läsa in alla
	orden och lägg in dem som nycklar i mappen med en tom mängd som associerat värde. I
	nästa uppgift kommer konstruktorn att kompletteras så att bågarna läggs in.
	
	Implementera också metoden adjacentTo i klassen.*/
	
	@SuppressWarnings("resource")
	public WordGraph(String fileName, GraphStrategy strategy) throws FileNotFoundException {
		
		m = new HashMap<String, Set<String>>();
		Scanner scan = null;
		scan = new Scanner(new File(fileName));
		
		while (scan.hasNext()) {
			String w = scan.nextLine();
			m.put(w, new HashSet<String>());
		}
		
		for(Map.Entry<String, Set<String>> w1 : m.entrySet()) {
			for(Map.Entry<String, Set<String>> w2 : m.entrySet()) {
				if(strategy.adjacent(w1.getKey(), w2.getKey())) {
					//lägg in w2 i den mängd som associeras till w1 i mappen
					w1.getValue().add(w2.getKey());
				}
			}
		}
	}
	
	public Map<String, Set<String>> getWordMap(){
		return m;	
	}

	@Override
	public Set<String> adjacentTo(String s) {
			return m.get(s);
	}

}
