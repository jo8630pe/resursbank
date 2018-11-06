package dfs_applications;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import graph.Graph;
import graph.Graph.Vertex;


public class DFS  {
	
	public static <V,E> void dfs(Graph<V,E> g) {
		g.unvisit();
		for (Graph.Vertex<V,E> v : g) {
			if (!v.isVisited()) {
				dfs(v);
			}
		}
	}
	
	private static <V,E> void dfs(Graph.Vertex<V,E> v) {
		v.visit();
		for (Graph.Edge<V,E> e : v) {
			Graph.Vertex<V,E> w = e.destination();
			if (!w.isVisited()) {
				dfs(w);
			}
		}
	}
	/*En oriktad graf är sammanhängade om det för varje par av noder gäller att det finns en
	väg (en serie bågar) mellan noderna. För att avgöra om detta är fallet räcker det att välja ut
	en godtycklig nod (v) och undersöka om det finns vägar från denna till alla andra noder
	i grafen:*/
	public static <V,E> boolean isConnected(Graph<V,E> g) {
		
		
		/* *Om det inte finns vägar från v till alla andra noder så är grafen inte sammanhängande
		(enligt definitionen).*/
		
		
		Iterator<Vertex<V, E>> itr = g.iterator();
		if(itr.hasNext()) {
			dfs(itr.next());
		}
		else {
			return false;
		}
		
		while(itr.hasNext()) {
			if(!itr.next().isVisited()) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public static <V,E> int nbrOfComponents(Graph<V,E> g) {
		int count = 0;
		Iterator<Vertex<V, E>> itr = g.iterator();
		
		while(itr.hasNext()) {
			Graph.Vertex<V,E> temp = itr.next();
			if(!temp.isVisited()) {
				dfs(temp);
				count++;
			}
		}

		return count;
	}
	/*Metoden skall undersöka om det finns en väg mellan v och u och i så fall returnera true.
	Annars skall false returneras.*/
	public static <V,E> boolean pathExists(Graph<V,E> g,
			Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		
		//markera alla noder obesökta 
		g.unvisit();
		
		//anropar en hjälpmetod som utför traversering från v.

		return pathExists(v,u);
	}
	
	private static <V,E> boolean pathExists(Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		
		//markerar noden som besökt
		v.visit();
		
		//Basfall
		if(v.equals(u)) {
			//returnerar true om/när målnoden besöks 
			return true;
		}


		for (Graph.Edge<V,E> e : v) {

			Graph.Vertex<V,E> w = e.destination();

			if (!w.isVisited() && pathExists(w,u)) {
				return true;

			}

		}

		//returera false om målnoden ej påträffas.
		return false;
	}
	
	public static <V,E> List<Graph.Vertex<V,E>> findPath(Graph<V,E> g,
			Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		
		List<Graph.Vertex<V,E>> l = new LinkedList<Graph.Vertex<V,E>>();
		
		g.unvisit();

		if(foundPath(l,v,u)) {
			return l;
		}
		

		return l;
	}
	
	private static <V,E> boolean foundPath(List<Graph.Vertex<V,E>> l,
			Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		
		//markerar noden som besökt
		v.visit();
		l.add(v);
		
		
		//Basfall
		if(v.equals(u)) {
			return true;
		}

		for (Graph.Edge<V,E> e : v) {
			
			Graph.Vertex<V,E> w = e.destination();
			
			if (!w.isVisited() && foundPath(l,w,u)) {
				return true;
			}
			
		}
		((LinkedList<Graph.Vertex<V,E>>) l).removeLast();

		return false;
	}
	

}

