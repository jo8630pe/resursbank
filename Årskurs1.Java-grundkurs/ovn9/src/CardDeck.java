import java.util.Random;

public class CardDeck {
	
	private Card[] theCards; //vektor med kort
	private boolean moreCards;
	private int topCard = 51;
	private Random rand = new Random();
	//...
	
	/** Skapar en kortlek */
	public CardDeck(){
		theCards = new Card[52];
		int cardNbr =0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 13; j++){
				theCards[cardNbr] = new Card(i+1,j+1);
				cardNbr++;
			}
		}
		moreCards = true;
		topCard = 51;
	}
	
	public void shuffle(){
		int nbr;
		Card temp;
		for(int i = 51; i > 0; i--){
			nbr = rand.nextInt(i);
			temp = theCards[i];
			theCards[i] = theCards[nbr];
			theCards[nbr] = temp;
		}
		topCard = 51;
		moreCards = true;
	}
	
	public boolean moreCards(){
		return moreCards;
	}
	
	public Card getCard(){
		int prevTopCard = topCard;
		if(topCard>0){
			topCard--;
		}
		else{
			moreCards = false;
		}
		return theCards[prevTopCard];
	}
	

}
