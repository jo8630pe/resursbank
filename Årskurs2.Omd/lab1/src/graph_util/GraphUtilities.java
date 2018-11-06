package graph_util;
import graph.Graph;
import graph.Graph.Vertex;

import java.util.HashSet;
import java.util.Set;

public class GraphUtilities {
	
	/*Metoden skall returnera antalet noder i grafen g. N�gra kommentarer till metodsignaturen:
	 * 
		* Metoden har deklarerats som static eftersom den inte beh�ver anv�nda n�gra attribut
		i den omgivande klassen.
		
		* Metoden har utformats s� att den blir generell genom att parametern �r ett interface
		som har typparametrar. Metoden �r allts� generisk och kan anropas med en aktuell
		parameter som �r ett objekt av en godtycklig klass som implementerar interfacet och
		med godtyckliga v�rden p� typparametrarna.

		* Syntaxen f�r statiska metoder som har typparametriserade parametrar kr�ver att
		dessa r�knas upp i metodrubriken f�re metodens returtyp. Ex:
		
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
	/*Metoden skall returnera antalet b�gar i grafen g. Metodens andra parameter
	anger om grafen �r riktad eller inte. Om den inte �r riktad f�ruts�tter vi att en b�ge mellan
	tv� noder representeras av tv� riktade b�gar d� man bygger grafen. I detta fall m�ste allts�
	antalet b�gar divideras med 2 innan det returneras.*/
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
