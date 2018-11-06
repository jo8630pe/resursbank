package todo;


import se.lth.cs.realtime.*;
import done.AbstractWashingMachine;


public class TemperatureController extends PeriodicThread {
	private int mode;
	private AbstractWashingMachine mach;
	private WashingProgram wp;
	private double targetTemp;
	private double currentTemp;

	public TemperatureController(AbstractWashingMachine mach, double speed) {
		super((long) (4000/speed)); 
		this.mach = mach;
		mode = 2;
	}

	public void perform() {
		TemperatureEvent temp = (TemperatureEvent) mailbox.tryFetch();

		if(temp != null){
			mode = temp.getMode();
			targetTemp = temp.getTemperature();
			wp = (WashingProgram) temp.getSource();
		}

		switch(mode){

			case TemperatureEvent.TEMP_IDLE:
				mach.setHeating(false);
				mode = 2;
			break;

			case TemperatureEvent.TEMP_SET:
				currentTemp = mach.getTemperature();

				//Heat up
				if(mach.getWaterLevel() > 0 && currentTemp < targetTemp - 1.95){
					mach.setHeating(true);
					
				}
				
				//Turn off heating
				else if(currentTemp > targetTemp - 0.3){
					mach.setHeating(false);
					wp.putEvent(new AckEvent(this));
					mode = 3;
				}
				
				
			break;

			case 2:
				//Do nothing
			break;
			
			case 3:
				currentTemp = mach.getTemperature();
				//Heat up
				if(mach.getWaterLevel() > 0 && currentTemp < targetTemp - 1.7){
					mach.setHeating(true);
					
				}
				
				//Turn off heating
				else if(currentTemp > targetTemp - 0.3){
					mach.setHeating(false);
				}
				
				break;

		}
		
	}
}
