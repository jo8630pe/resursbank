import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int latest = 0;
		int first;
		int low = 0;
		int high = 0;
		
		System.out.println("Mata in 11 tal:");
		
		first = scan.nextInt();
		
		for(int i = 0; i < 10; i++){
			latest = scan.nextInt();
			if(latest > first){
				high += latest; 
			}
			else{
				low += latest;
			}

		}
		System.out.println("Summan av tal större än det första talet: " + high);
		System.out.println("Summan av tal större än det första talet: " + low);
		
		
//		int departHour, departMin, travelTimeHour, travelTimeMin;
//		while(true){
//
//			int arrivalHour = 0;
//			int arrivalMin = 0;
//			int days = 0;
//			
//			System.out.println("Ange avgångstid(separera timmar och minuter med retur): ");
//			departHour = scan.nextInt();
//			departMin = scan.nextInt();
//			
//			System.out.println("Ange körtid(separera timmar och minuter med retur): ");
//			travelTimeHour = scan.nextInt();
//			travelTimeMin = scan.nextInt();
//			
//			days = (departHour + travelTimeHour) / 24;
//			
//			if((departMin + travelTimeMin) > 59){
//				arrivalHour = (departMin + travelTimeMin) / 60;
//				arrivalMin = (departMin + travelTimeMin) % 60;
//			}
//			else{
//				arrivalMin = departMin + travelTimeMin;
//			}
//			
//			if((departHour + travelTimeHour) > 23){
//				if(arrivalHour == 24){
//					arrivalHour = 0;
//				}
//				arrivalHour += (departHour + travelTimeHour) % 24;
//			}
//			else{
//			arrivalHour += departHour + travelTimeHour;
//			}
//			
//			if(days > 0){
//				if(arrivalMin < 10){
//					System.out.println("Ankomsttid: kl " + arrivalHour +":0" + arrivalMin + ", " + days + " dag/dagar efter avresa.");
//				}
//				else{
//					System.out.println("Ankomsttid: kl " + arrivalHour +":" + arrivalMin + ", " + days + " dag/dagar efter avresa.");
//				}
//			}
//			else{
//				if(arrivalMin < 10){
//					System.out.println("Ankomsttid: kl " + arrivalHour +":0" + arrivalMin);
//				}
//				else{
//					System.out.println("Ankomsttid: kl " + arrivalHour +":" + arrivalMin);
//				}
//			}
//		}
		
		
	}

}
