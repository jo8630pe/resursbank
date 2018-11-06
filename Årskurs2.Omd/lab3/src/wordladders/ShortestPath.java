package wordladders;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShortestPath {

	public static int shortestPath(SimpleGraph g,
						String source, String dest) {

		/*I denna utformning använder vi två mängder: en som består av de noder som finns på
		ett visst kortaste avstånd, distance, från utgångsnoden (v) och en för dem som befinner sig på
		avståndet distance+1 från denna. Den senare (nextLevel) byggs upp av ännu ej besökta grannar
		till den förra (actlevel) i loopen. I slutet av loopen låter vi sedan nextLevel bli den aktuella
		nivån och ökar distance med ett så att värdet motsvarar det avstånd noderna i actLevel ligger
		från utgångsnoden. 
		Som framgår av koden ovan så behöver vi för varje nod som behandlas i grafen få tillgång till
		grannarna.*/
		
		int distance = 0;
//		markera u besökt
		Set<String> visitedWords = new HashSet<String>();

		visitedWords.add(source);
//		actlevel = tom mängd
		Set<String> actlevel = new HashSet<String>();
//		lägg in u i actlevel
		actlevel.add(source);
//		så länge actlevel inte är tom
		while(!actlevel.isEmpty()) {
//			nextlevel = tom mängd
			Set<String> nextlevel = new HashSet<String>();
//			för varje nod w i actlevel
			for(String w: actlevel) {
//				om w == v
				if(w.equals(dest)) {
//					returnera distance
					return distance;
				}
				Set<String> neighbours = new HashSet<String>();
				neighbours = g.adjacentTo(w);
//				för varje granne n till w
				for(String n : neighbours) {
//					om n inte är besökt
					if(!visitedWords.contains(n)) {
//						markera n besökt
						visitedWords.add(n);
//						lägg in n i nextlevel
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
