package wordladders;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShortestPath {

	public static int shortestPath(SimpleGraph g,
						String source, String dest) {

		/*I denna utformning anv�nder vi tv� m�ngder: en som best�r av de noder som finns p�
		ett visst kortaste avst�nd, distance, fr�n utg�ngsnoden (v) och en f�r dem som befinner sig p�
		avst�ndet distance+1 fr�n denna. Den senare (nextLevel) byggs upp av �nnu ej bes�kta grannar
		till den f�rra (actlevel) i loopen. I slutet av loopen l�ter vi sedan nextLevel bli den aktuella
		niv�n och �kar distance med ett s� att v�rdet motsvarar det avst�nd noderna i actLevel ligger
		fr�n utg�ngsnoden. 
		Som framg�r av koden ovan s� beh�ver vi f�r varje nod som behandlas i grafen f� tillg�ng till
		grannarna.*/
		
		int distance = 0;
//		markera u bes�kt
		Set<String> visitedWords = new HashSet<String>();

		visitedWords.add(source);
//		actlevel = tom m�ngd
		Set<String> actlevel = new HashSet<String>();
//		l�gg in u i actlevel
		actlevel.add(source);
//		s� l�nge actlevel inte �r tom
		while(!actlevel.isEmpty()) {
//			nextlevel = tom m�ngd
			Set<String> nextlevel = new HashSet<String>();
//			f�r varje nod w i actlevel
			for(String w: actlevel) {
//				om w == v
				if(w.equals(dest)) {
//					returnera distance
					return distance;
				}
				Set<String> neighbours = new HashSet<String>();
				neighbours = g.adjacentTo(w);
//				f�r varje granne n till w
				for(String n : neighbours) {
//					om n inte �r bes�kt
					if(!visitedWords.contains(n)) {
//						markera n bes�kt
						visitedWords.add(n);
//						l�gg in n i nextlevel
						nextlevel.add(n);
					}
				}	
			}	
			distance++;
			actlevel = nextlevel;
		}	
		return -1;
	}

}
