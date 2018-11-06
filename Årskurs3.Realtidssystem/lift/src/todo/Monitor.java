package todo;

import done.BasketDrawer;
import done.LevelDrawer;

public class Monitor {
	private int here;
	private int next;
	private int[] waitEntry;
	private int[] waitExit;
	private int load;
	private boolean goingUp = true;
	private int personsInSystem;
	private BasketDrawer bD;
	private LevelDrawer lD;
	
	public Monitor( LevelDrawer lD, BasketDrawer bD) {
		next = 0;
		here = 0;
		load = 0;
		personsInSystem = 0;
		waitEntry = new int[7];
		waitExit= new int[7];
		this.bD = bD;
		this.lD = lD;
		
		for(int i = 0; i < 7; i++){
			waitEntry[i]= 0;
		}
		
		for(int i = 0; i < 7; i++){
			waitExit[i]= 0;
		}
		
	}
	
	public void runElevator(){
		int[] temp= moveLift();
		
		bD.moveLift(temp[0],temp[1]);
		
		liftStandingStill();
		
	}
	
	public synchronized int[] moveLift() {
		while (personsInSystem == 0) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(next == 6) {
			goingUp = false;
			
		}
		if(next == 0) {
			goingUp = true;

		}

		if(goingUp) {
			next++;
		}
		if(!goingUp) {
			next--;
		}
		
		int[] temp = new int[] {here, next};
		
		notifyAll();
		
		return temp;

	}
	
	public synchronized void liftStandingStill() {
		here = next;
		notifyAll();
		
		while(waitExit[here] != 0 || (waitEntry[here] != 0 && load < 4)) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public synchronized void requestLift(int initFloor, int targetFloor) {
		lD.drawWaiting(initFloor, 1);
		waitEntry[initFloor]++;
		personsInSystem++;
		notifyAll();
		while(initFloor != here || load == 4 || here != next || waitExit[here] != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		lD.drawWaiting(initFloor, -1);

		bD.drawLift(initFloor, load + 1);
		
		waitEntry[initFloor]--;
		load++;
		waitExit[targetFloor]++;	
		
		notifyAll();
		
	}
	
	public synchronized void requestToLeave(int targetFloor){
		while(targetFloor != here || here != next) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		bD.drawLift(here, load - 1);
		lD.drawLeaving(here);
		
		waitExit[targetFloor]--;
		load--;
		personsInSystem--;
		
		notifyAll();
	}
	
}
