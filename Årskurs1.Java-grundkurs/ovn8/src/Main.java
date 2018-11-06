import se.lth.cs.p.ovn.ladder.*;
import se.lth.cs.window.*;
import java.util.Random;
public class Main {

	public static void main(String[] args) {
		Random rand = new Random();
		Ladder ld = new Ladder(10);
		
		int lastPinne = 1;
		ld.drawMan(lastPinne);
		
		
		while(true){
			SimpleWindow.delay(40);
			double random = rand.nextDouble();
			if(random <= 0.1){
				ld.eraseMan(lastPinne);
				if(lastPinne >=2 && lastPinne <= 9){
					if(random <= 0.05){
						lastPinne++;
					}
					else {
						lastPinne--;
					}
				}
				else if(lastPinne == 10){
						lastPinne--;
				}
				else{
					lastPinne++;
				}
				ld.drawMan(lastPinne);
			}
		}

	}

}
