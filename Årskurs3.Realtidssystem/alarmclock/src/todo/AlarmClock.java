package todo;
import done.*;

public class AlarmClock extends Thread {

	private static ClockOutput	output;
	private static ClockData cd;
	private static ButtonEvent buttonEvent;

	public AlarmClock(ClockInput i, ClockOutput o) {
		output = o;
		cd = new ClockData(o, i);
		buttonEvent = new ButtonEvent(cd);
	}

	public void run() {
		buttonEvent.start();
		try{
			sleep(1000);
		} catch (InterruptedException e1) {
			System.out.println("couldn't sleep");
		}
		long t = System.currentTimeMillis();
		
		while (true) {
			
			cd.tick();

			t += 1000;
			long diff = t - System.currentTimeMillis();
			
			if(diff > 0){
				
				try {
					sleep(diff);
				} catch (InterruptedException e) {
					System.out.println("couldn't sleep");
				}
			}
		}
	}
}