
public class Main {
	
	public static void main(String[] args) {
		Dialog d = new Dialog();
		Register r = new Register();

		while (true){
			int command = d.readInt("Meny" + "\n"
					+ "1: S�tt in en ny person" + "\n"
					+ "2: Tag bort person(er)" + "\n"
					+ "3: S�k f�delsedatum fr�n namn/del av namn" + "\n"
		 			+ "5: Visa lista sorterad p� namn" + "\n"
		 			+ "6: Visa lista sorterad p� datum");
			
			switch (command) {
			case 1: 
//				1. S�tta in ett nytt namn med tillh�rande f�delsedatum.
				System.out.println("ok");
				break;
			case 2:
//				2. Ta bort alla personer med ett visst namn ur registret.
				break;
				
//				5. Presentera alla personer sorterade efter namn.
			case 5:
				d.printString(r.printByName());
				break;
//				6. Presentera alla personer sorterade efter f�delsedatum.
			case 6:
				d.printString(r.printByDate());
				break;
			case Integer.MAX_VALUE:
				System.exit(0);
			}
		}
		

		

		
//		3. S�ka upp alla f�delsedatum som g�ller f�r ett visst namn. Det skall r�cka att man anger
//		en del av namnet. Samtliga personer vars namn passar in skall presenteras med b�de namn
//		och f�delsedatum.
		
//		4. S�ka upp det eller de namn som har ett visst f�delsedatum. Personerna skall presenteras
//		med b�de namn och f�delsedatum.
		
//		6. Presentera alla personer sorterade efter f�delsedatum.
		
//		7. Spara registret p� en fil.
		
//		8. H�mta uppgifterna till registret fr�n en fil.

	}

}
