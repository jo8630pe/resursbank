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
//		pq = tom prioritetskö
		PriorityQueue<PrioQueueEntry> pq = new PriorityQueue<PrioQueueEntry>(new ComparePQEntry());
		
//		vertexMap = tom map (nod --> avstånd)
		Map<Graph.Vertex<V,E>, E> vertexMap = new HashMap<Graph.Vertex<V,E>, E>(); 
		
//		done = en tom mängd
		Set<Graph.Vertex<V,E>> done = new HashSet<Graph.Vertex<V,E>>();
		
//		pq.offer({v,0})
		pq.offer(new PrioQueueEntry(from,adder.getZero()));
		
//		vertexMap.put(v,0)
		vertexMap.put(from, adder.getZero());
		
//		så länge inte pq är tom
		while(!pq.isEmpty()){
			
//			{actVertex,dist} = minsta elementet i pq // som tas ut ur kön
			PrioQueueEntry current = pq.poll();
			E dist = current.distance;
			
//			om actVertex == målnoden u
			if(current.vertex == to){
//				return dist
				return dist;
			}

//			om actVertex inte finns i done
			if(!done.contains(current.vertex)){
//				done.add(actVertex)
				done.add(current.vertex);
//				for varje båge e från actVertex
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
	
	
//	För att implementera metoden ovan måste vi införa en klass för de element som läggs
//	in i priotritetskön. Öppna klassen Dijkstra i editorn i Eclipse och lägg till en inre privat
//	klass PrioQueueEntry. Denna klass behöver inte ha några typparametrar om den inte
//	deklareras static. Den kan då använda de typparmetrar <V,E> som finns i den yttre
//	klassen. Klassen skall ha två attribut och en konstruktor. Det ena attributet refererar till
//	en nod i grafen och det andra till avståndet på den hittills kortaste vägen till noden. Det
//	andra attributet är alltså av typen E.
	private class PrioQueueEntry{
		Graph.Vertex<V,E> vertex;
		E distance;
		
		public PrioQueueEntry(Graph.Vertex<V,E> vertex, E distance){
			this.vertex = vertex;
			this.distance = distance;
		}
		
	}
	
	
//	Vi behöver kunna jämföra element av typen PrioQueueEntry med varandra eftersom de
//	skall läggas in i en prioritetskö. Lägg därför till ytterligare en inre klass i klassen Dijkstra.
//	Den nya klassen skall implementera interfacet Comparator<PrioQueueEntry>. Jämförelse
//	skall göras med avseende på ”avståndsattributet” i elementen. Detta är av typen E.
//	För jämförelse mellan element av typen E finns det Comparator-objekt som är parameter
//	till metoden shortestPath. Denna parameter är dock inte tillgänglig i den nya klassen.
//	Inför därför ett attribut i den omgivande klassen och se till att i början av metoden
//	shortestPath lagra jämförelseobjektet i detta attribut.
	public class ComparePQEntry implements Comparator<PrioQueueEntry>{

		@Override
		public int compare(Dijkstra<V, E>.PrioQueueEntry entry1, Dijkstra<V, E>.PrioQueueEntry entry2) {
			return cmp.compare(entry1.distance, entry2.distance);
		}
		
	}
}
