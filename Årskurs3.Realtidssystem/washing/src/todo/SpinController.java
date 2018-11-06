package todo;


import se.lth.cs.realtime.*;
import done.AbstractWashingMachine;


public class SpinController extends PeriodicThread {
	private AbstractWashingMachine mach;
	SpinEvent se;
	boolean spinRight;
	boolean spinSlow;
	int mode;
	int counter;
	long startTime;
	long sampleTime;
	double speed;
	
	public SpinController(AbstractWashingMachine mach, double speed) {
		super((long) (100/speed)); // TODO: replace with suitable period
		this.mach = mach;
		
		spinRight = true;
		mode = 3;
		counter = 0;
	}

	public void perform() {
		SpinEvent temp = (SpinEvent) mailbox.tryFetch();
		sampleTime = System.currentTimeMillis(); 
		if(temp != null){
			mode = temp.getMode();
			
		}
		
		if(spinSlow){
			if(sampleTime - startTime > 60000/50){
				startTime = System.currentTimeMillis();
				if(spinRight){
					mach.setSpin(AbstractWashingMachine.SPIN_LEFT);
					spinRight = false;
				}
				else{
					mach.setSpin(AbstractWashingMachine.SPIN_RIGHT);
					spinRight = true;
				}
			}
		}
	    
		switch(mode){
			case SpinEvent.SPIN_FAST:
				if(mach.getWaterLevel() == 0){
					mach.setSpin(AbstractWashingMachine.SPIN_FAST);
					spinSlow = false;
				}
				mode = 3;
				break;
			case SpinEvent.SPIN_SLOW:
				spinSlow = true;
				startTime = System.currentTimeMillis();
				mach.setSpin(AbstractWashingMachine.SPIN_RIGHT);
				mode = 3;
				break;
			case SpinEvent.SPIN_OFF:
				mach.setSpin(AbstractWashingMachine.SPIN_OFF);
				spinSlow = false;
				mode = 3;
				break;
				
			case 3:
				
			break;
		
		
		}
	}	
	
}
