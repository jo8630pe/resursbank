package wordladders;

public class OneLetterDifference implements GraphStrategy{

	@Override
	public boolean adjacent(String word1, String word2) {
		int counter = 0;
		if(word1.equals(word2)) {
			return false;
		}
		for(int i = 0; i < word1.length(); i++) {
			if(word1.charAt(i) != word2.charAt(i)) {
				counter++;
				if(counter > 1) {
					return false;
				}
			}
		}
		return true;
	}

}
