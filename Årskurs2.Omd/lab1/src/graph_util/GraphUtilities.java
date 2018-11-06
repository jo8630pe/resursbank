package graph_util;
import graph.Graph;
import graph.Graph.Vertex;

import java.util.HashSet;
import java.util.Set;

public class GraphUtilities {
	
	/*Metoden skall returnera antalet noder i grafen g. Några kommentarer till metodsignaturen:
	 * 
		* Metoden har deklarerats som static eftersom den inte behöver använda några attribut
		i den omgivande klassen.
		
		* Metoden har utformats så att den blir generell genom att parametern är ett interface
		som har typparametrar. Metoden är alltså generisk och kan anropas med en aktuell
		parameter som är ett objekt av en godtycklig klass som implementerar interfacet och
		med godtyckliga värden på typparametrarna.

		* Syntaxen för statiska metoder som har typparametriserade parametrar kräver att
		dessa räknas upp i metodrubriken före metodens returtyp. Ex:
		
		public static <T> void p(List<T> list) { 
		...
		}
		
		public static <T extends Comparable<T>> void max(List<T> list) {
		...
		}
	*/
	public static <V,E> int nbrOfVertices(Graph<V,E> g) {
		int vert = 0;
		
		for(Graph.Vertex<V,E> v: g) {
			vert++;
		}
		
		return vert;
	}
	/*Metoden skall returnera antalet bågar i grafen g. Metodens andra parameter
	anger om grafen är riktad eller inte. Om den inte är riktad förutsätter vi att en båge mellan
	två noder representeras av två riktade bågar då man bygger grafen. I detta fall måste alltså
	antalet bågar divideras med 2 innan det returneras.*/
	public static <V,E> int nbrOfEdges(Graph<V,E> g, boolean directed) {
		int edges = 0;
		
		for(Graph.Vertex<V,E> v: g) {
			for (Graph.Edge<V,E> e : v) {
				edges++;
			}
		}

		if(!directed) {
			return edges/2;
		}
		
		return edges;
	}
	
	public static <V,E> boolean edgeBetween(Graph.Vertex<V,E> from, 
										Graph.Vertex<V,E> to) {
		
			for (Graph.Edge<V,E> e : from) {
				if(e.destination().equals(to)) {
					return true;
				}
			}
			
		return false;
	}
}
