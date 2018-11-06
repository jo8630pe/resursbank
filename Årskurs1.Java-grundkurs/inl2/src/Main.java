
public class Main {
	
	public static void main(String[] args) {
		Dialog d = new Dialog();
		Register r = new Register();

		while (true){
			int command = d.readInt("Meny" + "\n"
					+ "1: Sätt in en ny person" + "\n"
					+ "2: Tag bort person(er)" + "\n"
					+ "3: Sök födelsedatum från namn/del av namn" + "\n"
		 			+ "5: Visa lista sorterad på namn" + "\n"
		 			+ "6: Visa lista sorterad på datum");
			
			switch (command) {
			case 1: 
//				1. Sätta in ett nytt namn med tillhörande födelsedatum.
				System.out.println("ok");
				break;
			case 2:
//				2. Ta bort alla personer med ett visst namn ur registret.
				break;
				
//				5. Presentera alla personer sorterade efter namn.
			case 5:
				d.printString(r.printByName());
				break;
//				6. Presentera alla personer sorterade efter födelsedatum.
			case 6:
				d.printString(r.printByDate());
				break;
			case Integer.MAX_VALUE:
				System.exit(0);
			}
		}
		

		

		
//		3. Söka upp alla födelsedatum som gäller för ett visst namn. Det skall räcka att man anger
//		en del av namnet. Samtliga personer vars namn passar in skall presenteras med både namn
//		och födelsedatum.
		
//		4. Söka upp det eller de namn som har ett visst födelsedatum. Personerna skall presenteras
//		med både namn och födelsedatum.
		
//		6. Presentera alla personer sorterade efter födelsedatum.
		
//		7. Spara registret på en fil.
		
//		8. Hämta uppgifterna till registret från en fil.

	}

}
