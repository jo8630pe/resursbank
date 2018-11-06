import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		while(true){
			Scanner scan = new Scanner(System.in);
			System.out.println("Mata in minv�rde");
			int min = scan.nextInt();
			System.out.println("Mata in maxv�rde");
			int max = scan.nextInt();
			NumberGenerator ng = new NumberGenerator(min,max);
			ng.drawNbr();
			
			
			int guess = (max-min)/2;
			int counter = 0;
			
			while(!ng.isEqual(guess)){
				counter++;
				if(ng.isBiggerThan(guess)){
					min = guess + 1;
					guess = ((max - min)/2) + min; 
				}
				
				else {
					max = guess - 1;
					guess = ((max - min)/2) + min; 
				}

			}
			counter++;
			System.out.println("Det tog " + counter + " f�rs�k f�r datorn att gissa r�tt.");
		}
		
	}

}
