import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n;
		boolean primtal;
		while(true){
			
			primtal = true;
			System.out.println("Mata in ett tal: ");
			n = scan.nextInt();
			
			if (n % 2 == 0){
				primtal = false;
			}
			
			else{
				for(int i = 3 ; i <= Math.sqrt(n); i += 2){
					if ((n % i) == 0){
						primtal = false;
					}
				}	
			}
			
			if(!primtal){
				System.out.println("Inget primtal");
			}
			else{
				System.out.println("Primtal");
			}
			
		}

		
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Mata in en följd av positiva tal, där det första anger antalet tal som följer: ");
//		int n = scan.nextInt();
//		double x = 0;
//		double latestX;
//		double xSquare = 0;
//		for (int i = 0; i < n; i++){
//			latestX = scan.nextInt();
//			x += latestX;
//			xSquare += Math.pow(latestX, 2);
//		}
//		double m = x/n;
//		double s = Math.sqrt(((xSquare - (n*Math.pow(m,2)))/(n-1)));
//		
//		System.out.println("Medelvärde: " + m);
//		if(n < 5){
//			System.out.println("För få tal för standardavvikelse");
//		}
//		else{
//			System.out.println("Standardavvikelse: " + s);
//		}
	}
}
