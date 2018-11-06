package todo;

import done.ClockInput;
import se.lth.cs.realtime.semaphore.Semaphore;

public class ButtonEvent extends Thread{
	private ClockData cd;
	
	public ButtonEvent(ClockData cd){
		this.cd = cd;
	}
	
	public void run(){
		
		while(true){
			
			cd.actOnButtonEvent();
		
		}
		
	}
	
}
