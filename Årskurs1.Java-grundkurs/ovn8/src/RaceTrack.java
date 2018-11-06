import se.lth.cs.window.*;

public class RaceTrack {
	private int yStart; 
	private int yFinish;

	/**Skapar en kappl�pningsbana. yStart och yFinish �r y-koord. f�r start och m�l*/
	public RaceTrack(int yStart, int yFinish){
		this.yFinish = yFinish;
		this.yStart = yStart;
	}
	
	/**Rita kappl�pningsbanan i f�ntret */
	public void draw(SimpleWindow w){
		w.moveTo(50, yFinish);
		w.lineTo(350, yFinish);
		w.moveTo(50, yStart);
		w.lineTo(350, yStart);
		
	}
	
	public int getYStart(){
		return yStart;
	}
	
	public int getYFinish(){
		return yFinish;
	}
	
	//infoga fler metoder

}
