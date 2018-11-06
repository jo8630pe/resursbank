package dijkstra;
import graph.Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Dijkstra<V,E> {
	Comparator<E> cmp;

	public E shortestPath(Graph<V,E> g, Graph.Vertex<V,E> from, Graph.Vertex<V,E> to,
			Comparator<E> cmp, Adder<E> adder){
		this.cmp = cmp;
//		pq = tom prioritetsk�
		PriorityQueue<PrioQueueEntry> pq = new PriorityQueue<PrioQueueEntry>(new ComparePQEntry());
		
//		vertexMap = tom map (nod --> avst�nd)
		Map<Graph.Vertex<V,E>, E> vertexMap = new HashMap<Graph.Vertex<V,E>, E>(); 
		
//		done = en tom m�ngd
		Set<Graph.Vertex<V,E>> done = new HashSet<Graph.Vertex<V,E>>();
		
//		pq.offer({v,0})
		pq.offer(new PrioQueueEntry(from,adder.getZero()));
		
//		vertexMap.put(v,0)
		vertexMap.put(from, adder.getZero());
		
//		s� l�nge inte pq �r tom
		while(!pq.isEmpty()){
			
//			{actVertex,dist} = minsta elementet i pq // som tas ut ur k�n
			PrioQueueEntry current = pq.poll();
			E dist = current.distance;
			
//			om actVertex == m�lnoden u
			if(current.vertex == to){
//				return dist
				return dist;
			}

//			om actVertex inte finns i done
			if(!done.contains(current.vertex)){
//				done.add(actVertex)
				done.add(current.vertex);
//				for varje b�ge e fr�n actVertex
				for(Graph.Edge<V, E> e : current.vertex){
//					w = e.destination()
					Graph.Vertex<V, E> w = e.destination();
					
//					newdist = dist + e.getValue()
					E newdist = adder.add(dist, e.getValue());

//					om (!vertexMap.containsKey(w)) || (newdist < vertexMap.get(w))
					if (!vertexMap.containsKey(w) || cmp.compare(newdist, vertexMap.get(w) ) < 0 ){
						pq.offer(new PrioQueueEntry(w,newdist));
						vertexMap.put(w,newdist);
					}
					
				}

			}
		}

		return null;
	}
	
	
//	F�r att implementera metoden ovan m�ste vi inf�ra en klass f�r de element som l�ggs
//	in i priotritetsk�n. �ppna klassen Dijkstra i editorn i Eclipse och l�gg till en inre privat
//	klass PrioQueueEntry. Denna klass beh�ver inte ha n�gra typparametrar om den inte
//	deklareras static. Den kan d� anv�nda de typparmetrar <V,E> som finns i den yttre
//	klassen. Klassen skall ha tv� attribut och en konstruktor. Det ena attributet refererar till
//	en nod i grafen och det andra till avst�ndet p� den hittills kortaste v�gen till noden. Det
//	andra attributet �r allts� av typen E.
	private class PrioQueueEntry{
		Graph.Vertex<V,E> vertex;
		E distance;
		
		public PrioQueueEntry(Graph.Vertex<V,E> vertex, E distance){
			this.vertex = vertex;
			this.distance = distance;
		}
		
	}
	
	
//	Vi beh�ver kunna j�mf�ra element av typen PrioQueueEntry med varandra eftersom de
//	skall l�ggas in i en prioritetsk�. L�gg d�rf�r till ytterligare en inre klass i klassen Dijkstra.
//	Den nya klassen skall implementera interfacet Comparator<PrioQueueEntry>. J�mf�relse
//	skall g�ras med avseende p� �avst�ndsattributet� i elementen. Detta �r av typen E.
//	F�r j�mf�relse mellan element av typen E finns det Comparator-objekt som �r parameter
//	till metoden shortestPath. Denna parameter �r dock inte tillg�nglig i den nya klassen.
//	Inf�r d�rf�r ett attribut i den omgivande klassen och se till att i b�rjan av metoden
//	shortestPath lagra j�mf�relseobjektet i detta attribut.
	public class ComparePQEntry implements Comparator<PrioQueueEntry>{

		@Override
		public int compare(Dijkstra<V, E>.PrioQueueEntry entry1, Dijkstra<V, E>.PrioQueueEntry entry2) {
			return cmp.compare(entry1.distance, entry2.distance);
		}
		
	}
}
