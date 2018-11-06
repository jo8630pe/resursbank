package maxflow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class Maxflow {
	int nbrOfVertices;
	int nbrOfEdges;
	int[][] graph; 

	public Maxflow(String infile) throws FileNotFoundException{

		Scanner scan = null;
		scan = new Scanner(new File(infile));

		nbrOfVertices = scan.nextInt();
		nbrOfEdges = scan.nextInt();

		graph = new int[nbrOfVertices][nbrOfVertices];

		for(int row = 0; row < nbrOfVertices; row++){
			for(int col = 0; col < nbrOfVertices; col++){
				graph[row][col] = 0;
			}
		}

		while (scan.hasNext()) {
			int row = scan.nextInt();
			int column = scan.nextInt();
			int value = scan.nextInt();

			if(value == -1){
				graph[row][column] = Integer.MAX_VALUE;
			}
			else{
				graph[row][column] = value;
			}

		}
		scan.close();

	}


	//	Implementera i klassen en metod som beräknar maximala flödet från nod nummer 0 till
	//	nod nummer n-1 i det nätverk som representeras av matrisen från föregående uppgift.
	public int getMaxFlow(){
		int maxFlow = 0;
		int[] parentTracker = new int[nbrOfVertices]; //håller reda på föregångaren på vägen till sänkan

		//		Metoden skall skapa en residualmatris av samma storlek och initialt med samma
		//		värden som den matris som representerar nätverket.
		int[][] resGraph = new int[nbrOfVertices][nbrOfVertices];
		for(int row = 0; row < nbrOfVertices; row++){
			for(int col = 0; col < nbrOfVertices; col++){
				resGraph[row][col] = graph[row][col];
			}
		}

		//		Därefter skall den försöka hitta en väg från källan till sänkan som består av bågar
		//		med positiva värden i residualmatrisen. Detta skall fortgå tills man inte längre hittar
		//		någon sådan väg. Det är lämpligt att låta en hjälpmetod försöka hitta en sådan väg:
		int pathFlow = Integer.MAX_VALUE;
		while(pathFlow > 0){
			pathFlow = bfsPathFlow(parentTracker, resGraph, pathFlow);
			int u, v;

			v = nbrOfVertices - 1;
			//	När flaskhalsen hittats skall residualmatrisen uppdateras för hela den funna
			//	vägen från källan till sänkan.
			while(v != 0){
				u = parentTracker[v]; //u blir föregångaren till v
				//  Om t.ex. flaskhalsens värde är c så innebär det
				//	att flödet kan ökas med c på alla bågar på vägen från källan till sänkan och
				//	därmed minskar de med c på motsvarande bågar i residualmatrisen.
				resGraph[u][v] -= pathFlow;
				
				//	Dessutom läggs ett omvänt flöde till i residualmatrisen. Endast residualmatrisen behöver
				//	uppdateras.
				resGraph[v][u] += pathFlow;
				v = parentTracker[v]; // v blir föregångaren till v
			}
			maxFlow += pathFlow;
			
		}
		
		//		Metoden skall returnera det maximala flödet (ett heltal).
		return maxFlow;
	}

	//	– Använd t.ex. bredden först för att försöka hitta en väg från nod 0 till nod n-1.
	private int bfsPathFlow(int[] parentTracker, int[][] resGraph, int pathFlow) {
		boolean[] visited = new boolean[nbrOfVertices];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int u, v;
		
		queue.add(0);
		visited[0] = true;
		parentTracker[0] = -1;
		
		while(!queue.isEmpty()){
			
			u = queue.poll();
			
			for(v = 0; v < nbrOfVertices; v++){
				//	Vägen får dock bara använda bågar med positivt värde i residualmatrisen.
				if(!visited[v] && resGraph[u][v] > 0){
					queue.add(v);
					visited[v] = true;
					parentTracker[v] = u;
				}
			}
		}
		
		v = nbrOfVertices - 1;
		
		//	– Om en sådan väg hittas skall ”flaskhalsen” på vägen hittas d.v.s. den båge som
		//	har minst värde. Det är detta värde som bestämmer hur mycket man kan öka
		//	flödet på vägen. För att hitta denna måste man veta vilka bågar som ingår. Under
		//	breddenförstsökningen kan man t.ex i en vektor hålla reda på föregångaren på
		//	vägen till sänkan. Vektorelementet på plats i anger då numret på föregångaren
		//	till nod i på den funna vägen från källan till sänkan.
		if(visited[v]){
			while(v != 0){
				u = parentTracker[v]; //u blir föregångaren till v
				pathFlow = Math.min(pathFlow, resGraph[u][v]);
				v = parentTracker[v]; //v blir föregångaren till v
			}
			//c returneras till den anropande huvudmetoden som därmed kan
			//hålla reda på flödets storlek.
			return pathFlow;
		}
		else{
			return 0;
		}
	}

}
