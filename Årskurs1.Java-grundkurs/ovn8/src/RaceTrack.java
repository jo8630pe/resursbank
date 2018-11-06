import se.lth.cs.window.*;

public class RaceTrack {
	private int yStart; 
	private int yFinish;

	/**Skapar en kapplöpningsbana. yStart och yFinish är y-koord. för start och mål*/
	public RaceTrack(int yStart, int yFinish){
		this.yFinish = yFinish;
		this.yStart = yStart;
	}
	
	/**Rita kapplöpningsbanan i föntret */
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
