import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		CardDeck cd = new CardDeck();
		Scanner scan = new Scanner(System.in);
		double prob;
		int completed = 0;
		System.out.println("Ange antalet g�nger patiensen ska l�ggas ut: ");
		int nbrOfTests = scan.nextInt();

		for(int j = 0; j < nbrOfTests; j++){
			cd.shuffle();
			int counter = 0;
			int i = 1;
			int rank = cd.getCard().getRank();
			while(cd.moreCards() && rank != i){
				i = i % 3;
				rank = cd.getCard().getRank();
				counter++;
				i++;
			}
			if(counter == 51){
				completed++;
			}
		}
		prob = (double) completed / nbrOfTests;

		System.out.println("Sannolikheten att patiensen g�r ut �r: " + prob);


	}

}
