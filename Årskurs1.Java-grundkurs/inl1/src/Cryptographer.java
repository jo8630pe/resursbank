import se.lth.cs.p.inl1.*;
import se.lth.cs.p.inl1.nbr7.*;

public class Cryptographer {
	private Key key;
	
		/** Skapar ett objekt för chiffrering där nyckeln key används */
		public Cryptographer(Key key){
			this.key = key;
		}
		/** Chiffrerar texten text och returnerar chiffertexten */
		public String encrypt(String text){
			StringBuilder sb = new StringBuilder();
			int charNbr = 0;
			for(int i = 0; i < text.length(); i++){
				
				if(text.charAt(i) == ' '){
					sb.append(text.charAt(i));
				}
				else{
					int mod = charNbr % 5;
					if(((text.charAt(i) - 'A') + (key.getLetter(mod) +1 )) > 'Z'){
						sb.append((char) (((text.charAt(i)) + (key.getLetter(mod))  ) -'Z') );
						charNbr++;
					}
					else{
						sb.append((char) ((text.charAt(i) - 'A') + (key.getLetter(mod) +1 ) ) );
						charNbr++;
						
					}
					
				}
				
			}
			
			return sb.toString();
		}
}

