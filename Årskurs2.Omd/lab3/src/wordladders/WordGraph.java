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
	
	
/*	Klassen skall ha en konstruktor som bygger upp grafen utg�ende fr�n en fil som inneh�ller orden. Filnamnet
	skall vara parameter till konstruktorn.
	I konstruktorn l�ses orden fr�n filen och grafen (mappen) byggs. B�rja med att l�sa in alla
	orden och l�gg in dem som nycklar i mappen med en tom m�ngd som associerat v�rde. I
	n�sta uppgift kommer konstruktorn att kompletteras s� att b�garna l�ggs in.
	
	Implementera ocks� metoden adjacentTo i klassen.*/
	
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
					//l�gg in w2 i den m�ngd som associeras till w1 i mappen
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
