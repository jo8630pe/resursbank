package todo;

import se.lth.cs.realtime.*;
import done.AbstractWashingMachine;

public class WaterController extends PeriodicThread {
	private AbstractWashingMachine mach;
	private double waterLevel;
	private double targetLevel;
	private int mode;
	WashingProgram wp;
	WaterEvent event;
	
	
	public WaterController(AbstractWashingMachine mach, double speed) {
		super((long) (1000/speed)); // TODO: replace with suitable period
		this.mach = mach;
		waterLevel = 0;
		targetLevel = 0;
		mode = 3;
		
	}

	public void perform() {

		WaterEvent temp = (WaterEvent) mailbox.tryFetch();
		
		if(temp != null){
			mode = temp.getMode();
			targetLevel = temp.getLevel();
			wp = (WashingProgram) temp.getSource();
		}
		
		switch(mode){
		
		case WaterEvent.WATER_IDLE:
			mach.setFill(false);
			mach.setDrain(false);
			mode = 3;
			break;
			
		case WaterEvent.WATER_FILL:
			waterLevel = mach.getWaterLevel();
			if(waterLevel < targetLevel && mach.isLocked() && waterLevel < 0.5){
				mach.setDrain(false);
				mach.setFill(true);
				
			}
			else{
				mach.setFill(false);
				wp.putEvent(new AckEvent(this));
				mode = 3;
			}
			
			break;
			
		case WaterEvent.WATER_DRAIN:
			if(mach.getWaterLevel() > 0){
				mach.setFill(false);
				mach.setDrain(true);
			}
			else{
				wp.putEvent(new AckEvent(this));
				mach.setDrain(false);
				mode = 3;
			}
			break;
			
		case 3:
			break;
			
		}
		
	}
}
