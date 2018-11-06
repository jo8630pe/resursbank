package todo;


public class Person extends Thread{
	private Monitor monitor;
	private int initFloor;
	private int targetFloor;


	public Person(Monitor monitor) {

		this.monitor = monitor;

	}
	
	public void run() {
		
		while(true) {
			
			try {
				sleep(1000*((int)(Math.random()*46.0)));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			initFloor = ((int)(Math.random()*7.0));			//beräknar initial och target floor
			targetFloor = ((int)(Math.random()*7.0));
			
			while(targetFloor == initFloor) {
				targetFloor = ((int)(Math.random()*7.0));
			}
			
			
			monitor.requestLift(initFloor, targetFloor);	//request lift
			
			monitor.requestToLeave(targetFloor);

		}
	}
}
