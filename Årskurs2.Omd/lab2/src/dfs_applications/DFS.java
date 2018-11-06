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
	/*En oriktad graf �r sammanh�ngade om det f�r varje par av noder g�ller att det finns en
	v�g (en serie b�gar) mellan noderna. F�r att avg�ra om detta �r fallet r�cker det att v�lja ut
	en godtycklig nod (v) och unders�ka om det finns v�gar fr�n denna till alla andra noder
	i grafen:*/
	public static <V,E> boolean isConnected(Graph<V,E> g) {
		
		
		/* *Om det inte finns v�gar fr�n v till alla andra noder s� �r grafen inte sammanh�ngande
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
	/*Metoden skall unders�ka om det finns en v�g mellan v och u och i s� fall returnera true.
	Annars skall false returneras.*/
	public static <V,E> boolean pathExists(Graph<V,E> g,
			Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		
		//markera alla noder obes�kta 
		g.unvisit();
		
		//anropar en hj�lpmetod som utf�r traversering fr�n v.

		return pathExists(v,u);
	}
	
	private static <V,E> boolean pathExists(Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		
		//markerar noden som bes�kt
		v.visit();
		
		//Basfall
		if(v.equals(u)) {
			//returnerar true om/n�r m�lnoden bes�ks 
			return true;
		}


		for (Graph.Edge<V,E> e : v) {

			Graph.Vertex<V,E> w = e.destination();

			if (!w.isVisited() && pathExists(w,u)) {
				return true;

			}

		}

		//returera false om m�lnoden ej p�tr�ffas.
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
		
		//markerar noden som bes�kt
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

