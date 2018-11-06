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


	//	Implementera i klassen en metod som ber�knar maximala fl�det fr�n nod nummer 0 till
	//	nod nummer n-1 i det n�tverk som representeras av matrisen fr�n f�reg�ende uppgift.
	public int getMaxFlow(){
		int maxFlow = 0;
		int[] parentTracker = new int[nbrOfVertices]; //h�ller reda p� f�reg�ngaren p� v�gen till s�nkan

		//		Metoden skall skapa en residualmatris av samma storlek och initialt med samma
		//		v�rden som den matris som representerar n�tverket.
		int[][] resGraph = new int[nbrOfVertices][nbrOfVertices];
		for(int row = 0; row < nbrOfVertices; row++){
			for(int col = 0; col < nbrOfVertices; col++){
				resGraph[row][col] = graph[row][col];
			}
		}

		//		D�refter skall den f�rs�ka hitta en v�g fr�n k�llan till s�nkan som best�r av b�gar
		//		med positiva v�rden i residualmatrisen. Detta skall fortg� tills man inte l�ngre hittar
		//		n�gon s�dan v�g. Det �r l�mpligt att l�ta en hj�lpmetod f�rs�ka hitta en s�dan v�g:
		int pathFlow = Integer.MAX_VALUE;
		while(pathFlow > 0){
			pathFlow = bfsPathFlow(parentTracker, resGraph, pathFlow);
			int u, v;

			v = nbrOfVertices - 1;
			//	N�r flaskhalsen hittats skall residualmatrisen uppdateras f�r hela den funna
			//	v�gen fr�n k�llan till s�nkan.
			while(v != 0){
				u = parentTracker[v]; //u blir f�reg�ngaren till v
				//  Om t.ex. flaskhalsens v�rde �r c s� inneb�r det
				//	att fl�det kan �kas med c p� alla b�gar p� v�gen fr�n k�llan till s�nkan och
				//	d�rmed minskar de med c p� motsvarande b�gar i residualmatrisen.
				resGraph[u][v] -= pathFlow;
				
				//	Dessutom l�ggs ett omv�nt fl�de till i residualmatrisen. Endast residualmatrisen beh�ver
				//	uppdateras.
				resGraph[v][u] += pathFlow;
				v = parentTracker[v]; // v blir f�reg�ngaren till v
			}
			maxFlow += pathFlow;
			
		}
		
		//		Metoden skall returnera det maximala fl�det (ett heltal).
		return maxFlow;
	}

	//	� Anv�nd t.ex. bredden f�rst f�r att f�rs�ka hitta en v�g fr�n nod 0 till nod n-1.
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
				//	V�gen f�r dock bara anv�nda b�gar med positivt v�rde i residualmatrisen.
				if(!visited[v] && resGraph[u][v] > 0){
					queue.add(v);
					visited[v] = true;
					parentTracker[v] = u;
				}
			}
		}
		
		v = nbrOfVertices - 1;
		
		//	� Om en s�dan v�g hittas skall �flaskhalsen� p� v�gen hittas d.v.s. den b�ge som
		//	har minst v�rde. Det �r detta v�rde som best�mmer hur mycket man kan �ka
		//	fl�det p� v�gen. F�r att hitta denna m�ste man veta vilka b�gar som ing�r. Under
		//	breddenf�rsts�kningen kan man t.ex i en vektor h�lla reda p� f�reg�ngaren p�
		//	v�gen till s�nkan. Vektorelementet p� plats i anger d� numret p� f�reg�ngaren
		//	till nod i p� den funna v�gen fr�n k�llan till s�nkan.
		if(visited[v]){
			while(v != 0){
				u = parentTracker[v]; //u blir f�reg�ngaren till v
				pathFlow = Math.min(pathFlow, resGraph[u][v]);
				v = parentTracker[v]; //v blir f�reg�ngaren till v
			}
			//c returneras till den anropande huvudmetoden som d�rmed kan
			//h�lla reda p� fl�dets storlek.
			return pathFlow;
		}
		else{
			return 0;
		}
	}

}
