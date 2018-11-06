package todo;

import done.AbstractWashingMachine;

public class WashingProgram2 extends WashingProgram {

	protected WashingProgram2(AbstractWashingMachine mach, double speed, TemperatureController tempController,
			WaterController waterController, SpinController spinController) {
		super(mach, speed, tempController, waterController, spinController);
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	protected void wash() throws InterruptedException {
		
		
		//Lock the hatch
		myMachine.setLock(true);
		
		//let water into the machine
		myWaterController.putEvent(new WaterEvent(this, WaterEvent.WATER_FILL, 0.5));
		mailbox.doFetch();
		
		//heat to 40'C
		myTempController.putEvent(new TemperatureEvent(this,TemperatureEvent.TEMP_SET, 40));
		mailbox.doFetch();
		
		//keep the temperature for 15 minutes
		sleep((long) (900000/mySpeed));
		myTempController.putEvent(new TemperatureEvent(this,TemperatureEvent.TEMP_IDLE, 0));
		
		//drain after pre-wash
		myWaterController.putEvent(new WaterEvent(this, WaterEvent.WATER_DRAIN, 0));
		mailbox.doFetch();
		
		//let water into the machine for the main wash
		myWaterController.putEvent(new WaterEvent(this, WaterEvent.WATER_FILL, 0.5));
		mailbox.doFetch();
		
		//heat to 90'C
		myTempController.putEvent(new TemperatureEvent(this,TemperatureEvent.TEMP_SET, 90));
		mailbox.doFetch();
		
		//keep the temperature for 30 minutes
		sleep((long) (1800000/mySpeed));
		myTempController.putEvent(new TemperatureEvent(this,TemperatureEvent.TEMP_IDLE, 0));
		
		//drain
		myWaterController.putEvent(new WaterEvent(this, WaterEvent.WATER_DRAIN, 0));
		mailbox.doFetch();
 		
		//rinse 5 times 2 minutes in cold water
		for(int i = 0; i < 5; i++){
			
			myWaterController.putEvent(new WaterEvent(this, WaterEvent.WATER_FILL, 0.5));
			mailbox.doFetch();
			
			mySpinController.putEvent(new SpinEvent(this, SpinEvent.SPIN_SLOW));
			sleep((long) (120000/mySpeed));
			myWaterController.putEvent(new WaterEvent(this, WaterEvent.WATER_DRAIN, 0));
			mailbox.doFetch();

		}
		
		
		
 		// centrifuge for 5 minutes
		mySpinController.putEvent(new SpinEvent(this, SpinEvent.SPIN_FAST));
		sleep((long) (300000/mySpeed));
		mySpinController.putEvent(new SpinEvent(this, SpinEvent.SPIN_OFF));
		
		//unlock the hatch
		if(myMachine.getWaterLevel() == 0) {
			myMachine.setLock(false);
		}

	}

}
