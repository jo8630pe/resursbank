package todo;

import done.*;

public class WashingController implements ButtonListener {	
	private AbstractWashingMachine theMachine;
	private double theSpeed;
	private SpinController sc;
	private TemperatureController tc;
	private WaterController wc;
	private WashingProgram currentProgram;
	private WashingProgram wp1;
	private WashingProgram wp2;
	private WashingProgram wp3;
	
    public WashingController(AbstractWashingMachine theMachine, double theSpeed) {
		this.theMachine = theMachine;
		this.theSpeed = theSpeed;
		sc = new SpinController(theMachine, theSpeed);
		wc = new WaterController(theMachine, theSpeed);
		tc = new TemperatureController(theMachine, theSpeed);
 		sc.start();
 		wc.start();
 		tc.start();
 		currentProgram = null;
    }

    public void processButton(int theButton) {
    	 
         switch (theButton) {
             case 0:  	//Stop: All motors, as well as any filling/draining of water, should be turned
            	 	  	//off immediately
            	 		if(currentProgram != null){
            	 			currentProgram.interrupt();
            	 			currentProgram.terminate();
            	 		}
                      break;
                      
             case 1:  	
            	 		//Color wash
            	 		if(currentProgram == null || !currentProgram.isAlive() ){
                	 		wp1 = new WashingProgram1(theMachine,theSpeed,tc,wc,sc);
                	 		currentProgram = wp1;
                	 		wp1.start();
            	 		}

                      break;
                      
             case 2:  	//White wash
     	 				if(currentProgram == null || !currentProgram.isAlive()){
     	 					wp2 = new WashingProgram2(theMachine,theSpeed,tc,wc,sc);
     	 					currentProgram = wp2;
     	 				wp2.start();
     	 				}
     	 				
                      break;
                      
             case 3:  	
     	 				//Draining: Turn off heating and rotation. After pumping out the water(if any),
     	 				//unlock the hatch. Note the user should select this program as soon as 
     	 				//possible after 0 (stop).
            	 		if(currentProgram != null)
            	 			if(!currentProgram.isAlive()){
                    	 		wp3 = new WashingProgram3(theMachine,theSpeed,tc,wc,sc);
                    	 		currentProgram = wp3;
                    	 		wp3.start();
            	 			}

                      break;
             
             default: 
                      break;
         }
    }
}