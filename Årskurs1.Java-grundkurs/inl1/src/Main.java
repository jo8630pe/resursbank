import se.lth.cs.p.inl1.*;
import se.lth.cs.p.inl1.nbr7.*;

public class Main {

	public static void main(String[] args) {
		Key key = new Key();
		TestCase tk = new TestCase();
		TextWindow tw = new TextWindow("Chiffer");
		Cryptographer c = new Cryptographer(key);
		TextView tv = new TextView("Text", 3, 150);
		TextView tv2 = new TextView("Min chiffer", 3, 150);
		TextView tv3 = new TextView("Riktig chiffer", 3, 150);
		tw.addView(tv);
		tw.addView(tv2);
		tw.addView(tv3);
		
		for(int i = 1; i <= 5; i++){
			tw.waitForMouseClick();
			
			tv.displayText(tk.getClearText(i));
			tv2.displayText(tk.getCryptoText(i));
			tv3.displayText(c.encrypt(tk.getClearText(i)));

		}
		

	}

}
