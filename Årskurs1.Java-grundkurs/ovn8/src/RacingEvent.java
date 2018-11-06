import java.util.Random;
import se.lth.cs.window.*;
public class RacingEvent {
	private Random rand = new Random();
	private Turtle t1;
	private Turtle t2;
	private RaceTrack track;
	//private SimpleWindow w;
	
	
	public RacingEvent(RaceTrack track, Turtle t1, Turtle t2){
		this.t1 = t1;
		this.t2 = t2;
		this.track = track;
	}
	public void race(SimpleWindow w){
		t1.penDown();
		t2.penDown();
		w.waitForMouseClick();
		while((t1.getY() > track.getYFinish()) && (t2.getY() > track.getYFinish())){
			t1.forward(rand.nextInt(3));
			t2.forward(rand.nextInt(3));
			SimpleWindow.delay(10);
		}

	}

}
