package textproc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> map;
	private Set<String> set;

//	Konstruktorn ska ta en Scanner som parameter, så att undantagsord på så vis kan
//	läsas in från filen undantagsord.txt:
	public GeneralWordCounter(Scanner s) {
		
//		Använd en Map för att hålla reda på hur många gånger respektive ord förekommer,
		map = new HashMap<>();
		
//		samt en mängd (Set) för att hålla reda på undantagsorden.		
//		För din mängd (Set) kan du välja implementation själv.
		set = new HashSet<>();
		
		while(s.hasNext()) {
			String stopWord = s.next().toLowerCase();
			set.add(stopWord);
		}
	}
	
//	Metoden process räknar alla ord, såvida de inte finns i mängden av undantagsord.
//	Första gången ett nytt ord upptäcks läggs det till med antalet 1, och påföljande
//	gånger samma ord upptäcks ökas dess antal med ett.
	@Override
	public void process(String w) {
		if(!set.contains(w)) {
			if (map.containsKey(w)) {
				map.put(w, map.get(w) + 1);
			}
			else {
				map.put(w, 1);
			}
		}
		
	}
//	Metoden report ska skriva ut alla ord som förekommer 200 gånger eller fler. (Om
//	du vill får du gärna göra detta värde till en konstruktorparameter.)
	@Override
	public void report() {
		for(String key: map.keySet()) {
			if(map.get(key) >= 200)
			System.out.println(key + ": " + map.get(key));
		}
		
	}
	

}
